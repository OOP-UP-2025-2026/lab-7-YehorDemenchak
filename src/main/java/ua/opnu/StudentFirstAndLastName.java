package ua.opnu;

public class StudentFirstAndLastName {
    private String firstName;
    private String lastName;

    public StudentFirstAndLastName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
