package be.thomasmore.graduaten.diceroll.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Users")
public class User implements UserDetails {
    //User class implements UserDetails so this object can be used on the SecurityConfiguration for authentication and authorization purposes
    //Because of this, some default getters needed an Override of the interface
    //These methods are grouped further down in the class and replace the default getters for this class

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private int userId;
    @Column(name="Firstname")
    private String firstName;
    @Column(name="Lastname")
    private String lastName;
    @Column(name="Email")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="Birthdate")
    private Date birthdate;
    @Column(name="PostalCode")
    private String zipCode;
    @Column(name="City")
    private String city;
    @Column(name="StreetAddress")
    private String streetAddress;
    @Column(name="PhoneNumber")
    private String phoneNumber;
    @Column(name="MobileNumber")
    private String mobileNumber;
    @Column(name="Created")
    private Date created;
    @Column(name="Enabled")
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "UserAuthorities",joinColumns=@JoinColumn(name = "UserID"),inverseJoinColumns=@JoinColumn(name = "AuthorityID"))
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    //Methods for UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Convert database values to values of type GrantedAuthority for authorization purposes
        Set<GrantedAuthority> auth = new HashSet<>();
        this.authorities.forEach(x -> auth.add(new SimpleGrantedAuthority("ROLE_" + x.getName()))); //Append "ROLE_" before actual Authority. Spring Security expects this
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    //Hardcoded, not used
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //Hardcoded, not used
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //Hardcoded, not used
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
