package coe.datacollection.EntityDependencies;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class URankDeserializer extends StdDeserializer<URank> {

    public URankDeserializer() {
        this(null);
    }

    public URankDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public URank deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException {
        String name = jp.getText();
        return new URank(name);
    }
}

