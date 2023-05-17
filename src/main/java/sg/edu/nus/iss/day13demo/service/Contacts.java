package sg.edu.nus.iss.day13demo.service;

import java.io.FileWriter;
import java.io.PrintWriter;

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
    
}