// Done for now

public abstract class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Constructor
    public User() {}

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void showList();

    public abstract void addUser();

}

