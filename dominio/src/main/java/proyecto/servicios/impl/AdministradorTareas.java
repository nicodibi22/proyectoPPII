package proyecto.servicios.impl;

import java.util.Observable;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class AdministradorTareas<T> extends TimerTask {

	Callable<T> funcion;
	
	Observable o;
	
	public AdministradorTareas(Callable<T> c, Observable o) {
		funcion = c;
	}
	
	
	
	@Override
	public void run() {
		try {			
			o.notifyObservers(funcion.call());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
