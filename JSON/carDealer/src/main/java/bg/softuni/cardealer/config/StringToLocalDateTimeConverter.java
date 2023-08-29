package bg.softuni.cardealer.config;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter extends AbstractConverter<String, LocalDateTime> {
    @Override
    protected LocalDateTime convert(String source) {
        if (source == null) {
            return null;
        }
        return LocalDateTime.parse(source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

