package ro.visualious.responsegenerator.parser;

import java.util.List;

import org.apache.log4j.Logger;

import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.parser.helper.Constants;

/**
 * Created by Spac on 6/23/2015.
 */
class EducationInstitutionParser extends AbstractParserType  {
    private static Logger log = Logger.getLogger(SongParser.class.getCanonicalName());

    public EducationInstitutionParser() {
        TYPE = Constants.EDUCATION_INSTITUTION;
    }


    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        System.out.println(dbpediaResponse);
        return null;
    }
}
