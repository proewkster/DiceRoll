package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Users")
public class User {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;
    @Column(name = "Firstname")
    private String firstName;
    @Column(name = "Lastname")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "Birthdate")
    private Date birthdate;
    @Column(name = "PostalCode")
    private String zipCode;
    @Column(name = "City")
    private String city;
    @Column(name = "StreetAddress")
    private String streetAddress;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Column(name = "Created")
    private Date created;
    @Column(name = "Enabled")
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserAuthorities", joinColumns = @JoinColumn(name = "UserID"), inverseJoinColumns = @JoinColumn(name = "AuthorityID"))
    private List<Authority> authorities;

    //Default Getters and Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    //Constructors
    public User() {
    }

    //Methods

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", created=" + created +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                enabled == user.enabled &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                birthdate.equals(user.birthdate) &&
                zipCode.equals(user.zipCode) &&
                city.equals(user.city) &&
                streetAddress.equals(user.streetAddress) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(mobileNumber, user.mobileNumber) &&
                created.equals(user.created) &&
                authorities.equals(user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, password, birthdate, zipCode, city, streetAddress, phoneNumber, mobileNumber, created, enabled, authorities);
    }
}
