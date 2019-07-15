package proyecto.servicios.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import proyecto.CredencialesApi;
import proyecto.loader.LoaderClase;
import proyecto.servicios.Autenticador;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;
import proyecto.servicios.INube;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class ApiManager {

	GatewayRedSocial redSocial;
	
	GatewayNube gNube;
	
	private GatewayNube getInstanceNube() throws IllegalAccessException {
		
		if(gNube == null) {
			gNube = new GatewayNube(this.credenciales.getUsuario());
		}
		return gNube;
	}
	
	public enum FormatoRespuesta {
		JSON,
		XML
	}
	
	public ApiManager(CredencialesApi credenciales) throws AutenticadorExcepcion {
				
		this.credenciales = new CredencialesApi(credenciales.getUsuario(), credenciales.getPassword());				
		this.formatoRespuesta = FormatoRespuesta.JSON;
		AutenticadorApi.getInstance().autenticar(credenciales.getUsuario(), credenciales.getPassword());
		
	}
	
	public ApiManager(CredencialesApi credenciales, FormatoRespuesta formato) throws AutenticadorExcepcion {
		this(credenciales);
		this.formatoRespuesta = formato;
	}
	
	public CredencialInfo getCredenciales() {
		return AutenticadorApi.getInstance().getCredenciales(this.credenciales.getUsuario());		
	}
	
	private CredencialesApi credenciales;
	
	private FormatoRespuesta formatoRespuesta;
	
	private boolean estaAutenticado() {
		return this.getCredenciales().getEstado().equals(CredencialEstado.VALIDO);
	}
	
	/*public void subirNube(String nombreArchivo) throws AutenticadorExcepcion, Exception {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}
		
		
		GatewayNube gNube = new GatewayNube(this.credenciales.getUsuario());
		
		gNube.subirArchivosCompartir(nombreArchivo, mailUsuarios);
	}*/
	
	public void subirCompartirNube(String nombreArchivo, String mailUsuarios) throws AutenticadorExcepcion, Exception {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}
				
		gNube = getInstanceNube();		
		gNube.subirArchivosCompartir(nombreArchivo, mailUsuarios);
	}
	
	public String obtenerFotos(String tag) throws AutenticadorExcepcion {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}				
		
		redSocial.conectar(this.credenciales.getUsuario());
		redSocial.getFotos(tag);
		return "";
	}
	
	public static List<String> getListaNubes() {
		return Arrays.asList(
				"GOOGLEDRIVE",
				"DROPBOX",
				"ONEDRIVE");
	}
}
