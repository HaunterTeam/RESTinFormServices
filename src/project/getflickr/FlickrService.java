package project.getflickr;

import org.json.JSONObject;

import project.Settings;
import project.utils.RequestHandler;

/**
 * Created by les on 19/01/15.
 */
public class FlickrService {
    private RequestHandler handler;

    public FlickrService(){
        handler = new RequestHandler(Settings.FLICKR_BASE_URL + Settings.FLICKR_BASE_PORT + Settings.FLICKR_BASE_PATH);
    }

    public Photo getPhotoFromTag(String tag) {
    	
    	String url = Settings.FLICKR_BASE_URL + Settings.FLICKR_BASE_PORT + Settings.FLICKR_BASE_PATH + tag;
    	
    	System.out.println("URL: " + url);
    	
        handler.set_url(url);
        JSONObject obj = new JSONObject(handler.getRequestResult());

        if(obj == null) { return null; }
        
        Photo photo = new Photo();
        photo.setDateAdded(obj.get("dateAdded") == null?obj.getString("dateAdded"):"");
        photo.setDescription(obj.get("description") == null?obj.getString("description"):"");
        photo.setId(obj.getString("id"));
        photo.setUrl(obj.getString("url"));
        photo.setTitle(obj.get("title") == null?obj.getString("title"):"");

        return photo;
    }
}