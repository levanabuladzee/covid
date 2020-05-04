package beans;

import dao.StatisticDao;
import model.Statistic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
public class StatisticBean {
    @Inject
    StatisticDao statisticDao;
    @Inject
    UriBean uriBean;

    public Response addStatistic(@NotNull @Valid Statistic statistic, UriInfo uriInfo) {
        Statistic addedStatistic = statisticDao.addStatistic(statistic);
        String newId = String.valueOf(addedStatistic.getCountryId());
        URI uri = uriBean.getStatisticsUri(uriInfo, newId);
        return Response.created(uri).entity(addedStatistic).build();
    }

    public Response updateStatistic(@NotNull @Positive Integer id, @NotNull @Valid Statistic statistic, UriInfo uriInfo) {
        statistic.setStatisticId(id);
        Statistic updatedStatistic = statisticDao.updateStatistic(statistic);
        String newId = String.valueOf(updatedStatistic.getCountryId());
        URI uri = uriBean.getStatisticsUri(uriInfo, newId);
        return Response.ok().location(uri).entity(updatedStatistic).build();
    }

    public void deleteStatistic(@NotNull @Positive Integer id) {
        statisticDao.deleteStatistic(id);
    }
}
