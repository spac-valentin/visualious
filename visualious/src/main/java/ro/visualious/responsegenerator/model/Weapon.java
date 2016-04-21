package ro.visualious.responsegenerator.model;

import ro.visualious.responsegenerator.jsonmodel.JsonType;

/**
 * Created by Spac on 4/26/2015.
 */
public class Weapon {
    private JsonType name;
    private JsonType thumbnails;
    private JsonType description;
    private JsonType wikiPageExternal;
    private String weight;
    private String length;
    private StringPair designer;
    private StringPair origin;
    private String caliber;
    private String service;

    public JsonType getName() {
        return name;
    }

    public void setName(JsonType name) {
        this.name = name;
    }

    public JsonType getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(JsonType thumbnails) {
        this.thumbnails = thumbnails;
    }

    public JsonType getDescription() {
        return description;
    }

    public void setDescription(JsonType description) {
        this.description = description;
    }

    public JsonType getWikiPageExternal() {
        return wikiPageExternal;
    }

    public void setWikiPageExternal(JsonType wikiPageExternal) {
        this.wikiPageExternal = wikiPageExternal;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }


    public StringPair getDesigner() {
        return designer;
    }

    public void setDesigner(StringPair designer) {
        this.designer = designer;
    }

    public StringPair getOrigin() {
        return origin;
    }

    public void setOrigin(StringPair origin) {
        this.origin = origin;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
