package project.resources;


import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONObject;

import project.Settings;
import project.beans.NutritionalInfo;
import project.businesslogic.BusinessLogicService;
import project.getfacebookinfo.FacebookErrorException;
import project.getfacebookinfo.FacebookInfo;
import project.getfacebookinfo.FacebookService;
import project.getflickr.FlickrService;
import project.getflickr.Photo;
import project.getfood.Food;
import project.getfood.FoodService;
import project.utils.RequestHandler;
import document.ws.Person;

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
	public String getPhrase(
                @QueryParam("token") String token, @QueryParam("callback") String callback) throws IOException, JAXBException {
            
		// The JSONObject which will be sent to the frontend
		// If everything goes right, code = 200 and message = "Valid Request" 
		JSONObject result_json = new JSONObject();
    	JSONObject status_json = new JSONObject();
    	status_json.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_OK_REQ);
    	status_json.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, Settings.FB_OK_MESSAGE);
    	result_json.put(Settings.FB_JSON_OUT_STATUS_OBJ, status_json);
        
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
        
        // DBService service called
        double bmi = -1;
        try {
            // This version works with the database connection
            document.ws.People iPeople = RequestHandler.getInterface();
            Person p =  iPeople.readPerson(fi.getId(), token);
            if (p != null) {
                bmi = p.getLastBMI();
            } else {
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
        		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
            	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
            		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, "No Person Found for id = " + fi.getId());
            	System.err.println(result_json.toString());
            	return callback + "(" + result_json.toString() + ")";
            }
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
        String food = "";
        try {
        	food = bl.getFood(bmi);
        } catch (Exception general_excep) {
        	System.err.println("Exception raised in bl.getFood(): " + Settings.FB_ERR_REQ + ", " + general_excep.getMessage());
        	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
        		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
        	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
        		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, general_excep.getMessage());
        	System.err.println(result_json.toString());
        	return callback + "(" + result_json.toString() + ")";
        }
        

        //retrieve nutritional values about the suggested food
        FoodService service = new FoodService();
        Food foodObject = service.getFoodNutritionValues(food);
        if (foodObject == null) {
        	System.err.println("Exception raised in service.getFoodNutritionValues(): " + Settings.FB_ERR_REQ + ", " + "foodObject is null");
        	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
        		.put(Settings.FB_JSON_OUT_STATUS_CODE_ATTR, Settings.FB_ERR_REQ);
        	result_json.getJSONObject(Settings.FB_JSON_OUT_STATUS_OBJ)
        		.put(Settings.FB_JSON_OUT_STATUS_MESSAGE_ATTR, "No Food Found");
        	System.err.println(result_json.toString());
        	return callback + "(" + result_json.toString() + ")";
        }
        
        //Why not even a picture of the food?
        FlickrService flickrServices = new FlickrService();
        Photo photo = flickrServices.getPhotoFromTag(food);

        //Merge everything into a single object
        NutritionalInfo info = new NutritionalInfo();
        info.setFoodPhoto(photo);
        info.setSuggestedFood(foodObject);
        
//        JSONObject info_json = new JSONObject(info.toString());
//        info_json.put("flickr", photo);
//        info_json.put("food", foodObject);

        java.io.StringWriter sw = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(NutritionalInfo.class);
        
        System.err.println("BEFORE");
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("eclipselink.media.type", "application/json");
        marshaller.marshal(info, sw);
        
        System.err.println("AFTER");
        
        // Everything goes right!!
        result_json.put(Settings.FB_JSON_OUT_RESULT_OBJ, info);
        System.out.println("Output:");
        System.out.println(result_json.toString());
        return callback + "(" + result_json.toString() + ")";
    }
}