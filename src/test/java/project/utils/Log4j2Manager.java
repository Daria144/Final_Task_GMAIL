package project.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4j2Manager {
        private static Logger log= LogManager.getLogger(Log4j2Manager.class.getName());
        public static void info(String message) {
            log.info(message);
        }
        public static void error(String message,Throwable t) {
            log.error(message, t);
        }
}
