package project.resources;

import project.businesslogic.BmiObj;
import project.businesslogic.BusinessLogicService;
import project.getfacebookinfo.FacebookInfo;
import project.getfacebookinfo.FacebookService;
import project.getflickr.FlickrService;
import project.getflickr.Photo;
import project.getfood.Food;
import project.getfood.FoodService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by les on 20/01/15.
 */
@Path("/food")
public class ServiceFoodRes {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({"application/javascript"})
	public /*ArrayList<ActiWathMerge>*/ String getPhrase(
                @QueryParam("token") String token, @QueryParam("callback") String callback) throws IOException {
            
        FacebookService fb = new FacebookService();
        FacebookInfo fi = fb.getInfoByToken(token);

        double bmi = 3;
        double oldBmi = 3;
            
         // BL
        BusinessLogicService bl = new BusinessLogicService();
        BmiObj bmiobj = bl.calculateBmiLvlAndChange(bmi, oldBmi);
        
        
            
            
    	
    	
        //[TODO Mirko] add other food
        //initial random food
        List<String> foods = new ArrayList<>(Arrays.asList("pizza", "bacon", "salad", "potato", "roast", "pasta", "pasta carbonara", "noodles"));
        Random rmd = new Random();

        String food = foods.get(rmd.nextInt(foods.size() - 1));

        //The process will now start

        FoodService service = new FoodService();
        Food foodObject = service.getFoodNutritionValues(food);

        FlickrService flickrServices = new FlickrService();
        Photo photo = flickrServices.getPhotoFromTag(food);
        return "";
    }
}
