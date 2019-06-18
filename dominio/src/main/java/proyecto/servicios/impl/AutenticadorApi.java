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

import proyecto.servicios.IAutenticador;

public class AutenticadorApi implements IAutenticador {

	Map<String, String> usuarios = new HashMap<String, String>();
	
	public AutenticadorApi() {
		cargarUsuariosRegistrados();
	}
	
	@Override
	public void Autenticar(String user, String password) throws AutenticadorExcepcion {
		if(!usuarios.containsKey(user))		
			throw new AutenticadorExcepcion(AutenticadorExcepcion.USUARIO_NO_VALIDO);
		if(!usuarios.get(user).equals(password)) {
			throw new AutenticadorExcepcion(AutenticadorExcepcion.CONTRASENIA_INVALIDA);
		}
	}

	private void cargarUsuariosRegistrados() {
		JSONParser parser = new JSONParser();
		try {			
			Object obj = parser.parse(new FileReader(
                    "src/main/resources/usuarios.json"));
 
			JSONArray companyList = (JSONArray) obj;
			
			Iterator<JSONObject > iterator = companyList.iterator();
            while (iterator.hasNext()) {
            	JSONObject usuario = iterator.next();
            	String user = (String) usuario.get("user");
                String pass = (String) usuario.get("pass");
                usuarios.put(user, pass);
            }
			

            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
