package ro.visualious.responsegenerator.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.visualious.persistence.MongoDBManager;
import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.model.Location;
import ro.visualious.responsegenerator.parser.helper.Constants;
import ro.visualious.responsegenerator.parser.helper.DBPediaPropertyExtractor;

/**
 * Created by Spac on 5/2/2015.
 */
class LocationParser extends AbstractParserType  {

    public LocationParser() {
        TYPE = Constants.LOCATION;
    }


    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        String extractedUri = "";
        //region extract uri from dbpedia response
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseNode = mapper.readTree(dbpediaResponse);

            if (responseNode.has("results")) {
                responseNode = responseNode.get("results").get("bindings");
            }

            if (responseNode.isArray()) {
                JsonNode aux;

                //iterates through object in bindings array
                for (JsonNode node : responseNode) {
                    //elements from every object (x0,x1..) these are properties
                    aux = node.get("x0");
                    if (aux != null && aux.get("type").toString().equals("\"uri\"")) {
                        extractedUri = DBPediaPropertyExtractor.extractValue(aux.get("value"));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //endregion

        Location aux;
        List<Answer> locations = new ArrayList<>();
        if (extractedUri != null && !extractedUri.trim().isEmpty()) {
            try {
                aux = dbpediaLocation(new URI(extractedUri));
                if(aux != null) {
                    Answer s = Answer.getBuilderForQuestion(questionId)
                            .setBody(aux)
                            .setOrigin(Constants.DBPEDIA)
                            .setType(TYPE)
                            .build();
                    locations.add(s);

                    MongoDBManager.saveAnswerList(locations);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return locations;
    }

    private Location dbpediaLocation(URI dbpediaUri) {

        if (dbpediaUri == null || dbpediaUri.toString().trim().isEmpty()) {
            return null;
        }

        try {
            WebTarget client;
            String locationInfoResponse, aux;
            JsonNode locationInfo;
            ObjectMapper mapper = new ObjectMapper();

            client = ClientBuilder.newClient().target(DBPediaPropertyExtractor.convertDBPediaUrlToResourceUrl(dbpediaUri.toString()));
            locationInfoResponse = client.request().get(String.class);
            locationInfo = mapper.readTree(locationInfoResponse).get(dbpediaUri.toString());

            Location location = new Location();
            location.setName(DBPediaPropertyExtractor.getName(locationInfo));

            location.setDescription(DBPediaPropertyExtractor.getAbstractDescription(locationInfo));
            location.setThumbnails(DBPediaPropertyExtractor.getThumbnail(locationInfo));
            location.setWikiPageExternal(DBPediaPropertyExtractor.getPrimaryTopicOf(locationInfo));
            location.setGeolocation(DBPediaPropertyExtractor.getGeolocation(locationInfo));
            location.setOfficialLanguage(DBPediaPropertyExtractor.getLanguage(locationInfo));
            location.setCapital(DBPediaPropertyExtractor.getCapital(locationInfo));
            location.setCurrency(DBPediaPropertyExtractor.getCurrency(locationInfo));
            location.setCallingCode(DBPediaPropertyExtractor.getCallingCode(locationInfo));
            location.setArea(DBPediaPropertyExtractor.getArea(locationInfo));
            location.setDateFounded(DBPediaPropertyExtractor.getFoundingDate(locationInfo));
            location.setPopulation(DBPediaPropertyExtractor.getPopulation(locationInfo));

            return location;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
