package BookingApp.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime date = LocalDateTime.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        builder
                .append(formattedDate)
                .append(" [")
                .append(record.getSourceMethodName().toUpperCase())
                .append("] ")
                .append(record.getLoggerName())
                .append(" : ")
                .append(record.getMessage())
                .append("\n");

        return builder.toString();
    }
}
