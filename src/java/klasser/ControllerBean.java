/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author h12augde
 */
@ManagedBean
@ViewScoped
public class ControllerBean implements Serializable, ValueChangeListener {
    private DBConnect dbcon;
    private List<Season> season;
    private int selectedSeason, selectedDiv;
    private ArrayList<Matcher> matcher;
    private ArrayList<Tabell> tabell;
    private boolean logedin = false;
    private User user = new User();
    
    /**
     * Creates a new instance of ControllerBean
     */
    public ControllerBean() throws SQLException {
        dbcon = new DBConnect();
        season = new ArrayList();
        matcher = new ArrayList();
        tabell = new ArrayList();
        
    }
    public ArrayList<Tabell> getTabell() throws SQLException{
        return tabell;
    }
    public List<Season> getSeason() throws SQLException{
        season = dbcon.getSeasons();
        return season;
    }
    public ArrayList<Div> getDivision() throws SQLException{
        return dbcon.getDiv(selectedSeason);
    }
    //http://www.tutorialspoint.com/jsf/jsf_valuechangelistener_tag.htm
    public void seasonChanged(ValueChangeEvent e){
        //assign new value to country
        selectedSeason = Integer.parseInt(e.getNewValue().toString()); 
    }
    public void divChanged(ValueChangeEvent e) throws SQLException{
            selectedDiv = Integer.parseInt(e.getNewValue().toString());
            if(user.isLoggedin() && user.getPremission() != 0){
                matcher = dbcon.getAdminMatcher(selectedDiv, selectedDiv, user.getPremission());
            }else{
                matcher = dbcon.getMatcher(selectedDiv, selectedSeason);
                tabell = dbcon.getTabell(selectedSeason, selectedDiv);
            }
            
    }
    public ArrayList<Matcher> getArray(){
        return matcher;
    }
    public ArrayList<Matcher> getMatcher() {
        return matcher;
    }
    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
       System.out.println(event.getNewValue());
    }
}
