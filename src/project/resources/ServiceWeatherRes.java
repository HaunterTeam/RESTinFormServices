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
import getphrase.PhraseService;
import getweather.Weather;
import getweather.WeatherService;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import project.models.Measure;
import project.models.Phrase;
import beans.ActiWathMerge;

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
        @Produces({ MediaType.APPLICATION_JSON})
	public ArrayList<ActiWathMerge> getPhrase(
                @QueryParam("token") int tokenPerson) throws IOException {
            
            int idface = 3;
            
            WeatherService ws = new WeatherService();

            ArrayList<Weather> wl = ws.getWeather("Peio");
            int w1 = wl.get(0).getType();
            int w2 = wl.get(1).getType();
            int w3 = wl.get(2).getType();
            
            double bmi = Measure.getLastBmi(idface);
            double oldBmi=0;
            
            try{
                oldBmi= Measure.getOldBmi(idface);
            }catch(Exception e){}
            
            PhraseService ps = new PhraseService();
            ArrayList<Phrase> ph = ps.getPhraseS(bmi, oldBmi, w1, w2, w3);
            
            ArrayList<ActiWathMerge> awm = new ArrayList<>();
            for (int i = 0; i < ph.size(); i++) {
                awm.add(new ActiWathMerge(ph.get(i), wl.get(i)));
            }
            
            //WeatherPlan wp = new WeatherPlan(ph, wl);
            
            return awm;
	}
}