/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author h12augde
 */
@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {
    private ArrayList<Matcher> matcher;
    private ArrayList<Season> season;
    private int selectedSeason, selectedDiv;
    private User user = new User();
    private DBConnect dbcon;
    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
        dbcon = new DBConnect();
        season = new ArrayList();
        matcher = new ArrayList();
    }
    //http://www.tutorialspoint.com/jsf/jsf_valuechangelistener_tag.htm
    public void seasonChanged(ValueChangeEvent e){
        //assign new value to country
        selectedSeason = Integer.parseInt(e.getNewValue().toString()); 
    }
    public void divChanged(ValueChangeEvent e) throws SQLException{
        selectedDiv = Integer.parseInt(e.getNewValue().toString());
        matcher = dbcon.getAdminMatcher(selectedDiv, selectedSeason, user.getPremission());
    }
    public ArrayList<Matcher> getMatcher() {
        return matcher;
    }
    public ArrayList<Matcher> getArray(){
        return matcher;
    }
    public ArrayList<Season> getSeason() throws SQLException{
        season = dbcon.getSeasons();
        return season;
    }
    public ArrayList<Div> getDivision() throws SQLException{
        return dbcon.getDiv(selectedSeason);
    }
    public boolean isLogedin(){
        return user.isLoggedin();
    }
    public void check(ComponentSystemEvent event) throws IOException{
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav 
		   = (ConfigurableNavigationHandler) 
			fc.getApplication().getNavigationHandler();
        if(user.isLoggedin()){
            nav.performNavigation("admin");
        }else{
            nav.performNavigation("login");
        }
    }
    public String logout(){
        user.setLoggedin(false);
        return "login.xhtml";
    }
    public String setEditable(Matcher match) throws SQLException{
        match.setEditable(true);
        dbcon.updateMatch(match);
        return null;
    }
    public String saveAction(ArrayList<Matcher> match) throws SQLException{
        for(Matcher m : match){
            if(m.isEditable()){
                m.setEditable(false);
                System.out.println(m.getScoreAway());
                dbcon.updateMatch(m);
            }
        }
        return null;
    }
    public String login(User user) throws SQLException{
        this.user = dbcon.login(user);
        if(this.user.isLoggedin()){
            return "admin.xhtml";
        }else{
            return null;
        }
    }
    
}
