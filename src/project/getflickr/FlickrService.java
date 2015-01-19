package project.getflickr;

import org.json.JSONObject;
import project.utils.RequestHandler;

import java.util.Objects;

/**
 * Created by les on 19/01/15.
 */
public class FlickrService {
    public static final String BASE_URL = "http://95.85.59.245:9091/flickr/";
    private RequestHandler handler;

    public FlickrService(){
        handler = new RequestHandler(BASE_URL);
    }

    public Photo getPhotoFromTag(String tag){
        handler.set_url(BASE_URL+tag);
        JSONObject obj = new JSONObject(handler.getRequestResult());

        if(obj == null)
            return null;
        Photo photo = new Photo();

        photo.setDateAdded(obj.get("dateAdded") == null?obj.getString("dateAdded"):"");
        photo.setDescription(obj.get("description") == null?obj.getString("description"):"");
        photo.setId(obj.getString("id"));
        photo.setUrl(obj.getString("url"));
        photo.setTitle(obj.get("title") == null?obj.getString("title"):"");

        return photo;
    }
}
