package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.RedSocial;
import proyecto.servicios.impl.RedSocialTwitter;
import twitter4j.TwitterException;

public class RedSocialTwitterTest {

	@Test (expected = TwitterException.class) 
	public void publicarTweetNoAutorizadoTest() throws Exception {
		RedSocial red = new RedSocialTwitter();
		red.publicar("ninguno", "/test.jpg");
	}
	
	@Test  
	public void autenticarTest() {
		RedSocial red = new RedSocialTwitter();
		red.autenticar();
		assertTrue(red.estaAutenticado());
	}
}
