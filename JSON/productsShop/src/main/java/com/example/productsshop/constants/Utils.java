package com.example.productsshop.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public enum Utils {
    ;
    public static void writeIntoJsonFile(List<?> objects, Path path) throws IOException {

        FileWriter fileWriter = new FileWriter(path.toFile());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static void writeIntoJsonFile(Object object, Path path) throws IOException {

        FileWriter fileWriter = new FileWriter(path.toFile());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static <T> void writeIntoXmlFile(T object, Path path) throws IOException, JAXBException {

        FileWriter fileWriter = new FileWriter(path.toFile());

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
