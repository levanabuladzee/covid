package resources;

import dao.StatisticDao;
import model.Statistic;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/statistics")
public class StatisticsResource {
    @Inject
    StatisticDao dao;

    @POST
    public Response addStatistic(Statistic statistic, @Context UriInfo uriInfo) {
        Statistic addedStatistic = dao.addStatistic(statistic);
        String newId = String.valueOf(addedStatistic.getCountryId());
        URI uri = uriInfo.getBaseUriBuilder().path(CountryResource.class).path(newId).path(StatisticsResource.class).build();
        return Response.created(uri).entity(addedStatistic).build();
    }

    @PUT
    @Path("/{statisticId}")
    public Response updateStatistic(@PathParam("statisticId") int id, Statistic statistic, @Context UriInfo uriInfo) {
        int country = Integer.parseInt(String.valueOf(statistic.getCountryId()));
        statistic.setStatisticId(id);
        Statistic updatedStatistic = dao.updateStatistic(statistic);
        updatedStatistic.setCountryId(country);
        String newId = String.valueOf(updatedStatistic.getCountryId());
        URI uri = uriInfo.getBaseUriBuilder().path(CountryResource.class).path(newId).path(StatisticsResource.class).build();
        return Response.ok().location(uri).entity(updatedStatistic).build();
    }

    @DELETE
    @Path("/{statisticId}")
    public void deleteStatistic(@PathParam("statisticId") int id) {
        dao.deleteStatistic(id);
    }
}
