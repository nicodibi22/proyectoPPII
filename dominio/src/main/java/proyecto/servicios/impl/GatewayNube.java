package proyecto.servicios.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import proyecto.Resultado;
import proyecto.Resultado.EstadoResultado;
import proyecto.Resultados;
import proyecto.loader.LoaderClase;
import proyecto.servicios.CircuitBreaker;
import proyecto.servicios.CircuitBreakerException;
import proyecto.servicios.INube;

public class GatewayNube {

	private List<String> nubesHabilitadas;
	
	public GatewayNube(String usuario) throws IllegalAccessException {
		
		cargarNubesPorUsuario(usuario);
		if(nubesHabilitadas.size() == 0) {
			throw new IllegalAccessException("El usuario no tiene acceso a ninguna nube.");
		}
	}
	
	private void cargarNubesPorUsuario(String usuario) {
		nubesHabilitadas = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		try {			
			Object obj = parser.parse(new FileReader(
                    Propiedad.getInstance().getPropiedad("PATH_FILE_USUARIOS")));
 
			JSONArray companyList = (JSONArray) obj;
			
			
			
			Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
            	JSONObject usua = iterator.next();
            	
            	if(usuario.equals((String) usua.get("usuario"))) {
            		JSONArray nubes = (JSONArray) usua.get("nubes");
            		while(nubes.iterator().hasNext()) {
            			JSONObject nube = (JSONObject) nubes.iterator().next();
            			nubesHabilitadas.add((String) nube.get("nombre"));
            		}
            	}
                
            }			
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Resultados subirArchivosCompartir(String archivo, String mailUsuarios) throws Exception {
		
		Resultados resultados = new Resultados();
		
		ClassLoader loaderGenerico = LoaderClase.class.getClassLoader();
		LoaderClase loader = new LoaderClase(loaderGenerico);
						
		CircuitBreaker<Boolean> circuitBreaker = new CircuitBreakerNube();
		String respuesta;
		String error = "";
		for(String n : nubesHabilitadas) {
			Class nube = loader.loadClass(n);					
			INube drive = (INube)nube.newInstance();
			Resultado r = new Resultado();
			try {
				Supplier<Boolean> con = () -> drive.conectar();
				Supplier<Boolean> subirCompartir = () -> {
					try {
						return drive.uploadAndShare(archivo, mailUsuarios);
					} catch (IOException e) {
						return false;
					}					
				};
					
				circuitBreaker.ejecutar(con, subirCompartir);
				r.setEstado(EstadoResultado.OK);
				respuesta = drive.getTipo().name() + " - OK";
		 	} catch (CircuitBreakerException e) {
		 		respuesta = drive.getTipo().name() + " - ERROR";
		 		error = e.getMessage();
		 		r.setEstado(EstadoResultado.ERROR);
		 		
			} 
			r.setMensajeError(error);
			r.setRespuesta(respuesta);
			resultados.agregarResultado(r);
		}
				
		return resultados;
	}
}
