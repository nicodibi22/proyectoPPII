package proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.auth.DbxUserAuthRequests;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;

import proyecto.servicios.INube;


public class ConectorDropBox implements INube {

	
	DbxClientV2 client;
	DbxAuthInfo authInfo;
	
	
	public boolean conectar() {
		
		
		boolean result = false;
        try {
            authInfo = DbxAuthInfo.Reader.readFromFile("C:\\Users\\Nico\\eclipse-workspace\\parent-projectpp2\\nube\\src\\main\\java\\proyecto\\credentials.json");
        } catch (JsonReader.FileLoadException ex) {
            System.err.println("Error loading <auth-file>: " + ex.getMessage());            
            return result;
        }
		
		if(authorize() != null)        
			result = true;
		return result;
	}

	public boolean upload(String pathFile) throws IOException {
		
		if(authorize() == null)        
			return false;
		
		try (InputStream in = new FileInputStream("C:\\Users\\Nico\\eclipse-workspace\\parent-projectpp2\\nube\\src\\main\\java\\proyecto\\test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                .uploadAndFinish(in);
            return true;
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

	private DbxClientV2 authorize() {
		DbxRequestConfig config = DbxRequestConfig.newBuilder("ProyectoPPII").build();
        return new DbxClientV2(config, authInfo.getAccessToken());
	}
	
}
