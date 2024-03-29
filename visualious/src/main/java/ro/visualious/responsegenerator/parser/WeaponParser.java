package ro.visualious.responsegenerator.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ro.visualious.persistence.MongoDBManager;
import ro.visualious.responsegenerator.model.Answer;
import ro.visualious.responsegenerator.model.Weapon;
import ro.visualious.responsegenerator.parser.helper.Constants;
import ro.visualious.responsegenerator.parser.helper.DBPediaPropertyExtractor;

/**
 * Created by Spac on 4/26/2015.
 */
class WeaponParser extends AbstractParserType{
    private static Logger log = Logger.getLogger(WeaponParser.class.getCanonicalName());

    public WeaponParser() {
        TYPE = Constants.WEAPON;
    }


    @Override
    public List<Answer> parseDBPediaResponse(String dbpediaResponse, String questionId) {
        if (log.isInfoEnabled()) {
            log.info("WeaponUsedByCountryInConflict" + " : " + dbpediaResponse);
        }

        if (dbpediaResponse == null || dbpediaResponse.trim().isEmpty()) {
            return null;
        }

        ArrayList<String> weaponUris = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode response = mapper.readTree(dbpediaResponse).get("results").get("bindings");
            if (response.isArray()) {
                JsonNode aux;
                //x0 -> conflict uri, x1 -> weapon, x2 -> country/place
                for (JsonNode responseItem : response) {
                    aux = responseItem.get("x1");

                    if (aux != null && aux.get("type").toString().equals("\"uri\"")) {
                        weaponUris.add(DBPediaPropertyExtractor.extractValue(aux.get("value")));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Answer> answers = new ArrayList<>();
        List<String> listOfUris = new LinkedList<>(weaponUris);

        extractDbPediaAnswers(questionId, listOfUris, answers, 0, Constants.MAX_CHUNK_SIZE);
        new Thread(() -> extractDbPediaAnswers(questionId, listOfUris, null, Constants.MAX_CHUNK_SIZE, 0)).start();

        return answers;
    }

    private void extractDbPediaAnswers(String questionId, List<String> uris, List<Answer> answers, int offset, int max) {
        Weapon weapon;
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
                weapon = dbpediaWeapon(new URI(uris.get(idx)));
                Answer s = Answer.getBuilderForQuestion(questionId)
                        .setBody(weapon)
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

    public Weapon dbpediaWeapon(URI dbpediaUri) {
        if (dbpediaUri == null || dbpediaUri.toString().trim().isEmpty()) {
            return null;
        }

        try {
            WebTarget client;
            String weaponInfoResponse, aux;
            JsonNode weaponInfo;
            ObjectMapper mapper = new ObjectMapper();

            client = ClientBuilder.newClient().target(DBPediaPropertyExtractor.convertDBPediaUrlToResourceUrl(dbpediaUri.toString()));
            weaponInfoResponse = client.request().get(String.class);
            weaponInfo = mapper.readTree(weaponInfoResponse).get(dbpediaUri.toString());

            Weapon weapon = new Weapon();
            weapon.setName(DBPediaPropertyExtractor.getName(weaponInfo));
            weapon.setWikiPageExternal(DBPediaPropertyExtractor.getPrimaryTopicOf(weaponInfo));
            weapon.setDescription(DBPediaPropertyExtractor.getAbstractDescription(weaponInfo));
            weapon.setThumbnails(DBPediaPropertyExtractor.getThumbnail(weaponInfo));
            weapon.setLength(DBPediaPropertyExtractor.getWeaponLength(weaponInfo));
            weapon.setWeight(DBPediaPropertyExtractor.getWeaponWeight(weaponInfo));
            weapon.setService(DBPediaPropertyExtractor.getService(weaponInfo));
            weapon.setDesigner(DBPediaPropertyExtractor.getDesigner(weaponInfo));
            weapon.setCaliber(DBPediaPropertyExtractor.getCaliber(weaponInfo));
            weapon.setOrigin(DBPediaPropertyExtractor.getOrigin(weaponInfo));

            return weapon;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
