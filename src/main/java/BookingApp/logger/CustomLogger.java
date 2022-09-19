package BookingApp.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static BookingApp.utils.FilePath.logFile;

public class CustomLogger {

    private static final Logger logger = Logger.getLogger(CustomLogger.class.getSimpleName());

    private static FileHandler fileHandler = null;

    public static void info(String message) {
        logger.setUseParentHandlers(false);
        try {
            // This block configures the logger with handler and formatter
            fileHandler = new FileHandler(logFile, true);
            fileHandler.setLevel(Level.INFO);
            logger.addHandler(fileHandler);

            CustomFormatter fileFormatter = new CustomFormatter();
            fileHandler.setFormatter(fileFormatter);

            // the following statement is used to log any messages
            logger.info(message);

            fileHandler.close();

        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }


    public static void error(String message) {
        logger.setUseParentHandlers(false);
        try {

            // This block configures the logger with handler and formatter
            fileHandler = new FileHandler(logFile, true);
            logger.addHandler(fileHandler);

            CustomFormatter fileFormatter = new CustomFormatter();
            fileHandler.setFormatter(fileFormatter);

            // the following statement is used to log any messages
            logger.log(new CustomLevel("ERROR", 1100), message);

            fileHandler.close();

        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
