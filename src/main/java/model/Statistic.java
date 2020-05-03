package model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Statistic {
    @JsonbProperty("country-id")
    private int countryId;
    @JsonbProperty("date")
    private String date;
    @JsonbProperty("confirmed")
    private long confirmed;
    @JsonbProperty("deaths")
    private long deaths;
    @JsonbProperty("recovered")
    private long recovered;
    @JsonbProperty("statistic-id")
    private int statisticId;

    public Statistic() {
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

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
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
