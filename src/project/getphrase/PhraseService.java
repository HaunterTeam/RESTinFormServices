/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.getphrase;

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
    
    public static final String REQ_TYPE = "GET";

    public PhraseService() {}
    
    public ArrayList<Phrase> getPhraseS(int bmilvl, int change,int w1,int w2,int w3) throws MalformedURLException, IOException, JSONException{
        
        String url = "";
        url += Settings.BASE_PROTOCOL + Settings.PH_BASE_URL + Settings.PH_BASE_PORT + Settings.PH_BASE_PATH;
        url += "?" + Settings.PH_PARAM_BMI_LEVEL + "=" + bmilvl;
        url += "&" + Settings.PH_PARAM_CHANGE + "=" + change;
        url += "&" + Settings.PH_PARAM_WEATHER_TYPE_1 + "=" + w1;
        url += "&" + Settings.PH_PARAM_WEATHER_TYPE_2 + "=" + w2;
        url += "&" + Settings.PH_PARAM_WEATHER_TYPE_3 + "=" + w3;
        
        System.out.println("URL: " + url);
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(REQ_TYPE);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        
        ArrayList<Phrase> retPhs = new ArrayList<Phrase>();
        JSONArray phs = new JSONArray(response.toString());
        for (int i = 0; i < phs.length(); i++) {
            JSONObject o = phs.getJSONObject(i);
            Phrase p = new Phrase();
            p.setIdphrase(o.getInt(Settings.PH_JSON_ID_PHRASE));
            p.setPhrase(o.getString(Settings.PH_JSON_PHRASE));
            p.setWeathertype(o.getInt(Settings.PH_JSON_WEATHER_TYPE));
            p.setBmirange(o.getInt(Settings.PH_JSON_BMIRANGE));
            p.setChange(o.getInt(Settings.PH_JSON_CHANGE));
            p.setActivity(o.getString(Settings.PH_JSON_ACTIVITY));
            retPhs.add(p);
        }
        return retPhs;
    }
}