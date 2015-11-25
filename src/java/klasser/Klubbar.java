/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author h12augde
 */
@ManagedBean
@RequestScoped
public class Klubbar implements Serializable {
    private String namn;
    private int klubbID,seasonID,divID;

    public Klubbar(String namn, int klubbID) {
        this.namn = namn;
        this.klubbID = klubbID;
    }
    public Klubbar(){
        
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getDivID() {
        return divID;
    }

    public void setDivID(int divID) {
        this.divID = divID;
    }

    public int getKlubbID() {
        return klubbID;
    }

    public void setKlubbID(int klubbID) {
        this.klubbID = klubbID;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getNamn() {
        return namn;
    }
}
