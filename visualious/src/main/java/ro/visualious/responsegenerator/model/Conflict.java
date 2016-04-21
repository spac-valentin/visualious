package ro.visualious.responsegenerator.model;

import java.util.ArrayList;

import ro.visualious.responsegenerator.jsonmodel.JsonType;

/**
 * Created by Spac on 4/26/2015.
 */
public class Conflict {
    private JsonType name;
    private String result;
    private String date;
    private JsonType description;
    private ArrayList<StringPair> partOf;
    private JsonType wikiPageExternal;
    private ArrayList<StringPair> place;
    private ArrayList<StringPair> commanders;
    private JsonType thumbnails;
    private ArrayList<StringPair> combatants;
    private ArrayList<Casualty> casualties;

    public JsonType getName() {
        return name;
    }

    public void setName(JsonType name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public ArrayList<StringPair> getPlace() {
        return place;
    }

    public void setPlace(ArrayList<StringPair> place) {
        this.place = place;
    }

    public ArrayList<StringPair> getCommanders() {
        return commanders;
    }

    public void setCommanders(ArrayList<StringPair> commanders) {
        this.commanders = commanders;
    }

    public JsonType getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(JsonType thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ArrayList<StringPair> getPartOf() {
        return partOf;
    }

    public void setPartOf(ArrayList<StringPair> partOf) {
        this.partOf = partOf;
    }

    public ArrayList<StringPair> getCombatants() {
        return combatants;
    }

    public void setCombatants(ArrayList<StringPair> combatants) {
        this.combatants = combatants;
    }

    public ArrayList<Casualty> getCasualties() {
        return casualties;
    }

    public void setCasualties(ArrayList<Casualty> casualties) {
        this.casualties = casualties;
    }
}
