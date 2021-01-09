package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserDTO {

    //Attributes

    @NotBlank(message = "Gelieve je voornaam in te geven")
    private String firstname;

    @NotBlank(message = "Gelieve je achternaam in te geven")
    private String lastname;

    @NotBlank(message = "Gelieve je emailadres in te geven")
    @Email(message = "Gelieve een geldig emailadres in te geven")
    private String email;

    @NotBlank(message = "Gelieve een wachtwoord in te geven")
    @Size(min = 6, message = "Het wachtwoord moet uit minstens 6 tekens bestaan")
    private String password;

    @NotBlank(message = "Gelieve je wachtwoord te bevestigen")
    private String confirmPassword;

    @NotBlank(message = "Selecteer je geboortedatum")
    private String birthdate;

    @NotBlank(message = "Gelieve een postcode in te geven")
    private String zipcode;

    @NotBlank(message = "Gelieve een gemeente in te geven")
    private String city;

    @NotBlank(message = "Gelieve straat en huisnummer in te geven")
    private String streetAddress;

    private String phoneNumber;
    private String mobileNumber;

    //Constructors
    public RegisterUserDTO() {
    }

    //Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    //Methods
    @Override
    public String toString() {
        return "UserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
