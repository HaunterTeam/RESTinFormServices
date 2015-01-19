package utils;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
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
}
