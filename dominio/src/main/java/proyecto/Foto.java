package proyecto;

public class Foto {

	private String cometario;
	
	private String ubicacion;
	
	public Foto(String cometario, String ubicacion) {
		this.cometario = cometario;
		this.ubicacion = ubicacion;
	}
	
	public String getComentario() {
		return this.cometario;
	}
	
	public String getUbicacion() {
		return this.ubicacion;
	}
}
