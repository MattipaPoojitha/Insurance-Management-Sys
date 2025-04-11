package util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString(String filename) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);
            String host = props.getProperty("hostname");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");
            String user = props.getProperty("username");
            String password = props.getProperty("password");

            return "jdbc:mysql://" + host + ":" + port + "/" + dbname +
                    "?user=" + user + "&password=" + password +
                    "&useSSL=false&allowPublicKeyRetrieval=true";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
