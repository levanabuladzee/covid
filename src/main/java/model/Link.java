package model;

import javax.validation.constraints.NotNull;

public class Link {
    @NotNull
    private String link;
    @NotNull
    private String rel;
    @NotNull
    private String type;

    public Link() {
    }

    public Link(String link, String rel, String type) {
        this.link = link;
        this.rel = rel;
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Link{" +
                "link='" + link + '\'' +
                ", rel='" + rel + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
