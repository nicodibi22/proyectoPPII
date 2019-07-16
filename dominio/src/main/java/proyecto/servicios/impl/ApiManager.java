package proyecto.servicios.impl;


import proyecto.Album;
import proyecto.CredencialesApi;
import proyecto.Resultados;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class ApiManager {

	GatewayRedSocial oGatewayRedSocial;
	
	GatewayNube oGatewayNube;
	
	private GatewayNube getInstanceNube() throws IllegalAccessException {
		
		if(oGatewayNube == null) {
			oGatewayNube = new GatewayNube(this.credenciales.getUsuario());
		}
		return oGatewayNube;
	}
	
	private GatewayRedSocial getInstanceRedSocial() {
		
		if(oGatewayRedSocial == null) {
			oGatewayRedSocial = new GatewayRedSocial();
		}
		return oGatewayRedSocial;
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
	
	public String subirCompartirNube(String nombreArchivo, String mailUsuarios) throws AutenticadorExcepcion, Exception {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}
					
		Resultados res = getInstanceNube().subirArchivosCompartir(nombreArchivo, mailUsuarios);
		return getObjetoConFormato(res);
	}
	
	public String obtenerFotos(String tag) throws AutenticadorExcepcion {
		if(!estaAutenticado()) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_AUTENTICADO);
		}				
		
		Album album = getInstanceRedSocial().getFotos(tag);
		return getObjetoConFormato(album);
	}
	
	
	public String getObjetoConFormato(Object o) {
		return ConversorFactory.getConversor(formatoRespuesta).convert(o);
	}
	
}
