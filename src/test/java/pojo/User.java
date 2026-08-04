package pojo;

public class User {

    private String password;
    private String name;
    private String email;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
