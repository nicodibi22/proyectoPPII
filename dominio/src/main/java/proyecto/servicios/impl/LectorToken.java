package proyecto.servicios.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LectorToken {

	public static long getExpires(String token) {
    	
		return Long.parseLong(getData(token, "exp"));
    }
	
	public static String getData(String token, String etiqueta) {
    	String[] tokenSplit = token.split("/");
		
		String json = TokenUtiles.decode(tokenSplit[1]);

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject.get(etiqueta).toString();
    }
	
	public static boolean signatureValido(String token) {
    	String[] tokenSplit = token.split("/");
		
		return GeneradorToken.generarSignature(tokenSplit[0], tokenSplit[1]).equals(tokenSplit[2]);		
    }
}
