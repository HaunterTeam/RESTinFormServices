package project;

public final class Settings {

//	LOCALHOST
//	public static final String BASE_URL = "localhost";
//	public static final String BASE_PORT = "5900";
	
	public static final String BASE_PROTOCOL = "http://";
	public static final String BASE_URL = "localhost";
	public static final String BASE_PORT = "9091";
	public static final String SERVICE_PATH = "/project-director/";
	
	
	// ##############
	// ## SERVICES ##
	// ##############
	
	// Common Settings
	public static final String REQ_TYPE = "GET";
	
	// Facebook Service
	public static final String FB_BASE_URL = "http://localhost:";
	public static final String FB_BASE_PORT = "8020";
	public static final String FB_BASE_PATH = "/project-facebook/auth?token=";
    
    public static final String FB_QUERY_INFO = "/me?fields=id,first_name&access_token=";
    public static final String FB_QUERY_PHOTO = "/me/picture?type=normal&height=120&width=120&redirect=false&fields=url&access_token=";
}