package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader() {
        try (InputStream input = new FileInputStream("src/test/resources/data.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getUserName() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getWrongEmail() {
        return properties.getProperty("wrong.email");
    }

    public String getWrongUserName() {
        return properties.getProperty("wrong.username");
    }

    public String getWrongPassword() {
        return properties.getProperty("wrong.password");
    }

    public String getWebTitle() {
        return properties.getProperty("web.title");
    }

    public String getNativeTitle() {
        return properties.getProperty("native.title");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getKeyWord() {
        return properties.getProperty("keyWord");
    }
}
