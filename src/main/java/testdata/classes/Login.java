package testdata.classes;

import utils.Reader;

public class Login {
    private String username;
    private String password;

    public Login(String filename) {
        this.username = Reader.json(filename).get("username").toString();
        this.password = Reader.json(filename).get("password").toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
