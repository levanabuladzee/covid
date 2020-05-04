package beans;

import model.Country;
import resources.CountriesResource;
import resources.StatisticsResource;

import javax.ejb.Stateless;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
public class UriBean {
    public String getUriForSelf(UriInfo uriInfo, Country country) {
        return uriInfo.getBaseUriBuilder()
                .path(CountriesResource.class)
                .path(String.valueOf(country.getCountryId()))
                .build()
                .toString();
    }

    public String getUriForStatistics(UriInfo uriInfo, Country country) {
        return uriInfo.getBaseUriBuilder()
                .path(CountriesResource.class)
                .path(String.valueOf(country.getCountryId()))
                .path(StatisticsResource.class)
                .build()
                .toString();
    }

    public URI getStatisticsUri(UriInfo uriInfo, String id) {
        return uriInfo.getBaseUriBuilder().path(CountriesResource.class).path(id).path(StatisticsResource.class).build();
    }
}
