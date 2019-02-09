package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.IRedSocial;
import proyecto.servicios.impl.RedSocialTwitter;
import twitter4j.TwitterException;

public class RedSocialTwitterTest {

	@Test (expected = TwitterException.class) 
	public void publicarTweetNoAutorizadoTest() throws Exception {
		IRedSocial red = new RedSocialTwitter();
		red.Publicar("ninguno", "/test.jpg");
	}
	
	@Test  
	public void autenticarTest() {
		IRedSocial red = new RedSocialTwitter();
		red.Autenticar();
	}
}
