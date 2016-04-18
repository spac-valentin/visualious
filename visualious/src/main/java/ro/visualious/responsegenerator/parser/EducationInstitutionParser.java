package ro.visualious.responsegenerator.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.visualious.persistence.MongoDBManager;
import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.model.EducationInstitution;
import ro.visualious.responsegenerator.parser.helper.Constants;
import ro.visualious.responsegenerator.parser.helper.FreebasePropertyExtractor;
import ro.visualious.responsegenerator.parser.helper.MetadataProperties;

/**
 * Created by Spac on 6/23/2015.
 */
class EducationInstitutionParser extends AbstractParserType  {
    private static Logger log = Logger.getLogger(SongParser.class.getCanonicalName());

    public EducationInstitutionParser() {
        TYPE = Constants.EDUCATION_INSTITUTION;
    }


    @Override
    public List<Answer> parseFreebaseResponse(String freebaseResponse, String questionId) {
        String extractedUri;
        Set<String> uris = new HashSet<>();
        List<Answer> answers = new ArrayList<>();
        //region extract uri from freebase
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode results = mapper.readTree(freebaseResponse).get("result");
            if (results.isArray()) {
                for (JsonNode item : results) {
                    extractedUri = FreebasePropertyExtractor.getFreebaseLink(getIdForInstitution(item));
                    if (extractedUri != null && !extractedUri.trim().isEmpty()) {
                        uris.add(extractedUri);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //endregion

        List<String> listOfUris = new LinkedList<>(uris);
        extractFreebaseAnswers(questionId, listOfUris, answers, 0, Constants.MAX_CHUNK_SIZE);
        new Thread(() -> extractFreebaseAnswers(questionId, listOfUris, null, Constants.MAX_CHUNK_SIZE, 0)).start();

        return answers;
    }

    private String getIdForInstitution(JsonNode item) {
        JsonNode institution = item.path(MetadataProperties.EDUCATIONAL_INSTITUTION.getFreebase());
        if(institution != null && !institution.isMissingNode()) {
            if(institution.isArray()) {
                for(JsonNode node : institution) {
                    return FreebasePropertyExtractor.extractFreebaseId(node);
                }
            }
        }
        return null;
    }

    private void extractFreebaseAnswers(String questionId, List<String> uris, List<Answer> answers, int offset, int max) {
        Object person;
        int start = 0, finish;

        if(offset == 0) {
            finish = Math.min(max, uris.size());
        } else {
            start = offset;
            finish = uris.size();
        }

        if(answers == null) {
            answers = new ArrayList<>();
        }

        for(int idx = start; idx < finish; idx++) {
            try {
                person = freebaseEducationInstitution(new URI(uris.get(idx)));
                Answer s = Answer.getBuilderForQuestion(questionId)
                        .setBody(person)
                        .setOrigin(Constants.FREEBASE)
                        .setType(TYPE)
                        .build();
                answers.add(s);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        //save in db???
        MongoDBManager.saveAnswerList(answers);
    }

    private Object freebaseEducationInstitution(URI freebaseURI) {
        if (freebaseURI == null || freebaseURI.toString().trim().isEmpty()) {
            return null;
        }

        try {
            WebTarget client;
            String personInfoResponse, aux;
            ObjectMapper mapper = new ObjectMapper();

            client = ClientBuilder.newClient().target(freebaseURI);
            personInfoResponse = client.request().get(String.class);
            JsonNode institutionNode = mapper.readTree(personInfoResponse).get("property");

            EducationInstitution institution = new EducationInstitution();
            institution.setDescription(FreebasePropertyExtractor.getAbstractDescription(institutionNode));
            institution.setWikiPageExternal(FreebasePropertyExtractor.getPrimaryTopicOf(institutionNode));
            institution.setWebsites(FreebasePropertyExtractor.getOfficialWebsites(institutionNode));
            institution.setGeolocation(FreebasePropertyExtractor.getGeolocation(institutionNode));
            institution.setDateFounded(FreebasePropertyExtractor.getOrganizationDateFounded(institutionNode));
            institution.setName(FreebasePropertyExtractor.getPersonName(institutionNode));
            institution.setGraduates(FreebasePropertyExtractor.getGraduatesForEducationInstitution(institutionNode));



            System.out.println("dad");
            return institution;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        System.out.println(dbpediaResponse);
        return null;
    }
}
