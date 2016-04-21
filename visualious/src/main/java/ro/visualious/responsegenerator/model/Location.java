package ro.visualious.responsegenerator.model;

import java.util.ArrayList;

import ro.visualious.responsegenerator.jsonmodel.JsonType;

/**
 * Created by Spac on 4/22/2015.
 */
public class Location {
    private JsonType name;
    private JsonType description;
    private JsonType thumbnails;
    private JsonType wikiPageExternal;
    private StringPair capital;
    private String officialLanguage;
    private String currency;
    private String callingCode;
    private Geolocation geolocation;
    private String area;
    private ArrayList<StringPair> religions;
    private String dateFounded;
    private String population;

    public JsonType getName() {
        return name;
    }

    public void setName(JsonType name) {
        this.name = name;
    }

    public void setDescription(JsonType description) {
        this.description = description;
    }

    public JsonType getDescription() {
        return description;
    }

    public void setThumbnails(JsonType thumbnails) {
        this.thumbnails = thumbnails;
    }

    public JsonType getThumbnails() {
        return thumbnails;
    }

    public void setWikiPageExternal(JsonType wikiPageExternal) {
        this.wikiPageExternal = wikiPageExternal;
    }

    public JsonType getWikiPageExternal() {
        return wikiPageExternal;
    }

    public void setCapital(StringPair capital) {
        this.capital = capital;
    }

    public StringPair getCapital() {
        return capital;
    }

    public void setOfficialLanguage(String officialLanguage) {
        this.officialLanguage = officialLanguage;
    }

    public String getOfficialLanguage() {
        return officialLanguage;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setReligions(ArrayList<StringPair> religions) {
        this.religions = religions;
    }

    public ArrayList<StringPair> getReligions() {
        return religions;
    }

    public void setDateFounded(String dateFounded) {
        this.dateFounded = dateFounded;
    }

    public String getDateFounded() {
        return dateFounded;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPopulation() {
        return population;
    }
}
