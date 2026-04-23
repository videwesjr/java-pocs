import java.io.*;
import java.util.Properties;

public class PropertiesHandler {

    private final Properties properties;

    public PropertiesHandler(String fileName) throws IOException {
        this.properties = new Properties();

        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            this.properties.load(is);
        } else {
            File file = new File(fileName);
            if (file.exists()) {
                try (InputStream fileIs = new FileInputStream(file)) {
                    this.properties.load(fileIs);
                }
            }
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public void save(String outputPath, String comments) throws IOException {
        File file = new File(outputPath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (OutputStream os = new FileOutputStream(file)) {
            this.properties.store(os, comments);
        }
    }
}
