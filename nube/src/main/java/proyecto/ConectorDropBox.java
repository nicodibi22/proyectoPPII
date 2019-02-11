package proyecto;

import java.io.IOException;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import proyecto.servicios.INube;


public class ConectorDropBox implements INube {

	public boolean conectar() {
		
		
		
		DbxAuthInfo authInfo;
        try {
            authInfo = DbxAuthInfo.Reader.readFromFile("");
        } catch (JsonReader.FileLoadException ex) {
            System.err.println("Error loading <auth-file>: " + ex.getMessage());            
            return false;
        }
		
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, "bEyHbKOELXAAAAAAAAAAHz47rOuzjOXM4JV9qQKcojq_yjAAld88ZzYoQGHTl5ml");

        // Get current account info
        FullAccount account;
		try {
			account = client.users().getCurrentAccount();
			System.out.println(account.getName().getDisplayName());
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean upload(String pathFile) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public String uploadId(String pathFile) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Enum<?> getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
