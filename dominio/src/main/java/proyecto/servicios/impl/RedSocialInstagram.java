package proyecto.servicios.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramApi;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.OAuthRequest;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.http.Response;

import proyecto.servicios.IRedSocial;

public class RedSocialInstagram implements IRedSocial {

	@Override
	public boolean estaAutenticado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Autenticar() {
		// TODO Auto-generated method stub
		InstagramApi api = new InstagramApi();
		
		InstagramService service = new InstagramAuthService()
				.apiKey("8e4f918e34b345ebac87b933e755f9c4")
				.apiSecret("ca5e9ee18fd64e4f97bc6fe7d901c89d")
				.callback("http://google.com.ar")     
				.build();
		
	    Verifier verifier = new Verifier("72ccc9ba6fee4352a8c6deb80e95d8bb");

	    Token accessToken = service.getAccessToken(verifier);  //Token successfully gotten
	    
	   
	    
	    //** RUNS OK UP TO THIS LINE INCLUDED **//
	    Instagram instagram = new Instagram(accessToken);   //Ok
		
		
		String pepe = service.getAuthorizationUrl();
		
		//Verifier verifier = new Verifier("code");
		Token tok = service.getAccessToken(verifier);
		service.getRequestToken();
		System.out.println(tok.getToken());
		String url = "https://api.instagram.com/oauth/authorize/?client_id=8e4f918e34b345ebac87b933e755f9c4&redirect_uri=http://google.com.ar&response_type=code";
		
	    //add headers to the connection, or check the status if desired..
	    
	    // handle error response code it occurs
	    int responseCode;
	    InputStream inputStream;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			responseCode = connection.getResponseCode();
			
		    if (200 <= responseCode && responseCode <= 299) {
		        inputStream = connection.getInputStream();
		    } else {
		        inputStream = connection.getErrorStream();
		    }
		    BufferedReader in = new BufferedReader(
			        new InputStreamReader(
			            inputStream));

		   
			for (String key : connection.getHeaderFields().keySet()) {
				System.out.println(key + " " + connection.getHeaderFields().get(key).get(0));
			}
		    
		    StringBuilder response = new StringBuilder();
		    String currentLine;

		    while ((currentLine = in.readLine()) != null) 
		        response.append(currentLine);

		    in.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    



	    //return response.toString();
		
	}

	@Override
	public void Publicar(String comentario, String pathArchivo) {
		// TODO Auto-generated method stub
		
	}

}
