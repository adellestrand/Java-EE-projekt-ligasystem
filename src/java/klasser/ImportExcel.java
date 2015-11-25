/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 * http://www.vogella.com/tutorials/JavaExcel/article.html
 * @author h12augde
 */
public class ImportExcel {
    private ArrayList<Klubbar> klubbar = new ArrayList();
    private ArrayList<Div> divisioner = new ArrayList();
    private ArrayList<Matcher> matcher = new ArrayList();
    public ImportExcel(){
        
    }
    public void startImport(){
        try {
            Workbook workbook = Workbook.getWorkbook
                    (new File("H:\\Documents\\NetBeansProjects\\anmalda_lag_till_serier_v1a.xls"));
            Sheet sheet = workbook.getSheet(0);
            
            for(int j = 0; j < sheet.getColumns(); j++){
                for(int i = 0; i < sheet.getRows(); i++){
                    Cell cell = sheet.getCell(j, i);
                    if(i == 0 && cell.getContents() != ""){
                        Div div = new Div();
                        div.setId(j+1);
                        div.setNamn(cell.getContents());
                        divisioner.add(div);
                    }else if(cell.getContents() != ""){
                        Klubbar klubb = new Klubbar();
                        klubb.setDivID(j+1);
                        klubb.setNamn(cell.getContents());
                        klubbar.add(klubb);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Klubbar> getKlubbar(){
        return klubbar;
    }
    public ArrayList<Div> getDivision(){
        return divisioner;
    }
    public ArrayList<Matcher> getMatcher(){
        return matcher;
    }
    //http://stackoverflow.com/questions/1293058/round-robin-tournament-algorithm-in-c-sharp
    public void listMatches(ArrayList<Klubbar> klubbar){
        if(klubbar.size() % 2 != 0){
            Klubbar klubb = new Klubbar();
            klubbar.add(klubb);
        }
        int mid = klubbar.size()/2;
        int days = (klubbar.size()-1);
        
        ArrayList<Klubbar> teams = new ArrayList<Klubbar>(klubbar);
        System.out.println(klubbar.get(0));
        teams.remove(0);
        
        int teamSize = teams.size();
        System.out.println(klubbar.get(0));
        
        for(int day = 0; day < days; day++){
            Matcher match = new Matcher();
            match.setDag(day+1);
            int teamindex = day % teamSize;
            if(day % 2 == 0){
                if(teams.get(teamindex).getDivID() == 0 || klubbar.get(0).getDivID() == 0){
                    
                }else{
                    match.setHlagID(klubbar.get(0).getKlubbID());
                    match.setBlagID(teams.get(teamindex).getKlubbID());
                    match.setDivID(klubbar.get(0).getDivID());
                    match.setSeasonID(klubbar.get(0).getSeasonID());
                    matcher.add(match);
                }
            }else{
                if(teams.get(teamindex).getDivID() == 0 || klubbar.get(0).getDivID() == 0){
                    
                }else{
                    match.setHlagID(teams.get(teamindex).getKlubbID());
                    match.setBlagID(klubbar.get(0).getKlubbID());
                    match.setDivID(klubbar.get(0).getDivID());
                    match.setSeasonID(klubbar.get(0).getSeasonID());
                    matcher.add(match);
                }
            }
            
            for(int index = 1; index < mid; index++){
                match = new Matcher();
                match.setDag(day+1);
                int firstTeam = (day + index) % teamSize;
                int secondTeam = (day  + teamSize - index) % teamSize;
                if(teams.get(firstTeam).getDivID() == 0 || teams.get(secondTeam).getDivID() == 0){
                    
                }else{
                    match.setHlagID(teams.get(firstTeam).getKlubbID());
                    match.setBlagID(teams.get(secondTeam).getKlubbID());
                    match.setDivID(teams.get(firstTeam).getDivID());
                    match.setSeasonID(teams.get(firstTeam).getSeasonID());
                    matcher.add(match);  
                }
                
            }
             
        }
        
    }

    private void create(int i, ArrayList<String> test) {
        
    }
}
