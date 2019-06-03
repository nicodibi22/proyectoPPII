package proyecto;

import proyecto.servicios.IAutenticador;
import proyecto.servicios.IRedSocial;
import proyecto.servicios.impl.AutenticadorApi;
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
        
        IAutenticador a = new AutenticadorApi();
        
        IRedSocial red = new RedSocialInstagram();
        red.Autenticar();
        
    }
}
