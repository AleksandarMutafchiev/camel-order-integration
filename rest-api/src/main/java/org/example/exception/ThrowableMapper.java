package org.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableMapper.class);

  @Override
  public Response toResponse(Throwable exception) {
    LOGGER.error("Unexpected exception caught", exception);
    org.example.dto.ApiErrorResponse apiErrorResponse = new org.example.dto.ApiErrorResponse();
    apiErrorResponse.setCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
    apiErrorResponse.setType("Internal Server Error");
    apiErrorResponse.setMessage(exception.getMessage());
    return Response.status(Status.INTERNAL_SERVER_ERROR).entity(apiErrorResponse).build();
  }
}
