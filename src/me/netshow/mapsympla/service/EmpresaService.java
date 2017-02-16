package me.netshow.mapsympla.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.netshow.mapsympla.entity.Empresa;

public class EmpresaService {

	public void gerenciador(){
		
		FileService fileService = new FileService();
		List<String> nomesList = fileService.read();
		
		List<Empresa> empresas = this.getIdFromMaps(nomesList);
		empresas = this.getDataFromMaps(empresas);
		
		fileService.write(empresas);
	}	

	
	private List<Empresa> getDataFromMaps(List<Empresa> empresas){
		
		for(Empresa empresa: empresas){
			
			String url = "https://maps.googleapis.com/maps/api/place/details/json?"
					+ "placeid="+ empresa.getIdMaps()
					+ "&key=AIzaSyBeiix0W7ANoqJOowa-xr-PaYjDGrArxo4";
			
			String response = this.connect(url);
			try {
				JSONObject jsonObjectResponse = new JSONObject(response);
				JSONObject jsonObjectResult = jsonObjectResponse.getJSONObject("result");
				
				if(jsonObjectResult.has("international_phone_number")){
					String telefone = jsonObjectResult.getString("international_phone_number");
					empresa.setTelefone(telefone);					
				}
				if(jsonObjectResult.has("website")){
					String site = jsonObjectResult.getString("website");
					empresa.setSite(site);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return empresas;
	}
	
	
	private List<Empresa> getIdFromMaps(List<String> nomesList){

		List<Empresa> empresas = new ArrayList<Empresa>();
		
		int contador = 0;
		for(String nomeEmpresa: nomesList ){
			
			String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
					+ "query=" + nomeEmpresa.replaceAll(" ", "+")
					+ "&key=AIzaSyBeiix0W7ANoqJOowa-xr-PaYjDGrArxo4";
			contador++;
			
			System.out.println(contador);
			String response = this.connect(url);

			try {
				JSONObject jsonObjectResponse = new JSONObject(response);
				JSONArray jsonArrayResults = jsonObjectResponse.getJSONArray("results");
				
				if(jsonArrayResults.length() > 0 ){
					
					JSONObject jsonObjectData = jsonArrayResults.getJSONObject(0);	
	
					String endereco = jsonObjectData.getString("formatted_address");
					String idMaps = jsonObjectData.getString("place_id");
	
	
					Empresa empresa = new Empresa();
					empresa.setEndereco(endereco);
					empresa.setIdMaps(idMaps);
					empresa.setNome(nomeEmpresa);
					
					empresas.add(empresa);
				}else{
					System.out.println(url);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return empresas;
	}

	private String connect(String link){
		String jsonReturn = "";
		try {	
			URL url = new URL(link);

			URLConnection urlConnection = url.openConnection();

			urlConnection.addRequestProperty("User-Agent", 
					"Mozilla/48.0 (compatible; MSIE 6.0; Windows NT 5.0)");

			InputStreamReader inputStreamReader= new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String inputLine;

			while((inputLine = bufferedReader.readLine()) != null){
				jsonReturn += (inputLine + "\n");
			}

			bufferedReader.close();
			inputStreamReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


		return jsonReturn;
	}
}
