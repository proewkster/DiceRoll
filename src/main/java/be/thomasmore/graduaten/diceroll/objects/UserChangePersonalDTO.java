package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class UserChangePersonalDTO {

    // Attributes

    @NotBlank(message = "Geef Uw voornaam in")
    private String firstName;
    @NotBlank(message = "Geef Uw achternaam in")
    private String lastName;
    @NotBlank(message = "Selecteer Uw geboortedatum")
    private Date birthDate;

    //Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
