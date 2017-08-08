package sample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Request;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;




@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootController {

  @GET
  public Response index(@Context Request request) {

    // TODO how to get user IP Address(from header?)

    String ip = "127.0.0.1";
    String json = "{ip:" + "\"" + ip + "\"}";
    return Response.ok(json, MediaType.APPLICATION_JSON).build();

  }


}
