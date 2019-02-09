package proyecto;

import proyecto.servicios.IRedSocial;
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
        
        IRedSocial red = new RedSocialTwitter();
        red.Autenticar();
        
    }
}
