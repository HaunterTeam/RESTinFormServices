package project.resources;

import document.ws.People;
import document.ws.Person;
import org.json.JSONObject;
import project.beans.NutritionalInfo;
import project.businesslogic.BusinessLogicService;
import project.getflickr.FlickrService;
import project.getflickr.Photo;
import project.getfood.Food;
import project.getfood.FoodService;
import project.utils.RequestHandler;

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
    public String getFoodSuggestion(@QueryParam("token") String token,@QueryParam("callback") String callback) throws IOException{

        //[TODO Mirko] add authentication
        
        //Retrieve the user's information
        People iPeople = RequestHandler.getInterface();
        Person p = iPeople.readPerson((long)1);

        //calculate bmi and retrieve an appropriate food based on the bmi
        double bmi = p.getLastBMI();
        BusinessLogicService bsService = new BusinessLogicService();
        String food = bsService.getFood(bmi);

        //retrieve nutritional values about the suggested food
        FoodService service = new FoodService();
        Food foodObject = service.getFoodNutritionValues(food);

        //Why not even a picture of the food?
        FlickrService flickrServices = new FlickrService();
        Photo photo = flickrServices.getPhotoFromTag(food);

        //Merge everything into a single object
        NutritionalInfo info = new NutritionalInfo();
        info.setFoodPhoto(photo);
        info.setSuggestedFood(foodObject);

        //...and jsonize it!!
        JSONObject bb = new JSONObject();
        bb.put("result",info);
        String ret = callback + "(" + bb.toString() + ")";

        return ret;
    }
}
