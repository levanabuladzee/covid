package resources;

import dao.StatisticDao;
import model.Statistic;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryStatisticResource {

    StatisticDao dao = new StatisticDao();

    @GET
    public List<Statistic> getStatistics(@PathParam("countryId") int countryId, @Min(1) @Max(12) @QueryParam("month") Integer month) {
        List<Statistic> statistics;
        if (month > 0 && month <= 12) {
            statistics =  dao.getStatistics(countryId, month);
        } else {
            statistics =  dao.getStatistics(countryId);
        }
        return statistics;
    }

}
