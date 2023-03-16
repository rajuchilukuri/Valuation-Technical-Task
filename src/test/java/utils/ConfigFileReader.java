package utils;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;

    public ConfigFileReader() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String filePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            properties = new Properties();

            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + filePath);
        }
    }

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else
            throw new RuntimeException("url not specified in the config file.");
    }

    public String getInputFile() {
        String inPutFilePath = properties.getProperty("inPutFilePath");
        if (inPutFilePath != null) return inPutFilePath;
        else
            throw new RuntimeException("inPutFilePath not specified in the config file.");
    }

    public String getOutputFile() {
        String outPutFilePath = properties.getProperty("outPutFilePath");
        if (outPutFilePath != null) return outPutFilePath;
        else
            throw new RuntimeException("outPutFilePath not specified in the config file.");
    }

    public String getRegistrationNoPattern() {
        String regNoPattern = properties.getProperty("registrationNoPattern");
        if (regNoPattern != null) return regNoPattern;
        else
            throw new RuntimeException("regNoPattern not specified in the config file.");
    }
}
