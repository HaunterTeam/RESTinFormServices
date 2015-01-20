package project;

import document.ws.People;
import document.ws.Person;
import project.getflickr.FlickrService;
import project.getflickr.Photo;
import project.getfood.Food;
import project.getfood.FoodService;
import project.getphrase.PhraseService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import project.utils.RequestHandler;

public class App
{
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {    	
    	String protocol = Settings.BASE_PROTOCOL;
        String port = ":" + Settings.BASE_PORT;
        String hostname = InetAddress.getLocalHost().getHostAddress();
        String path = Settings.BASE_PATH;
        if (hostname.equals(Settings.BASE_URL))
        {
            hostname = "localhost";
        }

        //URI baseUrl = new URI(protocol + hostname + port + path); deploy version
        URI baseUrl = new URI(protocol + "localhost" + port + path);
        System.out.println("Starting HaunterTeam standalone HTTP server..");
        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");


        System.out.println("Done");


    }

    public static ResourceConfig createApp() {
    	System.out.println("Starting SDE assignment REST services..");
        return new MyApplicationConfig();
    }
}