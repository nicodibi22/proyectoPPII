package proyecto.servicios;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;


public interface INube {

	/**
	 * Establece conexion con la "nube"
	 * @return
	 */
	public boolean conectar();
	
	/**
	 * Sube archivos a la nube, historial de movimientos en ese caso 
	 * @return
	 * @throws UnknownHostException 
	 * @throws IOException 
	 */
	public boolean upload(String pathFile) throws IOException;
	
	public String uploadId(String pathFile) throws IOException;
	
	/**
	 * Devuelve que tipo de servicio en la nube brinda
	 * @return
	 */
	
	public Enum<?> getTipo();
	
}
