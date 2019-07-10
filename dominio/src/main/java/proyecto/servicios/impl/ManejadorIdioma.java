package proyecto.servicios.impl;

import java.util.Locale;

public class ManejadorIdioma {

	public enum Idioma {
		ESPANIOL,
		INGLES
	}
	
	private Locale locale;
	
	private static ManejadorIdioma controllerIdioma;
	
	
	public static ManejadorIdioma getInstance(Idioma idioma) {
		if(controllerIdioma == null) {
			controllerIdioma = new ManejadorIdioma();	
			
		}
		controllerIdioma.setLocale(idioma);
		return controllerIdioma;
	}
	
	public static ManejadorIdioma getInstance() {
		if(controllerIdioma == null) {
			controllerIdioma = new ManejadorIdioma();	
			
		}
		return controllerIdioma;
	}
	
	private ManejadorIdioma() {
		 
		this.locale = new Locale("es", "ES");
	}

	public void setLocale(Idioma idioma) {
		if(idioma == Idioma.ESPANIOL) {
			this.locale = new Locale("es", "ES");
		} else if (idioma == Idioma.INGLES) {
			this.locale = new Locale("en", "EN");
		}
	}
	
	public Locale getLocale() {
		return this.locale;
	}
	
}
