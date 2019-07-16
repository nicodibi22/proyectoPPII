package proyecto.servicios.impl;

import com.thoughtworks.xstream.XStream;

import proyecto.servicios.IConversor;

public class ConvertToXML implements IConversor {

	@Override
	public String convert(Object o) {
		XStream xstream = new XStream();  
        xstream.ignoreUnknownElements();
        return xstream.toXML(o);
	}

}
