package sample.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidParametersException extends Exception implements ExceptionMapper<InvalidParametersException> {

  public InvalidParametersException() {
    super("invalid_parameter");
  }

  public InvalidParametersException(String string) {
    super(string);
  }

  @Override
  public Response toResponse(InvalidParametersException exception) {
    // return with 200 status
    return Response.ok("{error:1, error_message:\""+ exception.getMessage()  + "\"}").build();
  }

}
