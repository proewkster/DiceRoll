package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserChangeEmailDTO {

    // Attributes

    @NotBlank(message = "Geef een nieuw emailadres in")
    @Email(message = "Geef een geldig email adres in")
    private String newEmail;

    @NotBlank(message = "Geef uw wachtwoord in om de wijzigingen door te voeren")
    private String verifyPassword;

    //Getters and Setters

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
