package be.thomasmore.graduaten.diceroll.objects;

public class UserDisplayModel {

    //Attributes
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

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

    public String getDisplayName() {
        return this.userId + " - " + this.email + " (" + this.firstName + " " + this.lastName + ")";
    }
}
