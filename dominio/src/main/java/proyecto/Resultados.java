package proyecto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import proyecto.Resultado.EstadoResultado;

public class Resultados {

	List<Resultado> resultados;
	
	public Resultados() {
		resultados = new ArrayList<Resultado>();
	}
	
	public void agregarResultado(Resultado resultado) {
		resultados.add(resultado);
	}
	
	public List<Resultado> getResultadosOk() {
		List<Resultado> res = new ArrayList<Resultado>();;
		
		Iterator<Resultado> iterator = resultados.iterator();
		
		while(iterator.hasNext()) {
			Resultado r = iterator.next();
			if (r.getEstado().equals(EstadoResultado.OK)) {
				res.add(r);
			}
		}
		return res;
	}
}
