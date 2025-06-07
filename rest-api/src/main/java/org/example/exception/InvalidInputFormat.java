package org.example.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class InvalidInputFormat implements ExceptionMapper<InvalidFormatException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(InvalidInputFormat.class);

  @Override
  public Response toResponse(InvalidFormatException exception) {
    org.example.dto.ApiErrorResponse apiErrorResponse = new org.example.dto.ApiErrorResponse();
    apiErrorResponse.setCode(Status.BAD_REQUEST.getStatusCode());
    apiErrorResponse.setType("Invalid input type");
    apiErrorResponse.setMessage(exception.getMessage());
    LOGGER.info(exception.getMessage());
    return Response.status(Status.BAD_REQUEST).entity(apiErrorResponse).build();
  }
}
