package proyecto.servicios.impl;

import java.io.File;

import proyecto.servicios.RedSocial;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.Authorization;
import twitter4j.auth.BasicAuthorization;
import twitter4j.auth.NullAuthorization;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;

public class RedSocialTwitter implements RedSocial {

	private Twitter twitterInstance;
	
	private Twitter getTwitterInstance() {
		if (twitterInstance != null)
			return twitterInstance;
		return null;
	}
	
	@Override
	public boolean estaAutenticado() {
		return twitterInstance.getAuthorization().isEnabled();
	}

	@Override
	public void autenticar() {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("pC5e2TMGb34NHfcB6ewSe78VN")
		  //.setOAuthConsumerSecret("KgmJJbxg8BdhxigT0If6VKyYfvqUWA4rdl0Y")
		  .setOAuthConsumerSecret("WHdlOlMKYwl6J2KgmJJbxg8BdhxigT0If6VKyYfvqUWA4rdl0Y")
		  .setOAuthAccessToken("1086983048850296836-QjxMVxEo8spOtCB2ehKZIHAjYseIW1")
		  .setOAuthAccessTokenSecret("BEx7kaPp0p4OA57mzQZumo208YZfqIGonzWASsUQ1iPFO");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitterInstance = tf.getInstance();
		
		esUsuario();
		
	}

	@Override
	public void publicar(String comentario, String pathArchivo) throws Exception {
		
		if(getTwitterInstance() != null && getTwitterInstance().getAuthorization() != NullAuthorization.getInstance()) {
			String statusMessage = "#PruebaPPII";
			File file = new File("C:\\Users\\Nico\\Desktop\\UNGS\\Programacion III\\5491f9e8516af.jpg"); 

			StatusUpdate status = new StatusUpdate(statusMessage);
			status.setMedia(file); // set the image to be uploaded here.
			twitterInstance.updateStatus(status);
			File mediaFile = null;
			//twitterInstance.uploadMedia(mediaFile);
		} else {
			throw new TwitterException("Usuario no autorizado");
		}
		
		
	}

	public boolean esUsuario() {
		Authorization aut = twitterInstance.getAuthorization(); 
		
		OAuthAuthorization  autBa = (OAuthAuthorization)aut;
		try {
			User usuario = twitterInstance.showUser(autBa.getOAuthAccessToken().getUserId());
			return true;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void getFotos(String tag) {
		Query query = new Query();
		query.setQuery(tag);
		try {
			QueryResult result = twitterInstance.search(query);
			result.getCount();
			
			for (Status sta : result.getTweets()) {
				
				MediaEntity[] media = sta.getMediaEntities(); //get the media entities from the status
				for(MediaEntity m : media){ //search trough your entities
				    System.out.println(m.getMediaURL()); //get your url!
				}
				
			}
				
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
