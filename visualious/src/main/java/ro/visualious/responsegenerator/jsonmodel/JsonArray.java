package ro.visualious.responsegenerator.jsonmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by valentin.spac on 4/19/2016.
 */
@XmlRootElement
@JsonSerialize(using = JsonArray.CustomSerializer.class)
public class JsonArray implements JsonType {
    private List<JsonType> value = new ArrayList<>();

    public JsonArray() {

    }

    public void addValue(JsonType obj) {
        value.add(obj);
    }

    public void addValue(String obj) {
        value.add(new JsonString(obj));
    }

    public void addValue(Collection<String> values) {
        this.value.addAll(values.stream().map(JsonString::new).collect(Collectors.toList()));
    }

    public List<JsonType> getValue() {
        return value;
    }

    public void setValue(List<JsonType> value) {
        this.value = value;
    }

    static class CustomSerializer extends JsonSerializer<JsonArray> {
        public void serialize(JsonArray value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen = jgen.useDefaultPrettyPrinter();
            jgen.writeStartArray();
            for (JsonType e : value.getValue()) {
                jgen.writeObject(e);
            }
            jgen.writeEndArray();
        }
    }
}
