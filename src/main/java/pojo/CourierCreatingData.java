package pojo;

public class CourierCreatingData {

    private String login;
    private String password;
    private String firstName;

    public CourierCreatingData(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public CourierCreatingData(String login) {
        this.login = login;
    }

    public CourierCreatingData(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierCreatingData() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
