/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author h12augde
 */
@ManagedBean
@SessionScoped
public class User implements Serializable{
    private String user, password;
    private int premission;
    private boolean loggedin = false;

    public User(String user, String password, int premission) {
        this.user = user;
        this.password = password;
        this.premission = premission;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public int getPremission() {
        return premission;
    }

    public void setPremission(int premission) {
        this.premission = premission;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Creates a new instance of User
     */
    public User() {
        loggedin = false;
    }
    
}
