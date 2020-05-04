package model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.validation.constraints.NotNull;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Statistic {
    private int statisticId;
    @JsonbProperty("country-id")
    @NotNull
    private int countryId;
    @JsonbProperty("date")
    @NotNull
    private String date;
    @JsonbProperty("confirmed")
    @NotNull
    private long confirmed;
    @JsonbProperty("deaths")
    @NotNull
    private long deaths;
    @JsonbProperty("recovered")
    @NotNull
    private long recovered;

    public Statistic() {
    }

    public Statistic(@NotNull @JsonbProperty("country-id") int countryId,
                     @NotNull @JsonbProperty("date") String date,
                     @NotNull @JsonbProperty("confirmed") long confirmed,
                     @NotNull @JsonbProperty("deaths")long deaths,
                     @NotNull @JsonbProperty("recovered") long recovered) {
        this.countryId = countryId;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    @JsonbProperty("statistic-id")
    @NotNull
    public int getStatisticId() {
        return statisticId;
    }

    @JsonbTransient
    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + statisticId +
                ", countryId=" + countryId +
                ", date='" + date + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
