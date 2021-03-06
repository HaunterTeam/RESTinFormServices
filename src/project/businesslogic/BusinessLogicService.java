package project.businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import project.Settings;

public class BusinessLogicService {

    public BusinessLogicService() { }

    public BmiObj calculateBmiLvlAndChange(double bmi, double old_bmi) throws IOException {

        String url = "";
        url += Settings.BASE_PROTOCOL + Settings.CALC_BASE_URL + Settings.CALC_BASE_PORT + Settings.CALC_BASE_PATH;
        url += "?" + Settings.CALC_PARAM_BMI + "=" + bmi + "&" + Settings.CALC_PARAM_OLDBMI + "=" + old_bmi;
        
        System.out.println("URL: " + url);

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

        JSONObject o = new JSONObject(response.toString());
        BmiObj bmiobj = new BmiObj();
        bmiobj.setBmilvl(o.getInt("bmilvl"));
        bmiobj.setChange(o.getInt("change"));
        return bmiobj;
    }

    public String getFood(double bmi) {

        List<String> dieteticFoods = new ArrayList<String>(Arrays.asList("salmon", "broccoli", "salad", "tomato", "berries", "beans", "soup", "eggs", "yougurt", "grapefruit", "apple"));
        List<String> normalFood = new ArrayList<String>(Arrays.asList("pizza", "bacon", "salad", "potato", "roast", "pasta", "pasta carbonara", "noodles","sausage","burger"));
        List<String> fatFood = new ArrayList<String>(Arrays.asList("fries", "mc crispy", "burger king", "cheese", "ice cream", "chicken burger", "fried chicken","sausage","burger", "bbq ribs", "bbq", "schnitzel", "subway sandwich", "caramel cupcake"));

        List<String> list = null;

        if(bmi <= 18)
            list = fatFood;
        else if(bmi <= 29)
            list = normalFood;
        else if(bmi > 29)
            list = dieteticFoods;

        //Debug
        list = fatFood;

        Random rmd = new Random();
        return list.get(rmd.nextInt(list.size() - 1));
    }
}