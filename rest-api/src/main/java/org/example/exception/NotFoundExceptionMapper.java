package org.example.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

  @Override
  public Response toResponse(NotFoundException exception) {
    org.example.dto.ApiErrorResponse apiErrorResponse = new org.example.dto.ApiErrorResponse();
    apiErrorResponse.setCode(Status.NOT_FOUND.getStatusCode());
    apiErrorResponse.setType("Object not found");
    apiErrorResponse.setMessage(exception.getMessage());
    LOGGER.info(exception.getMessage());
    return Response.status(Status.NOT_FOUND).entity(apiErrorResponse).build();
  }
}
