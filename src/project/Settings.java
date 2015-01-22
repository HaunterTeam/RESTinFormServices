package project;

public final class Settings {

    // Common Settings
    public static final String REQ_TYPE = "GET";
    public static final String BASE_PROTOCOL = "http://";
	
	// Director Service
	public static final String LOCAL_BASE_URL = "localhost:";
	public static final String LOCAL_BASE_PORT = "9091";
	public static final String LOCAL_BASE_PATH = "/project-director/";

	public static final String BASE_URL = "restindirectorservice.herokuapp.com";
	public static final String BASE_PORT = "9091";
	public static final String BASE_PATH = "/project-director/";
	
	// Facebook Service
	public static final String LOCAL_FB_BASE_URL = "localhost:";
	public static final String LOCAL_FB_BASE_PORT = "8020";
	public static final String LOCAL_FB_BASE_PATH = "/project-facebook/auth?token=";

	public static final String FB_BASE_URL = "restinfacebookservice.herokuapp.com";
	public static final String FB_BASE_PORT = "";
	public static final String FB_BASE_PATH = "/project-facebook/auth?token=";
	
    public static final String FB_JSON_OUT_ID_ATTR = "id";
    public static final String FB_JSON_OUT_NAME_ATTR = "first_name";
    public static final String FB_JSON_OUT_LOCATION_ATTR = "location";
    public static final String FB_JSON_OUT_STATUS_OBJ = "status";
    public static final String FB_JSON_OUT_STATUS_CODE_ATTR = "code";
    public static final String FB_JSON_OUT_STATUS_MESSAGE_ATTR = "message";
    public static final String FB_JSON_OUT_RESULT_OBJ = "result";
    
    public final static int FB_OK_REQ = 200;
    public final static int FB_ERR_REQ = 1;
    public final static String FB_OK_MESSAGE = "Valid Request";
    public final static String FB_ERR_MESSAGE = "Invalid OAuth access token";
    
    // Phrases Service
    public static final String LOCAL_PH_BASE_URL = "localhost:";
	public static final String LOCAL_PH_BASE_PORT = "8010";
	public static final String LOCAL_PH_BASE_PATH = "/phrase-service/phrase";

	public static final String PH_BASE_URL = "restinphraseservice.herokuapp.com";
	public static final String PH_BASE_PORT = "";
	public static final String PH_BASE_PATH = "/phrase-service/phrase";
	
	public static final String PH_PARAM_BMI_LEVEL = "bmilvl";
	public static final String PH_PARAM_CHANGE = "change";
	public static final String PH_PARAM_WEATHER_TYPE_1 = "wt1";
	public static final String PH_PARAM_WEATHER_TYPE_2 = "wt2";
	public static final String PH_PARAM_WEATHER_TYPE_3 = "wt3";

	public static final String PH_JSON_ID_PHRASE = "idphrase";
    public static final String PH_JSON_PHRASE = "phrase";
    public static final String PH_JSON_WEATHER_TYPE = "weathertype";
    public static final String PH_JSON_BMIRANGE = "bmirange";
    public static final String PH_JSON_CHANGE = "change";
    public static final String PH_JSON_ACTIVITY = "activity";
	
    // BmiCalc Service
    public static final String LOCAL_CALC_BASE_URL = "localhost:";
	public static final String LOCAL_CALC_BASE_PORT = "8030";
	public static final String LOCAL_CALC_BASE_PATH = "/bmicalc-service/bmiutil";

    public static final String CALC_BASE_URL = "bmicalculatorservice.herokuapp.com";
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

	//SOAP Database Service
	public static final String LOCAL_DB_BASE_URL = "http://localhost:";
	public static final String LOCAL_DB_BASE_PORT = "8443";
	public static final String LOCAL_DB_BASE_PATH = "/dbservice/ws/people?wsdl";

	public static final String DB_BASE_URL = "95.85.59.245:";
	public static final String DB_BASE_PORT = "8088";
	public static final String DB_BASE_PATH = "/dbservice/ws/people?wsdl";

	public static final String DB_BASE_ENDPOINT = "http://ws.document/";
	public static final String DB_BASE_SERVICE = "PeopleService";

	//RESTFul Database Service
	public static final String RDB_BASE_URL = "95.85.59.245:";
	public static final String RDB_BASE_PORT = "8086";
	public static final String RDB_BASE_PATH = "/dbservice/";

}