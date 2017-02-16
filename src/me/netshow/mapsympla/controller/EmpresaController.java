package me.netshow.mapsympla.controller;

import me.netshow.mapsympla.service.EmpresaService;

public class EmpresaController {
	
	private EmpresaService empresaService = new EmpresaService();
	
	public void start(){
		
		this.empresaService.gerenciador();
	}
}
