package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserChangePasswordDTO {

    // Attributes

    @NotBlank(message = "Geef uw oud wachtwoord in")
    public String oldPassword;

    @NotBlank(message = "Geef een nieuw wachtwoord in")
    @Size(min = 6, message="Wachtwoord moet minstens 6 karakters lang zijn")
    public String newPassword;

    @NotBlank(message = "Geef uw nieuw wachtwoord opnieuw in")
    public String confirmPassword;

    //Getters and Setters

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
