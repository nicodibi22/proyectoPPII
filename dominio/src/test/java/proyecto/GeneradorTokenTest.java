package proyecto;

import static org.junit.Assert.*;

import org.junit.Test;

import proyecto.servicios.impl.GeneradorToken;
import proyecto.servicios.impl.TokenUtiles;

public class GeneradorTokenTest {

	
	
	@Test
	public void GenerarTokenDistintos2Test() {
		
		GeneradorToken tok = new GeneradorToken("nico", 21000);
		System.out.println(tok.getToken());
		
		long aaa = GeneradorToken.getExpires("");
		
		String[] pepe = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9/eyJ1c3VhcmlvIjoibmljbyIsImV4cCI6MjEwMDAsImlhdCI6MTU2ODAyODczOH0/jHMXjkVlBtjK8sGZkGhlY0HjKpccSjIgvab66s7kGa8".split("/");
		
		for (String a : pepe) {
			System.out.println(TokenUtiles.decode(a));
		}
	}
	
}
