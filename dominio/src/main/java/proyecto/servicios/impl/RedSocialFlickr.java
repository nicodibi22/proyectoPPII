package proyecto.servicios.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

import proyecto.servicios.RedSocial;

public class RedSocialFlickr implements RedSocial {

	Flickr f;
	
	public RedSocialFlickr() {
		
	}

	@Override
	public boolean estaAutenticado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void autenticar() {
		String apiKey = "3653bef9cb08024969382485fc1631d7";
		String sharedSecret = "95734e484043e6b6";
		f = new Flickr(apiKey, sharedSecret, new REST());		
	}

	@Override
	public void publicar(String comentario, String pathArchivo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFotos(String tag) {
		PhotosInterface photos = f.getPhotosInterface();
        SearchParameters params = new SearchParameters();
        try {
			params.setMedia("photos");
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // One of "photos", "videos" or "all"
        params.setExtras(Stream.of("media").collect(Collectors.toSet()));
        params.setText("RenunciaCubillos");
        PhotoList<Photo> results;
		try {
			results = photos.search(params, 5, 0);
			results.forEach(p ->
	        {
	            System.out.println(String.format("Title: %s", p.getTitle()));
	            System.out.println(String.format("Media: %s", p.getMedia()));
	            //System.out.println(String.format("Original Video URL: %s", p.getVideoOriginalUrl()));
	        });
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		
	}
}
