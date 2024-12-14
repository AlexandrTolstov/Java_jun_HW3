package ru.gradle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void savePersonToFile(String fileName, Person person) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), person);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(person);
                }
            } else if (fileName.endsWith(".xml")) {
//                String s = xmlMapper.writeValueAsString(tasks);
                xmlMapper.writeValue(new File(fileName), person);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person loadPersonFromFile(String fileName) {
        Person person = null;

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    person = objectMapper.readValue(file, objectMapper.getTypeFactory().constructType(Person.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        person = (Person)ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    person = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructType(Person.class));
                }
            } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        }
        return person;
    }

}
