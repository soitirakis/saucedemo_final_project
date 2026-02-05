package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import testdata.classes.UserData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Writer {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String buildJsonPath(String fileNameNoExt) {
        return System.getProperty("user.dir")
                + "\\src\\main\\java\\testdata\\files\\"
                + fileNameNoExt + ".json";
    }

    public static void writeValidNewUser(UserData user, String fileNameNoExt) {
        String jsonPath = buildJsonPath(fileNameNoExt);

        try {
            Path parent = Path.of(jsonPath).getParent();
            if (parent != null) Files.createDirectories(parent);

            MAPPER.writeValue(new File(jsonPath), user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to: " + jsonPath, e);
        }
    }
}
