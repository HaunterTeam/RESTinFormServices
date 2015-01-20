package project.getfood;

import org.eclipse.persistence.oxm.sequenced.Setting;
import org.json.JSONObject;
import project.utils.RequestHandler;
import project.Settings;
/**
 * Created by les on 19/01/15.
 */

public class FoodService {

    private RequestHandler handler;

    public FoodService(){
        handler = new RequestHandler(Settings.FOOD_BASE_URL+Settings.FOOD_BASE_PORT+Settings.FOOD_BASE_PATH);
    }

    public Food getFoodNutritionValues(String food){

        handler.set_url(Settings.FOOD_BASE_URL+Settings.FOOD_BASE_PORT+Settings.FOOD_BASE_PATH+"?food="+food);
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