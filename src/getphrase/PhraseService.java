/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getphrase;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import getweather.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javassist.compiler.ast.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.NameValuePair;
import project.Settings;
import project.models.Phrase;

/**
 *
 * @author luca
 */
public class PhraseService {
    public static final String BASE_SERVICE = "phrase";
    public static final String BASE_URL = Settings.BASE_PROTOCOL + 
            Settings.BASE_URL + Settings.BASE_PORT + Settings.SERVICE_PATH;
    
    public static final String REQ_TYPE = "GET";

    public PhraseService() {}
    
    public Phrase getPhrase(double bmi,double oldbmi,int weather) throws MalformedURLException, IOException, JSONException{
        
        String url  = BASE_URL + BASE_SERVICE;
        
        url += "?bmi=" + bmi + "&oldbmi=" + oldbmi + "&weathertype=" + weather;
        System.err.println(url);
        
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(REQ_TYPE);
        
        int responseCode = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        
        System.err.println(response.toString());
        /*JSONObject o = new JSONObject(response.toString());
        JSONArray ja = o.getJSONArray(JSON_LIST);
        
        ArrayList<Weather> listWeather = new ArrayList();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject weather = ja.getJSONObject(i);
            
            JSONArray wd = weather.getJSONArray(JSON_WEATHER);
            JSONObject jo = wd.getJSONObject(0);
            String weatLoc = jo.getString(JSON_MAIN_WEATHER);
            
            JSONObject alltemps = weather.getJSONObject(JSON_TEMPERATURE);
            double tempAverage = alltemps.getDouble(JSON_TEMPERATURE_AVERAGE);
            
            listWeather.add(new Weather(location, tempAverage, weatLoc));
        }*/
        
        return null;
    }
    
}
