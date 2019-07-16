package proyecto;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private List<Foto> fotos;
	
	public Album() {
		fotos = new ArrayList<Foto>();
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}
	
	public void agregarFoto(Foto foto) {
		fotos.add(foto);
	}
	
	public void agregarFotos(List<Foto> fotos) {
		fotos.addAll(fotos);
	}
}
