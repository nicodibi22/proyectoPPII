package proyecto.servicios.impl;

import java.util.HashMap;
import java.util.Map;

import proyecto.servicios.INube;

public class RegistryNube {

	private static Map<String, INube> registry = new HashMap<String, INube>();
	private String moduleName = null;
	
	private static RegistryNube getInstance() {       
		return soleInstance;    
		
	}    
	
	private static RegistryNube soleInstance = new RegistryNube();
	
}
