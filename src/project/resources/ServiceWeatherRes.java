/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.resources;

/**
 *
 * @author luca
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import project.Settings;
import project.beans.ActiWathMerge;
import project.businesslogic.BmiObj;
import project.businesslogic.BusinessLogicService;
import project.getfacebookinfo.FacebookErrorException;
import project.getfacebookinfo.FacebookInfo;
import project.getfacebookinfo.FacebookService;
import project.getphrase.PhraseService;
import project.getweather.Weather;
import project.getweather.WeatherService;
import project.models.Phrase;
import project.utils.RequestHandler;
import document.ws.People;
import document.ws.Person;

@Path("/weather")
public class ServiceWeatherRes {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@PersistenceUnit(unitName="introsde-jpa")
	EntityManager entityManager;

	@PersistenceContext(unitName = "introsde-jpa", type=PersistenceContextType.TRANSACTION)
	private EntityManagerFactory entityManagerFactory;
        
	@GET
    @Produces({"application/javascript"})
	public String getPhrase(
                @QueryParam("token") String token,@QueryParam("callback") String callback) throws IOException {
            
			// The JSONObject which will be sent to the frontend
			// If everything goes right, code = 200 and message = "Valid Request" 
			JSONObject result_json = new JSONObject();
        	JSONObject status_json = new JSONObject();
        	status_json.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_OK_REQ);
        	status_json.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, Settings.FB_OK_MESSAGE);
        	result_json.put(Settings.FB_JSON_OUT_STATUS_OBJ, status_json);
			
            System.err.println("Token:" + token);
            
            // FacebookService called
            FacebookService fb = new FacebookService();
            FacebookInfo fi = null;
            try {
            	fi = fb.getInfoByToken(token);
            } catch(FacebookErrorException fb_excep) {
            	System.err.println("Exception raised in FacebookService: " + fb_excep.getCode() + ", " + fb_excep.getMessage());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, fb_excep.getCode());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, fb_excep.getMessage());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }
            
            // WeatherService is called
            WeatherService ws = new WeatherService();
            ArrayList<Weather> wl = null;
            int w1 = -1, w2 = -1, w3 = -1;
            
            try {
            	wl = ws.getWeather(fi.getLocation());
                w1 = wl.get(0).getType();
                w2 = wl.get(1).getType();
                w3 = wl.get(2).getType();
            } catch(Exception general_excep) {
            	System.err.println("Exception raised in WeatherService: " + Settings.FB_ERR_REQ + ", " + general_excep.getMessage());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, general_excep.getMessage());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }
            
//        	double bmi = Measure.getLastBmi(idface);
//            double oldBmi=0;
//            
//            try{
//                oldBmi= Measure.getOldBmi(idface);
//            } catch(Exception e){}

            // DBService service called
            double bmi = -1;
            double bmiold = -1;
            try {
                // This version works with the database connection
                People iPeople = RequestHandler.getInterface();
                Person p =  iPeople.readPerson(fi.getId());
                bmi = p.getLastBMI();
                bmiold = p.getOldBMI();
            } catch (Exception general_excep) {
            	System.err.println("Exception raised in iPeople.readPerson(): " + Settings.FB_ERR_REQ + ", " + general_excep.getMessage());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, general_excep.getMessage());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }

            // BmiCalculatorService service called
            BusinessLogicService bl = new BusinessLogicService();
            BmiObj bmiobj = null;
            try {
            	bmiobj = bl.calculateBmiLvlAndChange(bmi, bmiold);
            } catch (Exception general_excep) {
            	System.err.println("Exception raised in bl.calculateBmiLvlAndChange(): " + Settings.FB_ERR_REQ + ", " + general_excep.getMessage());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, general_excep.getMessage());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }
            
            // PhraseService service is called
            PhraseService ps = new PhraseService();
            ArrayList<Phrase> ph = null;
            try {
            	ps.getPhraseS(bmiobj.getBmilvl(), bmiobj.getChange(), w1, w2, w3);
            } catch (Exception general_excep) {
            	System.err.println("Exception raised in bl.calculateBmiLvlAndChange(): " + Settings.FB_ERR_REQ + ", " + general_excep.getMessage());
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, general_excep.getMessage());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }
            
            // Merging all the data acquired
            ArrayList<ActiWathMerge> awm = new ArrayList<ActiWathMerge>();
            for (int i = 0; i < ph.size(); i++) {
                awm.add(new ActiWathMerge(ph.get(i), wl.get(i)));
            }
            
            System.err.println(awm.get(0).getActivityplan().getActivity());
            System.err.println(awm.toString());
            
            // Everything goes right!!
            result_json.put(Settings.FB_JSON_OUT_RESULT_OBJ, awm);
            return callback + "(" + result_json.toString() + ")";
	}
}