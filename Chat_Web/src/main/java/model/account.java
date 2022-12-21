package model;

public class account {

    private int aid;
    private String email;

    private String password;
    private model.user user;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public model.user getUser() {
        return user;
    }

    public void setUser(model.user user) {
        this.user = user;
    }
}
