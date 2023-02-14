package utilities;

import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
    //Properties class is essentially created to handle .properties files
    public static Properties properties;

    public static void initializeProperties(){
        try {
            //Here we are creating an object of FileReader and passing the path to it.
            //FileReader is a class in java which is used to read info from files
            //It throws an exception which is why we have it surrounded in a try catch block
            FileReader fileReader = new FileReader("src/test/resources/config.properties");
            //Here we are initializing our Properties object
            properties = new Properties();
            //Here we are using the .load(fileReader) method in order to pass the file reader to the properties object
            properties.load(fileReader);
        } catch (Exception e){
            //Here we are just printing the exception stack trace if there is one caught
            e.printStackTrace();
        }
    }

    public static String getConfigProperty(String key){
        //Here we are returning the value stored inside of the key passed as a argument to this method
        return properties.getProperty(key);
    }
}
