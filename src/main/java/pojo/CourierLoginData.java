package pojo;

public class CourierLoginData {
    private String login;
    private String password;


    public CourierLoginData() {
    }

    public CourierLoginData(String password) {
        this.password = password;
    }

    public CourierLoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
