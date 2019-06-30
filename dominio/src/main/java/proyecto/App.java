package proyecto;

import proyecto.servicios.Autenticador;
import proyecto.servicios.RedSocial;
import proyecto.servicios.impl.AutenticadorApi;
import proyecto.servicios.impl.RedSocialFlickr;
import proyecto.servicios.impl.RedSocialInstagram;
import proyecto.servicios.impl.RedSocialTwitter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        

        RedSocial red = new RedSocialTwitter();
        red.autenticar();
        red.getFotos("");
    }
}
