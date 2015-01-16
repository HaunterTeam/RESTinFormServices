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
import getweather.Weather;
import getweather.WeatherService;
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
        @Produces({ MediaType.APPLICATION_JSON})
	public ArrayList<Weather> getPhrase(
                @QueryParam("token") int tokenPerson) throws IOException {
            
            int idface = 3;
            
            WeatherService ws = new WeatherService();
            ArrayList<Weather> wl = ws.getWeather("Lima");
            int wtype = wl.get(0).getType();
            double bmi = Measure.getLastBmi(idface);
            double oldBmi=0;
            
            try{
                oldBmi= Measure.getOldBmi(idface);
            }catch(Exception e){}
            
            System.err.println(bmi +" " + oldBmi);
            PhraseService ps = new PhraseService();
            System.err.println(ps.getPhrase(bmi, oldBmi, wtype));
            
            return wl;
	}
}
