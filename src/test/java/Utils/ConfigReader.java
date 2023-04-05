package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;
    public static Properties readProperties(){
        try {
            FileInputStream file=new FileInputStream(Constants.PROPERTY_FILE_PATH);
            properties=new Properties();
            properties.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties File!!!!");
        } catch (IOException e) {
            throw new RuntimeException("Load file");
        }
        return properties;
    }

    public static String getPropertyValue(String propertyKey){
        return properties.getProperty(propertyKey);
    }
}
