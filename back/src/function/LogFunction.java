package function;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFunction {

    public void LoggerFun(String type, String message) {
        String className =
                Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName =
                Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber =
                Thread.currentThread().getStackTrace()[2].getLineNumber();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        System.out.println(dateFormat.format(date));
        System.out.println("[" + dateFormat.format(date) + "] " + type.toUpperCase() + " " + className + " " + methodName + " " + lineNumber + " " + message);
    }

    public void Log(Logger logger,FileHandler fh) {

          logger.info(Thread.currentThread().getStackTrace()[2].getLineNumber() + "  you did it!!!!!!!!!!!!");


    }
}
