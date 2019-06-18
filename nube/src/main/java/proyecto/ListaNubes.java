package proyecto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyecto.servicios.INube;

public class ListaNubes {
	
	static Map<String, INube> connectors = new HashMap<String, INube>();
	
	public static List<String> getListaNubes() {
		return Arrays.asList(
				"GOOGLEDRIVE",
				"DROPBOX",
				"ONEDRIVE");
	}
	
	public static Map<String, INube> loadNubes(){
		List<String> nubes = ListaNubes.getListaNubes();
		for (String s: nubes) {
			if("GOOGLEDRIVE".equals(s))
				connectors.put(s,new ConectorDrive());
			else if("DROPBOX".equals(s))
				connectors.put(s,new ConectorDropBox());
			else if("ONEDRIVE".equals(s))
				connectors.put(s,new ConectorOneDrive());
		}
		return connectors;
	}

}