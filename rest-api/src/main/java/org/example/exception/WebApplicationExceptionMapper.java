package org.example.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

  @Override
  public Response toResponse(WebApplicationException exception) {
    org.example.dto.ApiErrorResponse apiErrorResponse = new org.example.dto.ApiErrorResponse();
    apiErrorResponse.setCode(exception.getResponse().getStatus());
    apiErrorResponse.setMessage(
        exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage());
    apiErrorResponse.setType(exception.getMessage());
    LOGGER.info(exception.getMessage());
    return Response.status(exception.getResponse().getStatus()).entity(apiErrorResponse).build();
  }
}
