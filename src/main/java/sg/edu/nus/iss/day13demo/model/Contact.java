package sg.edu.nus.iss.day13demo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    
    @NotNull(message = "Name cannot be empty!")
    @Size(min=3, max=15, message="Name should be between 3 and 14 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @Size(min=8, message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth should not be from the future!")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Must be above 10 years old")
    @Max(value = 100, message = "Must be below 100 years old")
    private int age;

    private String id;

    public Contact() {
        this.id = generateID(8);
    }

    private String generateID(int i) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < i) {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, i);
    }

    public Contact(String name, String email, String phoneNumber, LocalDate dateofBirth, int age, String id) {

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateofBirth;
        this.age = age;
        this.id = id;

    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        // calculate the age
        int calculatedAge = 0;

        if (dateOfBirth != null) {
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        this.age = calculatedAge;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

}