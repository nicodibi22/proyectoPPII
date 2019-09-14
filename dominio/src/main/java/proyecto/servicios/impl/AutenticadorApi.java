package proyecto.servicios.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import proyecto.AutenticadorExcepcion;
import proyecto.Token;
import proyecto.servicios.Autenticador;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class AutenticadorApi implements Autenticador {

	private static AutenticadorApi instance = null;	
	
	public static AutenticadorApi getInstance() {
	      if(instance == null) {
	         instance = new AutenticadorApi();
	      }
	      return instance;
	   }
	
	Map<String, String> usuarios = new HashMap<String, String>();
	Map<String, CredencialInfo> credenciales = new HashMap<String, CredencialInfo>();
	private AutenticadorApi() {
		cargarUsuariosRegistrados();
	}
	
	@Override
	public Token autenticar(String user, String password) throws AutenticadorExcepcion {
				
		verificarCredenciales(user, password);
		return new GeneradorToken(user, 300000L).getToken();				
		
	}

	private void verificarCredenciales(String user, String password) throws AutenticadorExcepcion {
		
		if(!usuarios.containsKey(user)) {
			throw new AutenticadorExcepcion("Usuario inexistente.");
		} else if(!usuarios.get(user).equals(password)) {			
			throw new AutenticadorExcepcion("Usuario inválido.");
		} 
						
	}
	
	private void cargarUsuariosRegistrados() {
		JSONParser parser = new JSONParser();
		try {			
			Object obj = parser.parse(new FileReader(
                    Propiedad.getInstance().getPropiedad("PATH_FILE_USUARIOS")));
 
			JSONArray companyList = (JSONArray) obj;
			
			Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
            	JSONObject usuario = iterator.next();
            	String user = (String) usuario.get("user");
                String pass = (String) usuario.get("pass");
                usuarios.put(user, pass);
            }			
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
}
