package resources;

import beans.CountryBean;
import model.Country;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("countries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountriesResource {

    @Inject
    CountryBean countryBean;
    @Context
    ResourceContext context;

    @GET
    public List<Country> getCountries(@Context UriInfo uriInfo) {
        return countryBean.getCountries(uriInfo);
    }

    @GET
    @Path("{id}")
    public Country getCountry(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        return countryBean.getCountry(id, uriInfo);
    }

    @POST
    public Response addCountry(Country country, @Context UriInfo uriInfo) {
        return countryBean.addCountry(country, uriInfo);
    }

    @PUT
    @Path("{id}")
    public Response updateCountry(@PathParam("id") Integer id, Country country, @Context UriInfo uriInfo) {
        return countryBean.updateCountry(id, country, uriInfo);
    }

    @DELETE
    @Path("{id}")
    public void deleteCountry(@PathParam("id") Integer id) {
        countryBean.deleteCountry(id);
    }

    @Path("{id}/statistics")
    public CountriesStatisticsResource getCountryStatisticResource() {
        return this.context.getResource(CountriesStatisticsResource.class);
    }

}
