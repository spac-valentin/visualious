package ro.visualious.responsegenerator.model;

import java.util.ArrayList;

import ro.visualious.responsegenerator.jsonmodel.JsonObject;
import ro.visualious.responsegenerator.jsonmodel.JsonType;

/**
 * Created by Spac on 4/18/2015.
 */
public class Person {
    private JsonType name;//
    private JsonType birthdate;//
    private JsonType thumbnails;//
    private JsonType deathdate;//
    private JsonType shortDescription;
    private JsonType description;//
    private JsonType wikiPageExternal;//
    private JsonType birthplace;//
    private JsonType nationality;//
    private JsonType education;//
    private JsonType children;//
    private JsonType siblings;//
    private JsonType parents;//
    private JsonType spouse;//
    private JsonType notableFor;


    public JsonType getName() {
        return name;
    }

    public void setName(JsonType name) {
        this.name = name;
    }

    public JsonType getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(JsonType birthdate) {
        this.birthdate = birthdate;
    }

    public JsonType getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(JsonType deathdate) {
        this.deathdate = deathdate;
    }

    public JsonType getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(JsonType object) {
        this.birthplace = object;
    }

    public JsonType getDescription() {
        return description;
    }

    public void setDescription(JsonType description) {
        this.description = description;
    }

    public JsonType getEducation() {
        return education;
    }

    public void setEducation(JsonType education) {
        this.education = education;
    }

    public JsonType getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(JsonType shortDescription) {
        this.shortDescription = shortDescription;
    }

    public JsonType getParents() {
        return parents;
    }

    public void setParents(JsonType parents) {
        this.parents = parents;
    }

    public JsonType getWikiPageExternal() {
        return wikiPageExternal;
    }

    public void setWikiPageExternal(JsonType wikiPageExternal) {
        this.wikiPageExternal = wikiPageExternal;
    }

    public JsonType getNationality() {
        return nationality;
    }

    public void setNationality(JsonType nationality) {
        this.nationality = nationality;
    }

    public JsonType getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(JsonType thumbnails) {
        this.thumbnails = thumbnails;
    }

    public JsonType getSpouse() {
        return spouse;
    }

    public void setSpouse(JsonType spouse) {
        this.spouse = spouse;
    }

    public JsonType getChildren() {
        return children;
    }

    public void setChildren(JsonType children) {
        this.children = children;
    }

    public void setNotableFor(JsonType notableFor) {
        this.notableFor = notableFor;
    }

    public JsonType getNotableFor() {
        return notableFor;
    }

    public JsonType getSiblings() {
        return siblings;
    }

    public void setSiblings(JsonType siblings) {
        this.siblings = siblings;
    }
}
