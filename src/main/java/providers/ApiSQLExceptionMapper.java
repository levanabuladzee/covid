package providers;

import error.ApiSQLException;
import error.ApiErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiSQLExceptionMapper implements ExceptionMapper<ApiSQLException> {

    @Override
    public Response toResponse(ApiSQLException e) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(e.getCode(), e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

}
