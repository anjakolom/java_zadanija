package ru.package1.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import ru.package1.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Date format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("FirstName_%s", i))
                    .withFirstName(String.format("FirstName_%s", i)).withMiddleName(String.format("MiddleName_%s", i)).withLastName(String.format("LastName_%s", i))
                    .withNickname(String.format("Nickname_%s", i)).withTitle(String.format("Title_%s", i)).withCompany(String.format("Company_%s", i)).withAddress(String.format("Address dom %s, kv _%s", i, i))
                    .withHomeTelephone(String.format("(495) 55555%s%s", i, i)).withMobileTelephone(String.format("+792602119%s%s", i, i)).withFaxTelephone(String.format("7-495-77744%s%s", i, i)).withWorkTelephone(String.format("Work_%s", i))
                    .withEmail(String.format("email%s@email.ru", i)).withBirthday(String.format("%s", i)).withBmonth("november").withYear("1982"));
                    //.withGroup("New_groups_1"));
        }
        return contacts;
    }
}
