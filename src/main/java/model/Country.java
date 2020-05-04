package model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Country {
    private int countryId;
    @JsonbProperty("country")
    @NotNull
    private String countryName;
    @JsonbProperty("code")
    @NotNull
    private String countryCode;
    private final List<Link> links = new ArrayList<>();

    public Country() {
    }

    public Country(int countryId, String countryName, String countryCode) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    @JsonbProperty("id")
    @NotNull
    public int getCountryId() {
        return countryId;
    }

    @JsonbTransient
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(String uri, String rel, String type) {
        Link link = new Link();
        link.setLink(uri);
        link.setRel(rel);
        link.setType(type);
        links.add(link);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
