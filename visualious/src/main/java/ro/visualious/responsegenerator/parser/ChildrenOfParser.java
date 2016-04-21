package ro.visualious.responsegenerator.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.visualious.persistence.MongoDBManager;
import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.model.Person;
import ro.visualious.responsegenerator.parser.helper.Constants;
import ro.visualious.responsegenerator.parser.helper.DBPediaPropertyExtractor;

/**
 * Created by Spac on 4/12/2015.
 */
class ChildrenOfParser extends AbstractParserType  {
    private static Logger log = Logger.getLogger(ChildrenOfParser.class.getCanonicalName());

    public ChildrenOfParser() {
        TYPE = Constants.PERSON;
    }


    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        Map<String, String> dbpediaChildrenInfo = new HashMap<>();
        parseDBPediaForUriAndName(dbpediaResponse, dbpediaChildrenInfo);

        ArrayList<String> listOfUris = new ArrayList<>();
        listOfUris.addAll(dbpediaChildrenInfo.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));

        List<Answer> answers = new ArrayList<>();
        extractDBPediaAnswers(questionId, listOfUris, answers, 0, Constants.MAX_CHUNK_SIZE);

        new Thread(() -> extractDBPediaAnswers(questionId, listOfUris, null, Constants.MAX_CHUNK_SIZE, 0)).start();

        return answers;
    }



    /**
     * Parses the response from dbpedia. Iterates through properties of "bindings", extracts the value of uris and saves
     * them in @param uris; if uri does not exists, extracts the literal (child name) and saves it in @param childrenName
     *
     * @param dbpedia      string to pe parsed (dbpedia response)
     * @param childrenName
     */
    private void parseDBPediaForUriAndName(String dbpedia, Map<String, String> childrenName) {
        try {
            /*
            * "results": {
                "bindings": [
                   {
                    "x0": { "type": "uri" , "value": "http://dbpedia.org/resource/Steve_Jobs" } ,
                    "x1": { "type": "literal" , "xml:lang": "en" , "value": "Erin Jobs" }
                  }]
            * */
            ObjectMapper mapper = new ObjectMapper();
            JsonNode dbpediaResponse = mapper.readTree(dbpedia);
            JsonNode bindings = dbpediaResponse.get("results").get("bindings");

            if (bindings.isArray()) {
                JsonNode aux;
                String uri, personName;
                String[] auxArray;

                //iterates through object in bindings array
                for (JsonNode node : bindings) {
                    //elements from every object (x0,x1..) these are properties
                    aux = node.get("x1");
                    if (aux != null) {
                        //TODO another approach is to GET link, get foaf:name property and add it here
                        if (aux.get("type").toString().equals("\"uri\"")) {
                            uri = DBPediaPropertyExtractor.extractValue(aux.get("value"));
                            auxArray = uri.split("/");
                            personName = auxArray[auxArray.length - 1].replace("_", " ");
                            childrenName.put(personName, DBPediaPropertyExtractor.extractValue(aux.get("value")));
                        }

                        if (aux.get("type").toString().equals("\"literal\"")) {
                            childrenName.put(DBPediaPropertyExtractor.extractValue(aux.get("value")),
                                    DBPediaPropertyExtractor.extractValue(aux.get("value")));
                            // System.out.println(extractValue(aux.get("value")));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractDBPediaAnswers(String questionId, List<String> uris, List<Answer> answers, int offset, int finish) {
        Person person;
        int start = 0, max = 0;

        if(offset == 0) {
            max = Math.min(finish, uris.size());
        } else {
            start = offset;
            max = uris.size();
        }

        if(answers == null) {
            answers = new ArrayList<>();
        }

        PersonParser parser = new PersonParser();
        for(int idx = start; idx < max; idx++) {
            try {
                person = parser.dbpediaWhoIs(new URI(uris.get(idx)));
                Answer s = Answer.getBuilderForQuestion(questionId)
                        .setBody(person)
                        .setOrigin(Constants.DBPEDIA)
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

}
