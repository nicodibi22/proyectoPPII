package proyecto.servicios.impl;

import java.util.Enumeration;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import proyecto.CredencialesApi;
import proyecto.servicios.Autenticador;

public class ApiManager {

	
	public enum FormatoRespuesta {
		JSON,
		XML
	}
	
	public ApiManager(CredencialesApi credenciales) throws AutenticadorExcepcion {
		Autenticador aut = AutenticadorApi.getInstance();
		aut.autenticar(credenciales.getUsuario(), credenciales.getPassword());
		this.credenciales = new CredencialesApi(credenciales.getUsuario(), credenciales.getPassword());
		this.formatoRespuesta = FormatoRespuesta.JSON;
	}
	
	public ApiManager(CredencialesApi credenciales, FormatoRespuesta formato) throws AutenticadorExcepcion {
		AutenticadorApi aut = AutenticadorApi.getInstance();
		aut.autenticar(credenciales.getUsuario(), credenciales.getPassword());
		this.credenciales = new CredencialesApi(credenciales.getUsuario(), credenciales.getPassword());
		this.formatoRespuesta = formato;
	}
	
	private CredencialesApi credenciales;
	
	private FormatoRespuesta formatoRespuesta;
	
	public void subirNube() {
		
	}
	
	public String obtenerFotos() {
		
		return "";
	}
}
