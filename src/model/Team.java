package model;

import javax.swing.*;

public class Team {

    private int idTeam;
    private String abv;
    private String name;

    private ImageIcon flag;

    public Team() {

        this.flag = new ImageIcon("icons/"+this.abv+".png");
    }

    public Team(int idTeam, String abv, String name) {
        this.idTeam = idTeam;
        this.abv = abv;
        this.name = name;
        this.flag = new ImageIcon("/home/mateuspx/eclipse-workspace/sweepstake/icons/"+this.abv+".png");
    }

    public Team(String abv, ImageIcon img) {
        this.abv = abv;
        this.name = "";
        this.flag = img;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getAbv() {
        return abv;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getFlag() {
        return flag;
    }
}
