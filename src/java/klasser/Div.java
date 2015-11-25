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
public class Div {
    private int id;
    private String namn;
    public Div(){
        
    }
    public Div(int id, String namn){
        this.id = id;
        this.namn = namn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public int getId() {
        return id;
    }

    public String getNamn() {
        return namn;
    }
}
