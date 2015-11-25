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
public class Tabell implements Serializable {
    private String lagNamn;
    private int divID, seasonID, matcher, vinster, oavgjorda, forluster, poang;
    /**
     * Creates a new instance of Tabell
     */
    public Tabell() {
    }

    public Tabell(String lagNamn, int divID, int seasonID, int matcher, int vinster, int oavgjorda, int forluster, int poang) {
        this.lagNamn = lagNamn;
        this.divID = divID;
        this.seasonID = seasonID;
        this.matcher = matcher;
        this.vinster = vinster;
        this.oavgjorda = oavgjorda;
        this.forluster = forluster;
        this.poang = poang;
    }

    public String getLagNamn() {
        return lagNamn;
    }

    public void setLagNamn(String lagNamn) {
        this.lagNamn = lagNamn;
    }

    public int getDivID() {
        return divID;
    }

    public void setDivID(int divID) {
        this.divID = divID;
    }

    public int getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getMatcher() {
        return matcher;
    }

    public void setMatcher(int matcher) {
        this.matcher = matcher;
    }

    public int getVinster() {
        return vinster;
    }

    public void setVinster(int vinster) {
        this.vinster = vinster;
    }

    public int getOavgjorda() {
        return oavgjorda;
    }

    public void setOavgjorda(int oavgjorda) {
        this.oavgjorda = oavgjorda;
    }

    public int getForluster() {
        return forluster;
    }

    public void setForluster(int forluster) {
        this.forluster = forluster;
    }

    public int getPoang() {
        return poang;
    }

    public void setPoang(int poang) {
        this.poang = poang;
    }
    
}
