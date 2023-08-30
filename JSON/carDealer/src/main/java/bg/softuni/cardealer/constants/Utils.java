package bg.softuni.cardealer.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;

    public static void writeIntoJsonFile(List<?> objects, Path path) throws IOException {

        FileWriter fileWriter = new FileWriter(path.toFile());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
