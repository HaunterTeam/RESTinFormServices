package project.getfood;

import org.json.JSONObject;

import project.Settings;
import project.utils.RequestHandler;
/**
 * Created by les on 19/01/15.
 */

public class FoodService {

    private RequestHandler handler;

    public FoodService(){
        handler = new RequestHandler(Settings.FOOD_BASE_URL + Settings.FOOD_BASE_PORT + Settings.FOOD_BASE_PATH);
    }

    public Food getFoodNutritionValues(String food){
    	
    	String url = "";
    	url += Settings.FOOD_BASE_URL + Settings.FOOD_BASE_PORT + Settings.FOOD_BASE_PATH;
    	url += "?" + Settings.FOOD_PARAM + "=" + food;
    	
    	System.out.println("URL: " + url);

        handler.set_url(url);
        JSONObject obj = new JSONObject(handler.getRequestResult());

        if(obj == null) { return null; }
        
        Food result = new Food();
        result.setName(obj.getString("item_name"));
        result.setBrand(obj.getString("brand_name"));
        result.setCalories((float)obj.getDouble("nf_calories"));
        result.setFat((float)obj.getDouble("nf_total_fat"));

        return result;
    }
}