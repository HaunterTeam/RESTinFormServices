package project;

import project.getflickr.FlickrService;
import project.getflickr.Photo;
import project.getfood.FoodService;
import project.getphrase.PhraseService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App
{
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {    	
    	String protocol = Settings.BASE_PROTOCOL;
        String port = ":" + Settings.BASE_PORT;
        String hostname = InetAddress.getLocalHost().getHostAddress();
        String path = Settings.SERVICE_PATH;
        if (hostname.equals(Settings.BASE_URL))
        {
            hostname = "localhost";
        }

        //URI baseUrl = new URI(protocol + hostname + port + path); deploy version
        URI baseUrl = new URI(protocol + "localhost" + port + path);
        System.out.println("Starting HaunterTeam standalone HTTP server..");
        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");        
        /*PhraseService p = new PhraseService();
        System.out.println(p.getPhraseS(34, 30, 1, 0, 0));*/
        //FoodService service = new FoodService();
        //service.getFoodNutritionValues("pizza");
        FlickrService service = new FlickrService();
        service.getPhotoFromTag("pizza");

    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting SDE assignment REST services..");
        return new MyApplicationConfig();
    }
}