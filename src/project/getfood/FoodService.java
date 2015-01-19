package project.getfood;

import org.json.JSONObject;
import project.utils.RequestHandler;

/**
 * Created by les on 19/01/15.
 */

public class FoodService {

    public static final String BASE_URL = "http://95.85.59.245:8443/nutritionix/food/";
    private RequestHandler handler;

    public FoodService(){
        handler = new RequestHandler(BASE_URL);
    }

    public Food getFoodNutritionValues(String food){

        handler.set_url(BASE_URL+"?food="+food);
        JSONObject obj = new JSONObject(handler.getRequestResult());

        if(obj == null)
            return null;
        Food result = new Food();

        result.setName(obj.getString("item_name"));
        result.setBrand(obj.getString("brand_name"));
        result.setCalories((float)obj.getDouble("nf_calories"));
        result.setFat((float)obj.getDouble("nf_total_fat"));

        return result;
    }

}
