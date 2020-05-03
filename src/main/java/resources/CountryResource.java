package resources;

import dao.CountryDao;
import model.Country;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("countries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {

    @Inject
    CountryDao dao;

    @GET
    public List<Country> getCountries(@Context UriInfo uriInfo) {
        List<Country> countries = dao.getCountries();
        String uri;
        for (Country country : countries) {
            uri = getUriForSelf(uriInfo, country);
            country.addLink(uri, "self", "GET");
        }
        return countries;
    }

    @GET
    @Path("{countryId}")
    public Country getCountry(@PathParam("countryId") @Min(1) int id, @Context UriInfo uriInfo) {
        Country country = dao.getCountry(id);
        String selfUri = getUriForSelf(uriInfo, country);
        String statisticsUri = getUriForStatistics(uriInfo, country);
        country.addLink(selfUri, "self", "GET");
        country.addLink(statisticsUri, "statistics", "GET");
        return country;
    }

    @POST
    public Response addCountry(@NotNull @Valid Country country, @Context UriInfo uriInfo) {
        Country addedCountry = dao.addCountry(country);
        String newId = String.valueOf(addedCountry.getCountryId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(addedCountry).build();
    }

    @PUT
    @Path("{countryId}")
    public Response updateCountry(@PathParam("countryId") int id, Country country, @Context UriInfo uriInfo) {
        country.setCountryId(id);
        Country updatedCountry = dao.updateCountry(country);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok().location(uri).entity(updatedCountry).build();
    }

    @DELETE
    @Path("{countryId}")
    public void deleteCountry(@PathParam("countryId") int id) {
        dao.deleteCountry(id);
    }

    @Path("{countryId}/statistics")
    public CountryStatisticResource getCountryStatisticResource() {
        return new CountryStatisticResource();
    }

    public String getUriForSelf(UriInfo uriInfo, Country country) {
        return uriInfo.getBaseUriBuilder()
                .path(CountryResource.class)
                .path(String.valueOf(country.getCountryId()))
                .build()
                .toString();
    }

    public String getUriForStatistics(UriInfo uriInfo, Country country) {
        return uriInfo.getBaseUriBuilder()
                .path(CountryResource.class)
                .path(String.valueOf(country.getCountryId()))
                .path(StatisticsResource.class)
                .build()
                .toString();
    }

}
