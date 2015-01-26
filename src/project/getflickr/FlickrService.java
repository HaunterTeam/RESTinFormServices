package project.getflickr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import project.Settings;
import project.utils.RequestHandler;

/**
 * Created by les on 19/01/15.
 */
public class FlickrService {

    public FlickrService() { }

    public Photo getPhotoFromTag(String tag) {
    	
    	String url = Settings.BASE_PROTOCOL + Settings.FLICKR_BASE_URL + Settings.FLICKR_BASE_PORT + Settings.FLICKR_BASE_PATH + tag;
    	
    	System.out.println("URL: " + url);
    	Photo photo = new Photo();
    	
    	try {
        	
        	URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(Settings.REQ_TYPE);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();
            
            JSONObject flickr_serv_response = new JSONObject(response.toString());
            
            System.err.println(flickr_serv_response.toString());
            
            
            photo.setDateAdded(flickr_serv_response
            		.get(Settings.FLICKR_JSON_DATE_ATTR) == null ? flickr_serv_response.getString(Settings.FLICKR_JSON_DATE_ATTR): "");
            photo.setDescription(flickr_serv_response
            		.get(Settings.FLICKR_JSON_DESCRIPTION_ATTR) == null ? flickr_serv_response.getString(Settings.FLICKR_JSON_DESCRIPTION_ATTR): "");
            photo.setId(flickr_serv_response.getString(Settings.FLICKR_JSON_ID_ATTR));
            photo.setUrl(flickr_serv_response.getString(Settings.FLICKR_JSON_URL_ATTR));
            photo.setTitle(flickr_serv_response
            		.get(Settings.FLICKR_JSON_TITLE_ATTR) == null ? flickr_serv_response.getString(Settings.FLICKR_JSON_TITLE_ATTR) : "");
	       
        } catch (Exception excep) {
        	
        	System.err.println("Exception catched in " + this.getClass().getName() + " " + excep.toString());
        	return null;
        }
        return photo;
    }
}