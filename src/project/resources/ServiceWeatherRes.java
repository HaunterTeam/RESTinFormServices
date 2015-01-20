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
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.json.JSONArray;
import org.json.JSONObject;

import project.beans.ActiWathMerge;
import project.businesslogic.BmiObj;
import project.businesslogic.BusinessLogicService;
import project.getfacebookinfo.FacebookInfo;
import project.getfacebookinfo.FacebookService;
import project.getphrase.PhraseService;
import project.getweather.Weather;
import project.getweather.WeatherService;
import project.models.Measure;
import project.models.Phrase;

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
	public /*ArrayList<ActiWathMerge>*/ String getPhrase(
                @QueryParam("token") String token,@QueryParam("callback") String callback) throws IOException {
            
            System.err.println(token);
            int idface = 3;
            
            FacebookService fb = new FacebookService();
            FacebookInfo fi = fb.getInfoByToken(token);
            
            WeatherService ws = new WeatherService();

            ArrayList<Weather> wl = ws.getWeather(fi.getLocation());
            int w1 = wl.get(0).getType();
            int w2 = wl.get(1).getType();
            int w3 = wl.get(2).getType();
            double bmi = Measure.getLastBmi(idface);
            double oldBmi=0;
            
            try{
                oldBmi= Measure.getOldBmi(idface);
            }catch(Exception e){}
            
            // BL
            BusinessLogicService bl = new BusinessLogicService();
            BmiObj bmiobj = bl.calculateBmiLvlAndChange(bmi, oldBmi);
            
            PhraseService ps = new PhraseService();
            ArrayList<Phrase> ph = ps.getPhraseS(bmiobj.getBmilvl(), bmiobj.getChange(), w1, w2, w3);
            System.err.println("after ph");
            
            ArrayList<ActiWathMerge> awm = new ArrayList<>();
            for (int i = 0; i < ph.size(); i++) {
                awm.add(new ActiWathMerge(ph.get(i), wl.get(i)));
            }
            System.err.println(awm.get(0).getActivityplan().getActivity());
//            WeatherPlan wp = new WeatherPlan(ph, wl);
            System.err.println(awm.toString());
            
            /// HOT FIX
            JSONObject bb = new JSONObject();
            bb.put("result",awm);
            String ret = callback + "(" + bb.toString() + ")";
            System.err.println(ret);
            return ret;
	}
}