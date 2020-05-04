package beans;

import dao.CountryDao;
import dao.StatisticDao;
import error.CountryNotFoundException;
import model.Country;
import model.Statistic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
public class CountryBean {
    @Inject
    CountryDao countryDao;
    @Inject
    StatisticDao statisticDao;
    @Inject
    UriBean uriBean;

    public List<Country> getCountries(UriInfo uriInfo) {
        List<Country> countries = countryDao.getCountries();
        for (Country country : countries) {
            country.addLink(uriBean.getUriForSelf(uriInfo, country), "self", "GET");
        }
        return countries;
    }

    public Country getCountry(@NotNull @Positive Integer id, UriInfo uriInfo) {
        Country country = countryDao.getCountry(id);
        if (country == null) {
            throw new CountryNotFoundException(id);
        }
        country.addLink(uriBean.getUriForSelf(uriInfo, country), "self", "GET");
        country.addLink(uriBean.getUriForStatistics(uriInfo, country), "statistics", "GET");
        return country;
    }

    public Response addCountry(@NotNull @Valid Country country, UriInfo uriInfo) {
        Country addedCountry = countryDao.addCountry(country);
        String newId = String.valueOf(addedCountry.getCountryId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(addedCountry).build();
    }

    public Response updateCountry(@NotNull @Positive Integer id, @NotNull @Valid Country country, UriInfo uriInfo) {
        country.setCountryId(id);
        Country updatedCountry = countryDao.updateCountry(country);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.ok().location(uri).entity(updatedCountry).build();
    }

    public void deleteCountry(@NotNull @Positive Integer id) {
        countryDao.deleteCountry(id);
    }

    public List<Statistic> getStatistics(@NotNull @Positive Integer id, @Min(1) @Max(12) Integer month) {
        List<Statistic> statistics;
        if (month != null) {
            statistics =  statisticDao.getStatistics(id, month);
        } else {
            statistics =  statisticDao.getStatistics(id);
        }
        return statistics;
    }
}
