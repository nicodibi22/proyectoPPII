package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.impl.GeneradorToken;

public class GeneradorTokenTest {

	@Test
	public void GenerarTokenTest() {
		String token = GeneradorToken.nextToken();
		assertTrue(token.length() == 256);
	}

	@Test
	public void GenerarTokenDistintosTest() {
		String token = GeneradorToken.nextToken();
		String token2 = GeneradorToken.nextToken();
		assertFalse(token.equals(token2));
	}
	
}
