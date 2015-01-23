package project.getfood;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import project.Settings;
import project.getfacebookinfo.FacebookErrorException;
import project.getfacebookinfo.FacebookInfo;
import project.utils.RequestHandler;
/**
 * Created by les on 19/01/15.
 */

public class FoodService {

    public FoodService() { }

    public Food getFoodNutritionValues(String food) {
    	
    	String url = Settings.BASE_PROTOCOL + Settings.FOOD_BASE_URL + Settings.FOOD_BASE_PORT + Settings.FOOD_BASE_PATH;
    	url += "?" + Settings.FOOD_PARAM + "=" + food;
    	
    	System.out.println("URL: " + url);
    	Food result = new Food();
    	
    	try {
        	
        	URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(Settings.REQ_TYPE);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();
            
            JSONObject food_serv_response = new JSONObject(response.toString());
            
            System.err.println(food_serv_response.toString());
            
            result.setName(food_serv_response.getString(Settings.FOOD_JSON_ITEM_NAME_ATTR));
            result.setBrand(food_serv_response.getString(Settings.FOOD_JSON_BRAND_NAME_ATTR));
            result.setCalories(food_serv_response.getDouble(Settings.FOOD_JSON_CALORIES_ATTR));
            result.setFat(food_serv_response.getDouble(Settings.FOOD_JSON_FAT_ATTR));
	       
        } catch (Exception excep) {
        	
        	System.err.println("Exception catched, " + excep.toString());
        	return null;
        }

        return result;
    }
}