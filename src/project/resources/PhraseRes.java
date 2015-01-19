package project.resources;

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

import project.models.Phrase;

@Path("/phrase")
public class PhraseRes {

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
	public Phrase getPhrase(
                @QueryParam("bmi") double bmi,
                @QueryParam("bmiold") double bmiold,
                @QueryParam("weathertype") int weatherType) {
            
            int change = 0;
            if(bmiold != 0){
                if(bmi > bmiold)
                    change = 1;
                else
                    change = 2;
            }
            int bmiType = utils.BMIUtils.getLevelByBmi(bmi);
            System.err.println(bmiType +" "+change+" "+weatherType);
            
            return Phrase.getPhraseByWeatherAndByBmi(bmiType,change, weatherType);
            
	}
}
