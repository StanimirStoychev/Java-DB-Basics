package bg.softuni.cardealer.config;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToStringConverter extends AbstractConverter<LocalDateTime, String> {
    @Override
    protected String convert(LocalDateTime source) {
        if (source == null) {
            return null;
        }
        return source.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
