package proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.sharing.AccessLevel;
import com.dropbox.core.v2.sharing.AddFileMemberBuilder;
import com.dropbox.core.v2.sharing.AddFileMemberErrorException;
import com.dropbox.core.v2.sharing.AddFolderMemberBuilder;
import com.dropbox.core.v2.sharing.AddFolderMemberErrorException;
import com.dropbox.core.v2.sharing.AddMember;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.sharing.DbxUserSharingRequests;
import com.dropbox.core.v2.sharing.FileMemberActionResult;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

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
        client = new DbxClientV2(config, authInfo.getAccessToken());
        return client;
	}

	@Override
	public boolean uploadAndShare(String pathFile, String user) throws IOException {
		// TODO Auto-generated method stub
		
		String id ="";
		/*CreateFolderResult metadata;
		try {
			metadata = client.files().createFolderV2("/prueba");
			id = metadata.getMetadata().getId();
		} catch (CreateFolderErrorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        */
			
		try (InputStream in = new FileInputStream(pathFile)) {
            String[] splitPathFile = pathFile.split("/");
            String fileName = splitPathFile[splitPathFile.length - 1]; 
			FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                .uploadAndFinish(in);
			id = metadata.getId();
        } catch (DbxException e) {
        	e.printStackTrace();
        }
		
		List<MemberSelector> newMembers = new ArrayList<MemberSelector>();
		MemberSelector newMember = MemberSelector.email(user);
		newMembers.add(newMember);
		
		try {
			client.sharing().addFileMember("/test.txt", newMembers);
		} catch (AddFileMemberErrorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		try {
			SharedLinkMetadata meta = client.sharing().createSharedLinkWithSettings("/test");
			System.out.println( meta.getUrl());
		} catch (CreateSharedLinkWithSettingsErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//adsa.withCustomMessage("hfiosafhas");
		/*
		AddMember miembro = new AddMember(MemberSelector.email(user), AccessLevel.OWNER);
		List<AddMember> members = new ArrayList<AddMember>();
		members.add(miembro);
		try {
			client.sharing().addFolderMember(id, members);
		} catch (AddFolderMemberErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
	
}
