package ru.gradle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Ваня", 25);
        Serializer.savePersonToFile("Person.json", person);
        Serializer.savePersonToFile("Person.bin", person);
        Serializer.savePersonToFile("Person.xml", person);

        System.out.println("Серриализация завершина");

        Person personJson = Serializer.loadPersonFromFile("Person.json");
        System.out.println("json person: \n" + personJson);
        Person personBin = Serializer.loadPersonFromFile("Person.bin");
        System.out.println("bin person: \n" + personBin);
        Person personXml = Serializer.loadPersonFromFile("Person.xml");
        System.out.println("xml person: \n" + personXml);

    }
}