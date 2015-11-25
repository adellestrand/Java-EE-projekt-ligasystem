/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell_000 kopplingen mot databasen
 */
public class DBConnect {

    String db = "jdbc:mysql://adellestrand.ddns.net:3306/Inlamningsuppgift1?user=admin&password=admin";
    Connection con;
    Statement st;

    //konstruktor

    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(db);

        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User login(User user) throws SQLException {
        st = con.createStatement();
        User usern = new User();
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE password='" +user.getPassword()+ "' AND email='" + user.getUser() + "'");
        if (rs.next()) {
                usern.setPremission(rs.getInt("klubbID"));
                usern.setLoggedin(true);
        }
        return usern;
    }

    //för att hämta de olika kategorerna i databasen

    public void addExcel() throws SQLException {
        ImportExcel excel = new ImportExcel();
        ArrayList<Div> div = excel.getDivision();
        ArrayList<Klubbar> klubbar = excel.getKlubbar();
        PreparedStatement ps = con.prepareStatement("INSERT INTO season (year) VALUES (?)");
        ps.setInt(1, 2000);
        ps.executeUpdate();
        ps = con.prepareStatement("INSERT INTO klubbar (clubName, seasonID, divID) VALUES (?,?,?)");
        for (Klubbar k : klubbar) {
            ps.setString(1, k.getNamn());
            ps.setInt(2, 1);
            ps.setInt(3, k.getDivID());
            ps.executeUpdate();
        }
        ps = con.prepareStatement("INSERT INTO serier (divID, divName, seasonID) VALUES (?, ?,?)");
        for (Div d : div) {
            ps.setInt(1, d.getId());
            ps.setString(2, d.getNamn());
            ps.setInt(3, 1);
            ps.executeUpdate();
        }
        ps.close();
        setMatcher(1,1);
    }
    public void setMatcher(int divID, int seasonID) throws SQLException{
        ArrayList<Klubbar> klubbar = new ArrayList();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM klubbar WHERE divID =? and seasonID =?");
        ps.setInt(1, divID);
        ps.setInt(2, seasonID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Klubbar klubb = new Klubbar();
            klubb.setKlubbID(rs.getInt("klubbID"));
            klubb.setNamn(rs.getString("clubName"));
            klubb.setSeasonID(rs.getInt("seasonID"));
            klubb.setDivID(rs.getInt("divID"));
            klubbar.add(klubb);
        }
        ImportExcel excel = new ImportExcel();
        excel.listMatches(klubbar);
        ArrayList<Matcher> matcherna = excel.getMatcher();
        ps = con.prepareStatement("INSERT INTO matcher (divID, seasonID, hLagID, bLagID, dag) VALUES (?,?,?,?,?)");
        for(Matcher m : matcherna){
            ps.setInt(1, m.getDivID());
            ps.setInt(2, m.getSeasonID());
            ps.setInt(3, m.getHlagID());
            ps.setInt(4, m.getBlagID());
            ps.setInt(5, m.getDag());
            ps.executeUpdate(); 
        }
        ps.close();
        setTabell(divID,seasonID);
    }
    public void setTabell(int divID, int seasonID) throws SQLException{
        ArrayList<Klubbar> klubbar = new ArrayList(); 
        PreparedStatement ps = con.prepareStatement("SELECT * FROM klubbar WHERE divID="+divID+" AND seasonID="+seasonID+"");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Klubbar klubb = new Klubbar();
            klubb.setKlubbID(rs.getInt("klubbID"));
            klubb.setNamn(rs.getString("clubName"));
            klubb.setSeasonID(rs.getInt("seasonID"));
            klubb.setDivID(rs.getInt("divID"));
            klubbar.add(klubb);
        }
        ps = con.prepareStatement("INSERT INTO tabell (divID, seasonID, klubbID) VALUES (?,?,?)");
        for(Klubbar k : klubbar){
            ps.setInt(1, k.getDivID());
            ps.setInt(2, k.getSeasonID());
            ps.setInt(3, k.getKlubbID());
            ps.executeUpdate(); 
        }
        ps.close();
    }

    public ArrayList<Matcher> getMatcher(int divID,int seasonID) throws SQLException {
        st = con.createStatement();
        ArrayList<Matcher> matcher = new ArrayList();
        ResultSet rs = st.executeQuery("SELECT * FROM matcher WHERE divID ="+divID+" AND seasonID="+seasonID+" "
                + "ORDER BY dag ASC");
        while (rs.next()) {
            Matcher match = new Matcher();
            String hKlubbNamn = getKlubbNamn(rs.getInt("hLagID"));
            match.setHlag(hKlubbNamn);
            String bKlubbNamn = getKlubbNamn(rs.getInt("bLagID"));
            match.setBlag(bKlubbNamn);
            match.setDag(rs.getInt("dag"));
            match.setDivID(rs.getInt("divID"));
            match.setScoreHome(rs.getInt("scoreHome"));
            match.setScoreAway(rs.getInt("scoreAway"));
            match.setMatchID(rs.getInt("matchID"));
            match.setSeasonID(rs.getInt("seasonID"));
            if(rs.getInt("editable") == 0){
                match.setEditable(false);
            }else{
                match.setEditable(true); 
            }
            matcher.add(match);
        }
        return matcher;
    }
    public ArrayList<Matcher> getAdminMatcher(int divID,int seasonID, int klubbID) throws SQLException {
        st = con.createStatement();
        ArrayList<Matcher> matcher = new ArrayList();
        ResultSet rs = st.executeQuery("SELECT * FROM matcher WHERE divID ="+divID+" AND seasonID="+seasonID+" "
                + "AND hLagID="+klubbID+" OR bLagID="+klubbID+" "
                + "ORDER BY dag ASC");
        while (rs.next()) {
            Matcher match = new Matcher();
            String hKlubbNamn = getKlubbNamn(rs.getInt("hLagID"));
            match.setHlag(hKlubbNamn);
            String bKlubbNamn = getKlubbNamn(rs.getInt("bLagID"));
            match.setBlag(bKlubbNamn);
            match.setDag(rs.getInt("dag"));
            match.setDivID(rs.getInt("divID"));
            match.setScoreHome(rs.getInt("scoreHome"));
            match.setScoreAway(rs.getInt("scoreAway"));
            match.setMatchID(rs.getInt("matchID"));
            match.setSeasonID(rs.getInt("seasonID"));
            if(rs.getInt("editable") == 0){
                match.setEditable(false);
            }else{
                match.setEditable(true); 
            }
            matcher.add(match);
        }
        return matcher;
    }
    public void updateMatch(Matcher match) throws SQLException{
        try (PreparedStatement ps = con.prepareStatement("UPDATE matcher SET scoreHome=?, scoreAway=?, editable=? WHERE matchID=?")) {
            ps.setInt(1, match.getScoreHome());
            System.out.println(match.getScoreAway());
            ps.setInt(2, match.getScoreAway());
            
            if(match.isEditable()){
                ps.setInt(3, 1);
            }else{
                ps.setInt(3, 0);
            }
            ps.setInt(4, match.getMatchID());
            ps.executeUpdate();
        }
       
   }
    public ArrayList<Season> getSeasons() throws SQLException{
        ArrayList<Season> seasons = new ArrayList();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM season");
        while (rs.next()) {
            Season season = new Season();
            season.setSeasonName(rs.getInt("year"));
            season.setSeasonID(rs.getInt("seasonID"));
            seasons.add(season);
        }
        return seasons;
    }
    public ArrayList<Div> getDiv(int seasonID) throws SQLException{
        ArrayList<Div> divs = new ArrayList();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM serier WHERE seasonID="+seasonID+"");
        while (rs.next()) {
            Div div = new Div();
            div.setId(rs.getInt("divID"));
            div.setNamn(rs.getString("divName"));
            divs.add(div);
        }
        return divs;
    }
    public ArrayList<Tabell> getTabell(int seasonID, int divID) throws SQLException{
        ArrayList<Tabell> tabell = new ArrayList();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM tabell WHERE seasonID ="+seasonID+" AND divID="+divID+" "
                + "ORDER BY poang DESC");
        while (rs.next()) {
            Tabell tabellen = new Tabell();
            tabellen.setDivID(rs.getInt("divID"));
            tabellen.setSeasonID(rs.getInt("seasonID"));
            String klubbNamn = getKlubbNamn(rs.getInt("klubbID"));
            tabellen.setLagNamn(klubbNamn);
            tabellen.setMatcher(rs.getInt("antalMatcher"));
            tabellen.setVinster(rs.getInt("antalVinster"));
            tabellen.setOavgjorda(rs.getInt("antalOavgjorda"));
            tabellen.setForluster(rs.getInt("antalForluster"));
            tabellen.setPoang(rs.getInt("poang"));
            tabell.add(tabellen);
        }
        return tabell;
    }
    public String getKlubbNamn(int klubbID) throws SQLException{
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM klubbar WHERE klubbID="+klubbID+"");
        String namn = "";
        while (rs.next()) {
            namn = rs.getString("clubName");
        }
        return namn;
        
    }
    
   //kollar om det användarnamn och det krypterade lösen ordet finns i databasen 
    //om det finns så får man 1 annars 0

    public boolean password(String pass, String user) throws SQLException {
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT EXISTS(SELECT 1 FROM users WHERE encrypted_pass='" + pass + "' AND user_name='" + user + "')");
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void close() {
        try {
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
