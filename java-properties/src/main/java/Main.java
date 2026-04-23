import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PropertiesHandler reader = new PropertiesHandler("application.properties");

        System.out.println("Reading from application.properties");
        System.out.println("app.name: " + reader.getProperty("app.name"));
        System.out.println("app.version: " + reader.getProperty("app.version"));
        System.out.println("java.version: " + reader.getProperty("java.version"));
        System.out.println("------------------------------------");

        PropertiesHandler writer = new PropertiesHandler("app.properties");

        writer.setProperty("env", "dev");
        writer.setProperty("test-property", "test");

        writer.save("src/main/resources/app.properties", "App properties created");

        System.out.println("app.properties file created.");
        PropertiesHandler newProperties = new PropertiesHandler("src/main/resources/app.properties");

        System.out.println("Reading from app.properties");
        System.out.println("env: " + newProperties.getProperty("env"));
        System.out.println("test-property: " + newProperties.getProperty("test-property"));
    }
}