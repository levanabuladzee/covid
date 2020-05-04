package resources;

import beans.CountryBean;
import model.Statistic;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountriesStatisticsResource {

    @Inject
    CountryBean countryBean;

    @GET
    public List<Statistic> getStatistics(@PathParam("id") Integer id, @QueryParam("month") Integer month) {
        return countryBean.getStatistics(id, month);
    }

}
