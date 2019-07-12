package proyecto.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import proyecto.servicios.impl.Propiedad;

public class LoaderClase extends ClassLoader{

    public LoaderClase(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String name) throws ClassNotFoundException {
    	
    	
        if(!Propiedad.getInstance().getPropiedad("nubeDrive").equals(name) 
        		&& !Propiedad.getInstance().getPropiedad("nubes").equals(name)
        		&& !Propiedad.getInstance().getPropiedad("nubeDropBox").equals(name)
        		&& !Propiedad.getInstance().getPropiedad("nubeOneDrive").equals(name)
        		&& !Propiedad.getInstance().getPropiedad("propiedadesExtra").equals(name) )
                return super.loadClass(name);
       
        try {
            String url = "file:./src/test/resources/" + name + ".class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("proyecto." + name,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}