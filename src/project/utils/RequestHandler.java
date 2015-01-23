package project.utils;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;

import project.Settings;
import project.document.ws.People;

/**
 * Created by les on 16/01/15.
 */
public class RequestHandler {

    private Client _client;
    private WebTarget _service;
    private Response _response;
    private String _url;

    public RequestHandler(String url)
    {
        ClientConfig config = new ClientConfig();
        set_client(ClientBuilder.newClient(config));
        set_url(url);
        set_service(get_client().target(UriBuilder.fromUri(url).build()));
        set_response(null);

    }
    public String getRequestResult()
    {
        set_service(get_client().target(UriBuilder.fromUri(get_url()).build()));
        set_response(get_service().path("").request().accept(MediaType.APPLICATION_JSON).get());

        if(get_response() != null)
            return get_response().readEntity(String.class);
        return "";
    }

    public Client get_client() {
        return _client;
    }

    public void set_client(Client _client) {
        this._client = _client;
    }

    public WebTarget get_service() {
        return _service;
    }

    public void set_service(WebTarget _service) {
        this._service = _service;
    }

    public Response get_response() {
        return _response;
    }

    public void set_response(Response _response) {
        this._response = _response;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public static People getInterface() {
        People people = null;
        try {
//        	URL url = new URL(Settings.LOCAL_DB_BASE_URL + Settings.LOCAL_DB_BASE_PORT + Settings.LOCAL_DB_BASE_PATH);
        	URL url = new URL(Settings.BASE_PROTOCOL + Settings.DB_BASE_URL + Settings.DB_BASE_PORT + Settings.DB_BASE_PATH);
            QName qname = new QName(Settings.DB_BASE_ENDPOINT, Settings.DB_BASE_SERVICE);
            Service service = Service.create(url, qname);
            people = service.getPort(People.class);
            
            System.err.println("asdaddasdad");
            
        }
        catch (Exception ex){
            System.out.println("Exception catched: " + ex.getMessage());
        }
        return people;
    }
}
