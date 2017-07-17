package dbService.dataSets;

/**
 * Created by alex on 17.7.17.
 */
@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private String login;
    private String firstName;
    private String lastName;
    private String password;

    @Override
    public String toString() {
        return "\n UsersDataSet{" +
                "Login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                "}";
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public UsersDataSet(String firstName, String lastName, String login, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
