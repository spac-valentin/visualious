package ro.visualious.responsegenerator.jsonmodel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by valentin.spac on 4/19/2016.
 */
@XmlRootElement
@JsonSerialize(using = JsonObject.CustomSerializer.class)
@Entity
public class JsonObject implements JsonType{
    private Map<String, JsonType> values;

    public JsonObject() {
        values = new HashMap<>();
    }

    public JsonObject(String key, String value) {
        values = new HashMap<>();
        JsonString s = new JsonString(value);
        addProperty(key, s);
    }

    public void addProperty(String key, JsonType value) {
        values.put(key, value);
    }

    public void addProperty(String key, String value) {
        JsonString s = new JsonString(value);
        values.put(key, s);
    }

    public Map<String, JsonType> getValues() {
        return values;
    }

    public static JsonObject addWithPredicate(String predicateName, String predicateValue, String value) {
        JsonObject finalObject = new JsonObject();
        JsonObject object = new JsonObject("predicate", predicateName);
        object.addProperty("value",  new JsonString(predicateValue));
        finalObject.addProperty(value, object);

        return finalObject;
    }


    static class CustomSerializer extends JsonSerializer<JsonObject> {
        public void serialize(JsonObject value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen = jgen.useDefaultPrettyPrinter();
            jgen.writeStartObject();
            for(Map.Entry<String, JsonType> obj : value.getValues().entrySet()) {
                jgen.writeObjectField(obj.getKey(), obj.getValue());
            }

            jgen.writeEndObject();
        }
    }
}
