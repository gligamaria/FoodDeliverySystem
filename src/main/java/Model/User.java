package Model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userID;
    private String username;
    private String password;
    private String name;
    private userType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Integer userID, String username, String password, String name, userType type){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public userType getType() {
        return type;
    }

    public void setType(userType type) {
        this.type = type;
    }
}
