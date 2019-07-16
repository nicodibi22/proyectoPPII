package proyecto.servicios.impl;

import com.google.gson.Gson;

import proyecto.servicios.IConversor;

public class ConvertToJson implements IConversor {

	@Override
	public String convert(Object o) {
		
		Gson gson = new Gson();		
		return gson.toJson(o);
	}

}
