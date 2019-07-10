package proyecto.servicios.impl;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import proyecto.CredencialesApi;
import proyecto.servicios.Autenticador;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class ApiManager {

	GatewayRedSocial redSocial;
	
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
	
	public void subirNube() throws AutenticadorExcepcion {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}
	}
	
	public String obtenerFotos(String tag) throws AutenticadorExcepcion {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}				
		
		redSocial.conectar(this.credenciales.getUsuario());
		
		return "";
	}
}
