package proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import proyecto.servicios.INube;

public class NubeManagerFactory {
	
	static Map<String, INube> connectors = ListaNubes.loadNubes();
	
	public static INube getNube(String nube) {
		return connectors.get(nube);
	}
	
	public static List<INube> getNube(List<String> clouds) {
		List<INube> nubes = new ArrayList<INube>();
		for(String c : clouds) {
			nubes.add(connectors.get(c));
			}
		return nubes;
	}
}