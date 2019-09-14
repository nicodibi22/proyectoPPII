package proyecto.servicios.impl;

import proyecto.Album;
import proyecto.AutenticadorExcepcion;
import proyecto.CredencialesApi;
import proyecto.Resultados;
import proyecto.Token;
import proyecto.TokenInvalidoException;

public class ApiManager {

	Token token;
	
	GatewayRedSocial oGatewayRedSocial;
	
	GatewayNube oGatewayNube;
	
	private synchronized GatewayNube getInstanceNube() throws IllegalAccessException {
		
		if(oGatewayNube == null) {
			oGatewayNube = new GatewayNube(this.credenciales.getUsuario());
		}
		return oGatewayNube;
	}
	
	private synchronized GatewayRedSocial getInstanceRedSocial() {
		
		if(oGatewayRedSocial == null) {
			oGatewayRedSocial = new GatewayRedSocial();
		}
		return oGatewayRedSocial;
	}
	
	public enum FormatoRespuesta {
		JSON,
		XML
	}
	
	public ApiManager(Token token) throws TokenInvalidoException {
				
		this.token = token;		
		validarToken();							
		this.formatoRespuesta = FormatoRespuesta.JSON;				
	}
	
	public ApiManager(Token token, FormatoRespuesta formato) throws AutenticadorExcepcion, TokenInvalidoException {
		this(token);
		this.formatoRespuesta = formato;
	}
		
	private CredencialesApi credenciales;
	
	private FormatoRespuesta formatoRespuesta;
	
	private void validarToken() throws TokenInvalidoException {
		token.validar();
	}
	
	public String subirCompartirNube(String nombreArchivo, String mailUsuarios) throws Throwable {
		validarToken();					
		Resultados res = getInstanceNube().subirArchivosCompartir(nombreArchivo, mailUsuarios);
		return getObjetoConFormato(res);
	}
	
	public String obtenerFotos(String tag) throws Throwable {
		validarToken();		
		Album album = getInstanceRedSocial().getFotos(tag);
		return getObjetoConFormato(album);
	}
		
	public String getObjetoConFormato(Object o) {
		return ConversorFactory.getConversor(formatoRespuesta).convert(o);
	}
	
}
