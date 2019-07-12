package proyecto;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import de.tuberlin.onedrivesdk.OneDriveException;
import de.tuberlin.onedrivesdk.OneDriveFactory;
import de.tuberlin.onedrivesdk.OneDriveSDK;
import de.tuberlin.onedrivesdk.common.ConcreteOneDriveSDK;
import de.tuberlin.onedrivesdk.common.OneDriveCredentials;
import de.tuberlin.onedrivesdk.common.OneDriveScope;
import de.tuberlin.onedrivesdk.drive.ConcreteOneDrive;
import de.tuberlin.onedrivesdk.drive.OneDrive;
import de.tuberlin.onedrivesdk.folder.OneFolder;
import de.tuberlin.onedrivesdk.uploadFile.ConcreteOneUploadFile;
import de.tuberlin.onedrivesdk.uploadFile.OneUploadFile;
import proyecto.servicios.INube;

public class ConectorOneDrive implements INube {

	@Override
	public boolean conectar() {
		// TODO Auto-generated method stub
		
		OneDriveSDK api = OneDriveFactory.createOneDriveSDK("18e8d27e-bc2a-460b-badf-c779932487c8", "xyGY09:avpbzLGCWO244-)_", "msal18e8d27e-bc2a-460b-badf-c779932487c8://auth"
                , OneDriveScope.READWRITE);
		String hola = api.getAuthenticationURL();
		try {
			api.authenticate("18e8d27e-bc2a-460b-badf-c779932487c8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OneDriveException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(api.isAuthenticated());
        
        api.startSessionAutoRefresh();
        return api.isAuthenticated();
    }

	@Override
	public boolean upload(String pathFile) throws IOException {
		
		return false;
	}

	

	@Override
	public Enum<?> getTipo() {
		
		return null;
	}

	@Override
	public boolean uploadAndShare(String pathFile, String user) throws IOException {
		
		return false;
	}

}
