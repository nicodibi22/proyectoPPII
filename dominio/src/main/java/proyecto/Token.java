package proyecto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import proyecto.servicios.impl.LectorToken;

public class Token {
	
	private String token;
	
	public Token(String token) {
		this.token = token;
	}
	
	
	
	public boolean esValido() {
		if(LectorToken.getExpires(token) < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) {
			return false;
		}
		if(!LectorToken.signatureValido(token)) {
			return false;
		}
		return true;
	}
	
	public void validar() throws TokenInvalidoException {
		
		if (!esValido()) {
			throw new TokenInvalidoException("Token inválido o expirado.");
		}
		
	}
	
	
}
