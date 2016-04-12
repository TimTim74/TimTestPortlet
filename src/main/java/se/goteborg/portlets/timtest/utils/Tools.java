package se.goteborg.portlets.timtest.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class Tools {

    public static void putMetaInMap(Iterator iterator, Map allMetaData, Logger logger) {
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (logger != null) {
                logger.fine("    Key = " + key + ", Value = " + value);
            }
            allMetaData.put(key, value);
        }
    }

}
