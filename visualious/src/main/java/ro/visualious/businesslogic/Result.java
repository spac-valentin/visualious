package ro.visualious.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ro.visualious.responsegenerator.model.Answer;

/**
 * Created by Spac on 6/21/2015.
 */
@XmlRootElement(name = "result")
public class Result {
    private List<Answer> dbpedia = new ArrayList<>();
    private String entityType;

    public List<Answer> getDbpedia() {
        return dbpedia;
    }

    public void setDbpedia(List<Answer> dbpedia) {
        this.dbpedia = dbpedia;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
