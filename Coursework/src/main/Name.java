package main;

/**
 * Represents a person's name.
 */
public class Name {

    private String firstName, lastName;

    /**
     * Constructs a Name object.
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** @return first name */
    public String getFirstName() {
        return firstName;
    }

    /** @return last name */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns initials of the name.
     *
     * @return initials in uppercase
     */
    public String getInitials() {
        return ("" + firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
    }

    /**
     * @return full name
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
