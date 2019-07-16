package proyecto.servicios.impl;

import proyecto.servicios.IConversor;
import proyecto.servicios.impl.ApiManager.FormatoRespuesta;

public class ConversorFactory {

	public static IConversor getConversor(FormatoRespuesta formato) {
		if(formato.equals(FormatoRespuesta.JSON))
			return new ConvertToJson();
		else
			return new ConvertToXML();
	}
}
