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
		//return false;
		openWebpage(api.getAuthenticationURL());

        //intercepts redirect end automatically enters the oAuth Code
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8082);
            while (!api.isAuthenticated()) {
                Socket s = serverSocket.accept();
                BufferedReader bs = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String line;
                while ((line = bs.readLine()) != null) {
                    Matcher m = Pattern.compile("\\?code=([^ ]+) HTTP").matcher(line);
                    if (m.find()) {
                    	
                        try {
							api.authenticate(m.group(1));
						} catch (OneDriveException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        /*OutputStream os = s.getOutputStream();
                        os.write(new String(html_response).getBytes());
                        os.close();*/
                        break;
                    }
                }

                s.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
			OneFolder currentFolder = api.getRootFolder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OneDriveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        api.startSessionAutoRefresh();
        return api.isAuthenticated();
    }

    private static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {}
        }
    }

    private static void openWebpage(String url) {
        try {
            openWebpage(new URL(url).toURI());
        } catch (Exception e) {}
    }

	@Override
	public boolean upload(String pathFile) throws IOException {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public String uploadId(String pathFile) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enum<?> getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

}
