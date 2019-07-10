package proyecto;

import proyecto.controladores.FrontController;
import proyecto.servicios.impl.ManejadorIdioma.Idioma;


public class App 
{
    public static void main( String[] args )
    {
    	FrontController.getInstance(Idioma.ESPANIOL).manejarRequest("HOME");
    }
}
