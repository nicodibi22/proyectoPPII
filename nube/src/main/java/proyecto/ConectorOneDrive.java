package proyecto;

import java.io.IOException;
import org.apache.commons.lang3.NotImplementedException;
import de.tuberlin.onedrivesdk.OneDriveException;
import de.tuberlin.onedrivesdk.OneDriveFactory;
import de.tuberlin.onedrivesdk.OneDriveSDK;
import de.tuberlin.onedrivesdk.common.OneDriveScope;
import proyecto.servicios.INube;

public class ConectorOneDrive implements INube {

	@Override
	public boolean conectar() {
		
		OneDriveSDK api = OneDriveFactory.createOneDriveSDK("18e8d27e-bc2a-460b-badf-c779932487c8", "xyGY09:avpbzLGCWO244-)_", "msal18e8d27e-bc2a-460b-badf-c779932487c8://auth"
                , OneDriveScope.READWRITE);
		//String hola = api.getAuthenticationURL();
		try {
			api.authenticate("18e8d27e-bc2a-460b-badf-c779932487c8");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (OneDriveException e1) {
			e1.printStackTrace();
		}
		System.out.println(api.isAuthenticated());
        
        api.startSessionAutoRefresh();
        return api.isAuthenticated();
    }

	@Override
	public boolean upload(String pathFile) throws IOException {
		
		throw new NotImplementedException("No se ha implementado la nube OneDrive.");
		
	}

	

	@Override
	public Enum<?> getTipo() {
		
		return NubeEnum.ONEDRIVE;
	}

	@Override
	public boolean uploadAndShare(String pathFile, String user) throws IOException {
		
		throw new NotImplementedException("No se ha implementado la nube OneDrive.");
	}

}
