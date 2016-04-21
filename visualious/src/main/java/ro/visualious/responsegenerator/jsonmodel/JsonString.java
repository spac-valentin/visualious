package ro.visualious.responsegenerator.jsonmodel;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by valentin.spac on 4/19/2016.
 */
@XmlRootElement
@JsonSerialize(using = JsonString.CustomSerializer.class)
public class JsonString implements JsonType {
    @JsonUnwrapped
    private String value;

    public JsonString() {
    }

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    static class CustomSerializer extends JsonSerializer<JsonString> {
        public void serialize(JsonString value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen = jgen.useDefaultPrettyPrinter();
            jgen.writeString(value.getValue());

        }
    }
}
