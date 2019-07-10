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

import proyecto.servicios.Autenticador;
import proyecto.servicios.impl.CredencialInfo.CredencialEstado;

public class AutenticadorApi implements Autenticador {

	private static AutenticadorApi instance = null;
	
	private CredencialInfo credencial; 
	
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
	public void autenticar(String user, String password) throws AutenticadorExcepcion {
		credenciales.remove(user);
		CredencialEstado estado = null;
		try {
			if(!usuarios.containsKey(user))
			{
				estado = CredencialEstado.INEXISTENTE;
				
			} else if(!usuarios.get(user).equals(password)) {
				estado = CredencialEstado.INVALIDO;
				
			} else {
				estado = CredencialEstado.VALIDO;
			}	
		} finally {
			credencial = new CredencialInfo(user, estado);		
			credenciales.put(user, credencial);		
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
	
	public CredencialInfo getCredenciales(String user) {
		return credenciales.get(user);
	}
}
