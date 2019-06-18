package proyecto.servicios.impl;

import java.util.Enumeration;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import proyecto.CredencialesApi;

public class ApiManager {

	
	public enum FormatoRespuesta {
		JSON,
		XML
	}
	
	public ApiManager(CredencialesApi credenciales) throws AutenticadorExcepcion {
		AutenticadorApi aut = new AutenticadorApi();
		aut.Autenticar(credenciales.getUsuario(), credenciales.getPassword());
		this.credenciales = new CredencialesApi(credenciales.getUsuario(), credenciales.getPassword());
		this.formatoRespuesta = FormatoRespuesta.JSON;
	}
	
	private CredencialesApi credenciales;
	
	private FormatoRespuesta formatoRespuesta;
	
	public void subirNube() {
		
	}
	
	public String obtenerFotos() {
		
		return "";
	}
}
