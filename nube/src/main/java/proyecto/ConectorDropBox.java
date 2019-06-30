package proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import proyecto.servicios.INube;


public class ConectorDropBox implements INube {

	
	DbxClientV2 client;
	DbxAuthInfo authInfo;
	
	
	public boolean conectar() {
		
		String credenciales = NubePropiedades.getInstance().getPropiedad("DROPBOX_CLIENT_SECRET_DIR");
		boolean result = false;
        try {
            authInfo = DbxAuthInfo.Reader.readFromFile(credenciales);
        } catch (JsonReader.FileLoadException ex) {
            System.err.println("Error loading <auth-file>: " + ex.getMessage());            
            return result;
        }
		
		try {
			if(authorize() != null)        
				result = true;
		} catch (DbxException e) {			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean upload(String pathFile) throws IOException {
		
		try {
			if(authorize() == null)        
				return false;
		} catch (DbxException e1) {
			e1.printStackTrace();
			return false;
		}
		
		try (InputStream in = new FileInputStream(pathFile)) {
            String[] splitPathFile = pathFile.split("\\");
            String fileName = splitPathFile[splitPathFile.length - 1]; 
			FileMetadata metadata = client.files().uploadBuilder(fileName)
                .uploadAndFinish(in);
			if (metadata.getId() != null && !metadata.getId().equals("")) {
				return true;
			}            
        } catch (DbxException e) {
        	e.printStackTrace();
        }
		
		return false;
	}

	public String uploadId(String pathFile) throws IOException {
		String id ="";
		try (InputStream in = new FileInputStream("test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                .uploadAndFinish(in);
            id = metadata.getId();
        } catch (DbxException e) {
        	e.printStackTrace();
        }
		
		return id;
	}

	public Enum<?> getTipo() {
		return NubeEnum.DROPBOX;
	}

	private DbxClientV2 authorize() throws DbxException {
		if (authInfo == null) {
			throw new DbxException("No se cargaron las credenciales.");
		}
		DbxRequestConfig config = DbxRequestConfig.newBuilder("ProyectoPPII").build();
        return new DbxClientV2(config, authInfo.getAccessToken());
	}

	@Override
	public String uploadAndShare(String pathFile, String user) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
