package br.com.match.licit.profile.enterprise.entity.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return "{" + String.join(SEPARATOR, attribute) + "}";
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty() || "{}".equals(dbData)) {
            return Collections.emptyList();
        }
        String cleanedData = dbData.substring(1, dbData.length() - 1);
        if (cleanedData.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList(cleanedData.split(SEPARATOR)));
    }
}
