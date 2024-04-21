package com.example.oblig3data1700;

public class Film {
    private String velger;
    private int id;



    public Film(String velger,int id){
        this.velger= velger;
        this.id=id;
    }

    public String getVelger() {
        return velger;
    }

    public void setVelger(String velger) {
        this.velger = velger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film(){
    }


    public String getValg() {
        return velger;
    }

    public void setValg(String velger) {
        this.velger = velger;
    }


}
