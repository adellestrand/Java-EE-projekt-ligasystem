/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;


/**
 *
 * @author h12augde
 */
public class Matcher {
    private int divID,matchID,scoreHome,scoreAway, dag, seasonID,hlagID,blagID;
    private String hlag, blag;
    private boolean editable;

    public Matcher(int divID, int matchID, int scoreHome, int scoreAway, int dag, int seasonID, int hlagID, int blagID, String hlag, String blag) {
        this.divID = divID;
        this.matchID = matchID;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.dag = dag;
        this.seasonID = seasonID;
        this.hlagID = hlagID;
        this.blagID = blagID;
        this.hlag = hlag;
        this.blag = blag;
    }
    
    public Matcher() {
    }
    
    public boolean isEditable(){
        return editable;
    }
    
    public void setEditable(boolean editable){
        this.editable = editable;
    }
    
    public int getHlagID() {
        return hlagID;
    }

    public void setHlagID(int hlagID) {
        this.hlagID = hlagID;
    }

    public int getBlagID() {
        return blagID;
    }

    public void setBlagID(int blagID) {
        this.blagID = blagID;
    }

    public String getHlag() {
        return hlag;
    }

    public void setHlag(String hlag) {
        this.hlag = hlag;
    }

    public String getBlag() {
        return blag;
    }

    public void setBlag(String blag) {
        this.blag = blag;
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

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }
    
}
