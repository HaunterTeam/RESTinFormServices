/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.getweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.Settings;

/**
 *
 * @author luca
 */
public class WeatherService {
    
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    public static final String PARAM = "&units=metric&cnt=";
    
    public static final int BASE_DAY = 3;
    
    public static final String JSON_LIST = "list";
    public static final String JSON_WEATHER = "weather";
    public static final String JSON_TEMPERATURE = "temp";
    public static final String JSON_TEMPERATURE_AVERAGE = "eve";
    public static final String JSON_MAIN_WEATHER = "main";

    public WeatherService() { }
    
    public ArrayList<Weather> getWeather(String location) throws MalformedURLException, IOException, JSONException{
        return getWeather(location,BASE_DAY);
    }
    
    public ArrayList<Weather> getWeather(String location,int days) throws MalformedURLException, IOException, JSONException{
        
        String url  = BASE_URL + location + PARAM + days;
        
        System.out.println("URL: " + url);
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(Settings.REQ_TYPE);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        
        JSONObject o = new JSONObject(response.toString());
        JSONArray ja = o.getJSONArray(JSON_LIST);
        
        ArrayList<Weather> listWeather = new ArrayList<Weather>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject weather = ja.getJSONObject(i);
            
            JSONArray wd = weather.getJSONArray(JSON_WEATHER);
            JSONObject jo = wd.getJSONObject(0);
            String weatLoc = jo.getString(JSON_MAIN_WEATHER);
            
            JSONObject alltemps = weather.getJSONObject(JSON_TEMPERATURE);
            double tempAverage = alltemps.getDouble(JSON_TEMPERATURE_AVERAGE);
            
            listWeather.add(new Weather(location, tempAverage, weatLoc));
        }
        return listWeather;
    }
}