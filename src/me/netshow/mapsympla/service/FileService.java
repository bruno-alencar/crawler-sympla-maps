package me.netshow.mapsympla.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.netshow.mapsympla.entity.Empresa;

public class FileService {


	public void write(List<Empresa> empresas){

		File arquivo = new File("resultado.csv");
		try(
				FileWriter fw = new FileWriter(arquivo, true) ){

			for(Empresa empresa: empresas){
				
				fw.write("\n"+empresa.getIdMaps());
				fw.write(","+empresa.getNome());
				fw.write(","+empresa.getSite());
				fw.write(","+empresa.getTelefone());
				fw.write(","+empresa.getEndereco());
			}

			fw.flush();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	public List<String> read(){
		
		File arquivo = new File("dados.csv");
		List<String> nomesList = new ArrayList<String>();
		
		try {
			FileReader fileReader = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fileReader);
			
			String linha = br.readLine();
			linha = br.readLine();
			while(linha != null){
				
				String [] empresa = linha.split(",");
				
				nomesList.add(empresa[2]);
				linha = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return nomesList;
	}
}
