/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getphrase;

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
import project.models.Phrase;

/**
 *
 * @author luca
 */
public class PhraseService {
    public static final String BASE_SERVICE = "phrase";
    public static final String PHRASE_URL = "";
    public static final String BASE_PORT = "8010";
    public static final String SERVICE_PATH = "/phrase-service/";
    
    
    
    public static final String BASE_URL = Settings.BASE_PROTOCOL + 
    		Settings.BASE_URL + ":" + BASE_PORT + SERVICE_PATH;
    
    public static final String REQ_TYPE = "GET";

    public PhraseService() {}
    
    public ArrayList<Phrase> getPhraseS(double bmi,double oldbmi,int w1,int w2,int w3) throws MalformedURLException, IOException, JSONException{
        
        String url  = BASE_URL + BASE_SERVICE;
        
        url += "?bmi=" + bmi + "&bmiold=" + oldbmi + "&wt1=" + w1 + "&wt2=" + w2 + "&wt3=" + w3;
        
        
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
        
        
        ArrayList<Phrase> retPhs = new ArrayList<>();
        JSONArray phs = new JSONArray(response.toString());
        for (int i = 0; i < phs.length(); i++) {
            JSONObject o = phs.getJSONObject(i);
            Phrase p = new Phrase();
            p.setIdphrase(o.getInt("idphrase"));
            p.setPhrase(o.getString("phrase"));
            p.setWeathertype(o.getInt("weathertype"));
            p.setBmirange(o.getInt("bmirange"));
            p.setChange(o.getInt("change"));
            p.setActivity(o.getString("activity"));
            retPhs.add(p);
        }
        
        return retPhs;
    }
    
}
