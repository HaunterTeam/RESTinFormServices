package project;

public final class Settings {
	
	// Common Settings
	public static final String REQ_TYPE = "GET";
	public static final String BASE_PROTOCOL = "http://";
	
	// ##############
	// ## SERVICES ##
	// ##############
	
	// Director Service
	public static final String LOCAL_BASE_URL = "localhost:";
	public static final String LOCAL_BASE_PORT = "9091";
	public static final String LOCAL_BASE_PATH = "/project-director/";

	public static final String BASE_URL = "restindirectorservice";
	public static final String BASE_PORT = "9091";
	public static final String BASE_PATH = "/project-director/";
	
	// Facebook Service
	public static final String LOCAL_FB_BASE_URL = "localhost:";
	public static final String LOCAL_FB_BASE_PORT = "8020";
	public static final String LOCAL_FB_BASE_PATH = "/project-facebook/auth?token=";

	public static final String FB_BASE_URL = "restinfacebookservice";
	public static final String FB_BASE_PORT = "";
	public static final String FB_BASE_PATH = "/project-facebook/auth?token=";
    
    // Phrases Service
    public static final String LOCAL_PH_BASE_URL = "localhost:";
	public static final String LOCAL_PH_BASE_PORT = "8010";
	public static final String LOCAL_PH_BASE_PATH = "/phrase-service/phrase";

	public static final String PH_BASE_URL = "restinphraseservice";
	public static final String PH_BASE_PORT = "";
	public static final String PH_BASE_PATH = "/phrase-service/phrase";
	
	public static final String PH_PARAM_BMI_LEVEL = "bmilvl";
	public static final String PH_PARAM_CHANGE = "change";
	public static final String PH_PARAM_WEATHER_TYPE_1 = "wt1";
	public static final String PH_PARAM_WEATHER_TYPE_2 = "wt2";
	public static final String PH_PARAM_WEATHER_TYPE_3 = "wt3";
	
    // BmiCalc Service
    public static final String LOCAL_CALC_BASE_URL = "localhost:";
	public static final String LOCAL_CALC_BASE_PORT = "8030";
	public static final String LOCAL_CALC_BASE_PATH = "/bmicalc-service/bmiutil";

    public static final String CALC_BASE_URL = "bmicalculatorservice";
	public static final String CALC_BASE_PORT = "";
	public static final String CALC_BASE_PATH = "/bmicalc-service/bmiutil";

	public static final String CALC_PARAM_BMI = "bmi";
	public static final String CALC_PARAM_OLDBMI = "bmiold";

	//Nutritionix Service
	public static final String LOCAL_FOOD_BASE_URL = "localhost:";
	public static final String LOCAL_FOOD_BASE_PORT = "8443";
	public static final String LOCAL_FOOD_BASE_PATH = "/nutritionix/food/";

	public static final String FOOD_BASE_URL = "95.85.59.245:";
	public static final String FOOD_BASE_PORT = "8443";
	public static final String FOOD_BASE_PATH = "/nutritionix/food/";

	public static final String FOOD_PARAM = "food";

	//Flickr Service
	public static final String LOCAL_FLICKR_BASE_URL = "localhost:";
	public static final String LOCAL_FLICKR_BASE_PORT = "9091";
	public static final String LOCAL_FLICKR_BASE_PATH = "/flickr/";

	public static final String FLICKR_BASE_URL = "95.85.59.245:";
	public static final String FLICKR_BASE_PORT = "9091";
	public static final String FLICKR_BASE_PATH = "/flickr/";

	//Database Service
	public static final String LOCAL_DB_BASE_URL = "http://localhost:";
	public static final String LOCAL_DB_BASE_PORT = "8443";
	public static final String LOCAL_DB_BASE_PATH = "/dbservice/ws/people?wsdl";

	public static final String DB_BASE_URL = "95.85.59.245:";
	public static final String DB_BASE_PORT = "8088";
	public static final String DB_BASE_PATH = "/dbservice/ws/people?wsdl";

	public static final String DB_BASE_ENDPOINT = "http://ws.document/";
	public static final String DB_BASE_SERVICE = "PeopleService";

}