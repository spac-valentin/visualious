package ro.visualious.responsegenerator.model;

import java.util.ArrayList;

import ro.visualious.responsegenerator.jsonmodel.JsonType;

/**
 * Created by Spac on 5/3/2015.
 */
public class Album {
    private JsonType name;
    private JsonType thumbnails;
    private ArrayList<StringPair> trackList;
    private JsonType wikiPageExternalLink;
    private String description;
    private String length;
    private String releaseDate;
    private String genre;

    public JsonType getName() {
        return name;
    }

    public void setName(JsonType name) {
        this.name = name;
    }

    public void setThumbnails(JsonType thumbnails) {
        this.thumbnails = thumbnails;
    }

    public JsonType getThumbnails() {
        return thumbnails;
    }

    public void setWikiPageExternalLink(JsonType wikiPageExternalLink) {
        this.wikiPageExternalLink = wikiPageExternalLink;
    }

    public JsonType getWikiPageExternalLink() {
        return wikiPageExternalLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<StringPair> getTrackList() {
        return trackList;
    }

    public void setTrackList(ArrayList<StringPair> trackList) {
        this.trackList = trackList;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }


}
