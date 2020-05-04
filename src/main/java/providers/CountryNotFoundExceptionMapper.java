package providers;

import error.ApiErrorResponse;
import error.CountryNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CountryNotFoundExceptionMapper implements ExceptionMapper<CountryNotFoundException> {

    @Override
    public Response toResponse(CountryNotFoundException e) {
        ApiErrorResponse errorResponse = new ApiErrorResponse("0001", "No country found with ID " + e.getId());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

}
