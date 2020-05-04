package resources;

import beans.StatisticBean;
import model.Statistic;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("statistics")
public class StatisticsResource {

    @Inject
    StatisticBean statisticBean;

    @POST
    public Response addStatistic(Statistic statistic, @Context UriInfo uriInfo) {
        return statisticBean.addStatistic(statistic, uriInfo);
    }

    @PUT
    @Path("{id}")
    public Response updateStatistic(@PathParam("id") Integer id, Statistic statistic, @Context UriInfo uriInfo) {
        return statisticBean.updateStatistic(id, statistic, uriInfo);
    }

    @DELETE
    @Path("{id}")
    public void deleteStatistic(@PathParam("id") Integer id) {
        statisticBean.deleteStatistic(id);
    }

}
