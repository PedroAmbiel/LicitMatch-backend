package br.com.match.licit.profile.enterprise.entity.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class LongListConverter implements AttributeConverter<List<Long>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }

        String joined = attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SEPARATOR));

        return "{" + joined + "}";
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty() || "{}".equals(dbData)) {
            return Collections.emptyList();
        }

        String cleanedData = dbData.substring(1, dbData.length() - 1);
        if (cleanedData.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(cleanedData.split(SEPARATOR))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
