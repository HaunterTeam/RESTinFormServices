package project.businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import project.Settings;

public class BusinessLogicService {
	
	public BusinessLogicService() { }
	
	public BmiObj calculateBmiLvlAndChange(double bmi, double old_bmi) throws IOException {
		
		String url  = Settings.BASE_PROTOCOL + Settings.CALC_BASE_URL + Settings.CALC_BASE_PORT + Settings.CALC_BASE_PATH;
        url += "?bmi=" + bmi + "&bmi_old=" + old_bmi;
        
        URL obj = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(Settings.REQ_TYPE);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        
        JSONObject o = new JSONObject(response.toString());
        
        BmiObj bmiobj = new BmiObj();
        bmiobj.setBmilvl(Integer.parseInt(o.getString("bmilvl")));
        bmiobj.setChange(Integer.parseInt(o.getString("change")));
		return bmiobj;
	}
}