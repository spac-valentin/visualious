package ro.visualious.responsegenerator.parser;

import java.util.List;

import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.parser.helper.Constants;

/**
 * Created by Spac on 6/2/2015.
 */
class PersonnelInvolvedParser extends AbstractParserType {

    public PersonnelInvolvedParser() {
        TYPE = Constants.PERSON;
    }

    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        PersonParser parser = new PersonParser();
        return parser.parseDBPediaResponse(dbpediaResponse, questionId);
    }

}
