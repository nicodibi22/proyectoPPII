package proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.sharing.AddFileMemberErrorException;
import com.dropbox.core.v2.sharing.MemberSelector;
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
			authorize();
		} catch (DbxException e1) {
			e1.printStackTrace();
			return false;
		}
		
		try (InputStream in = new FileInputStream(pathFile)) {
            String[] splitPathFile = pathFile.split("/");
            String fileName = splitPathFile[splitPathFile.length - 1]; 
			FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                .uploadAndFinish(in);
			if (metadata.getId() != null && !metadata.getId().equals("")) {
				return true;
			}            
        } catch (DbxException e) {
        	e.printStackTrace();
        }
		
		return false;
	}

	public Enum<?> getTipo() {
		return NubeEnum.DROPBOX;
	}

	private DbxClientV2 authorize() throws DbxException {
		if (authInfo == null) {
			throw new DbxException("No se cargaron las credenciales.");
		}
		DbxRequestConfig config = DbxRequestConfig.newBuilder("ProyectoPPII").build();
        client = new DbxClientV2(config, authInfo.getAccessToken());
        return client;
	}

	@Override
	public boolean uploadAndShare(String pathFile, String user) throws IOException {
		
		String fileName = "";
		try (InputStream in = new FileInputStream(pathFile)) {
            String[] splitPathFile = pathFile.split("/");
            fileName = splitPathFile[splitPathFile.length - 1]; 
			fileName = fileName + new Date().getTime();
            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                .uploadAndFinish(in);
			if (metadata.getId().equals("")) {
				return false;
			}
        } catch (DbxException e) {
        	e.printStackTrace();
        	return false;
        }
		
		List<MemberSelector> newMembers = new ArrayList<MemberSelector>();
		MemberSelector newMember = MemberSelector.email(user);
		newMembers.add(newMember);
		
		try {
			client.sharing().addFileMember("/" + fileName, newMembers);
		} catch (AddFileMemberErrorException e1) {
			
			return false;
		} catch (DbxException e1) {
			return false;
		}
		
		return true;
	}
	
}
