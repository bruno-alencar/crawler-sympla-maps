package me.netshow.mapsympla.entity;

public class Empresa {
	
	private Long id;
	private String idMaps;
	private String endereco;
	private String telefone;
	private String site;
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdMaps() {
		return idMaps;
	}
	public void setIdMaps(String idMaps) {
		this.idMaps = idMaps;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	 

}
