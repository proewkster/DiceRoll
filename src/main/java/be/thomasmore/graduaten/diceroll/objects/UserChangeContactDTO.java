package be.thomasmore.graduaten.diceroll.objects;

import javax.validation.constraints.NotBlank;

public class UserChangeContactDTO {

    // Attributes
    @NotBlank(message = "Geef Uw adres in")
    private String streetAddress;
    @NotBlank(message = "Geef Uw postcode in")
    private String zipCode;
    @NotBlank(message = "Geef Uw gemeente in")
    private String city;

    private String phoneNumber;
    private String mobileNumber;

    //Getters and Setters

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
