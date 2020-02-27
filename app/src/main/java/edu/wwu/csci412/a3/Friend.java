package edu.wwu.csci412.a3;

public class Friend {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Integer id;

    public Friend(Integer id, String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.emailAddress = email;
        this.id = id;
    }

    public Friend() {}

    public String toString() {
        return id + " " + firstName + " " + lastName + " " + emailAddress;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
