package proyecto.servicios.impl;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;

import proyecto.servicios.IRedSocial;

public class RedSocialInstagram implements IRedSocial {

	@Override
	public boolean estaAutenticado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Autenticar() {
		// TODO Auto-generated method stub
		InstagramService service = new InstagramAuthService()
				.apiKey("your_client_id")
				.apiSecret("your_client_secret")
				.callback("your_callback_url")     
				.build();
	}

	@Override
	public void Publicar(String comentario, String pathArchivo) {
		// TODO Auto-generated method stub
		
	}

}
