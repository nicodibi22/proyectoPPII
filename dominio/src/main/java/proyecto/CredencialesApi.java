package proyecto;

public class CredencialesApi {

	public CredencialesApi(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}
	
	private String usuario;
	
	private String password;
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
}
