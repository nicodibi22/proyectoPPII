package proyecto;

import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.model.File;

public class ReaderDrive {
	
	public static List<File> leerArchivosDrive(ConectorDrive drive) throws IOException{
		return drive.authorize().files().list().execute().getFiles();
	}

}