package ctm.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
    /**
     * This class loads the property file 
     * Property file contains information like browser to use URL etc
     * 
     */
	
	Properties properties = new Properties();
    InputStream inputStream = null;

    // Constructor to initiate 
    public PropertyReader() {
        loadProperties();
    }

    /*
	 * ****************************************************************************************************************************
	 * This method loads property file
	 * Arguments: NA
	 * ****************************************************************************************************************************
	 */
    private void loadProperties() {
        try {
            inputStream = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    // getter method
    public String readProperty(String key) {
        return properties.getProperty(key);
    }

}
