package br.com.carpediemsystem.deserializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        
        if (value != null && !value.isEmpty()) {
            try {
                // Remove vírgulas e tenta parsear o valor
                return new BigDecimal(value.replace(",", ""));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Failed to parse BigDecimal", e);
            }
        }
        return null;
    }
}
