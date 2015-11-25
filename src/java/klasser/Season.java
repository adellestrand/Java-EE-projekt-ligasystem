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
public class Season implements Serializable{
    private int seasonID, seasonName;
    /**
     * Creates a new instance of Season
     */
    public Season() {
    }

    public Season(int seasonID, int seasonName) {
        this.seasonID = seasonID;
        this.seasonName = seasonName;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(int seasonName) {
        this.seasonName = seasonName;
    }
    
}
