package sg.edu.nus.iss.day13demo.service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import sg.edu.nus.iss.day13demo.model.Contact;

@Component
public class Contacts {

    public void save(Contact contact, Model model, String dataDir) throws Exception {

        String fileName = contact.getId();
        PrintWriter printWriter = new PrintWriter(new FileWriter(dataDir + "/" + fileName + ".txt"));

        printWriter.println(contact.getName());
        printWriter.println(contact.getEmail());
        printWriter.println(contact.getPhoneNumber());
        printWriter.println(contact.getDateOfBirth());

        printWriter.flush();
        printWriter.close();
    }

    public Contact getContactByID(String contactID, String dataDir) throws Exception {
        Contact ctc = new Contact();

        java.nio.file.Path filePath = new File(dataDir + "/" + contactID + ".txt").toPath();
        System.out.println("--->" + filePath);

        List<String> stringList = new ArrayList<String>();

        stringList = Files.readAllLines(filePath);

        ctc.setId(contactID);
        ctc.setName(stringList.get(0));
        ctc.setEmail(stringList.get(1));
        ctc.setPhoneNumber(stringList.get(2));
        ctc.setDateOfBirth(LocalDate.parse(stringList.get(3)));

        return ctc;
    }

    public void getAllContactsInURI(Model model, String dataDir) {
        Set<String> dataFiles = listFiles(dataDir);

        Set<String> modifiedFiles = new HashSet<String>();

        for (String file : dataFiles) {

            String modifiedFile = file.replace(".txt", "");
            modifiedFiles.add(modifiedFile);

        }

        model.addAttribute("contacts", modifiedFiles.toArray(new String[dataFiles.size()]));
    }
    
    private Set<String> listFiles(String dataDir) {
        
        return Stream.of(new File(dataDir).listFiles())
        .filter(file -> !file.isDirectory())
        .map(File :: getName).collect(Collectors.toSet());  
    }
}