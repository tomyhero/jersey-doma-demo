package sample.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundException extends Exception implements ExceptionMapper<NotFoundException> {

  public NotFoundException() {
    super("not_found");
  }

  public NotFoundException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(NotFoundException exception) {
    // return with 200 status
    return Response.ok("{error:1, error_message:\""+ exception.getMessage()  + "\"}").build();
  }

}
