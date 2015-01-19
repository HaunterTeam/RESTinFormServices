package project;

public final class Settings {
	
	// Common Settings
	public static final String REQ_TYPE = "GET";
	public static final String BASE_PROTOCOL = "http://";
	
	// ##############
	// ## SERVICES ##
	// ##############
	
	// Director Service
	public static final String BASE_URL = "localhost:";
	public static final String BASE_PORT = "9091";
	public static final String BASE_PATH = "/project-director/";
	
	// Facebook Service
	public static final String FB_BASE_URL = "localhost:";
	public static final String FB_BASE_PORT = "8020";
	public static final String FB_BASE_PATH = "/project-facebook/auth?token=";
    
    public static final String FB_QUERY_INFO = "/me?fields=id,first_name&access_token=";
    public static final String FB_QUERY_PHOTO = "/me/picture?type=normal&height=120&width=120&redirect=false&fields=url&access_token=";
    
    // Phrases Service
    public static final String PH_BASE_URL = "localhost:";
	public static final String PH_BASE_PORT = "8010";
	public static final String PH_BASE_PATH = "/phrase-service/phrase";

}