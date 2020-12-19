package be.thomasmore.graduaten.diceroll.objects;

import be.thomasmore.graduaten.diceroll.entity.Authority;

import java.sql.Date;
import java.util.List;

public class UserMgmtDTO {

    // Attributes

    private int userId;

    private String firstName;

    private String lastName;

    private String email;

    private Date birthDate;

    private String zipCode;

    private String city;

    private String streetAddress;

    private String phoneNumber;

    private String mobileNumber;

    private Date created;

    private boolean enabled;

    private boolean UserRole;

    private boolean AdminRole;

    private List<Authority> authorities;

    private String errors = "";
    private String validation = "";

    // Constructor

    public UserMgmtDTO() {
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isUserRole() {
        return UserRole;
    }

    public void setUserRole(boolean userRole) {
        UserRole = userRole;
    }

    public boolean isAdminRole() {
        return AdminRole;
    }

    public void setAdminRole(boolean adminRole) {
        AdminRole = adminRole;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    // Methods

    public void setUserRoles() {
        if (this.authorities != null) {
            for (Authority auth : this.authorities) {
                if (auth.getName().equals("User")) {
                    this.setUserRole(true);
                }
                if (auth.getName().equals("Admin")) {
                    this.setAdminRole(true);
                }
            }
        }
    }
}
