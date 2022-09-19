package BookingApp.logger;

import java.util.logging.Level;

public class CustomLevel extends Level {
    private static final long serialVersionUID = 260050453223002526L;
    private static final String defaultBundle = "sun.util.logging.resources.logging";
    public CustomLevel(String name, int value) {
        super(name, value, defaultBundle);
    }
}
