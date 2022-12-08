package model;

import control.SweepstakeDAO;

public class Sweepstake {

    private int idSweepstake;
    private String punterName;
    private Stage quarterFinal;
    private Stage semiFinal;
    private Stage finalStage;

    public Sweepstake(){
        this.idSweepstake = -1;
        this.punterName = "";

        this.quarterFinal = new Stage(1, "Quarter-Final", 4);
        this.semiFinal = new Stage(2, "Semi-Final", 2);
        this.finalStage = new Stage(3, "Final", 1);
    }

    public Sweepstake(int idSweepstake, String punterName) {
        this.idSweepstake = idSweepstake;
        this.punterName = punterName;

        this.quarterFinal = new Stage(1, "Quarter-Final", 4);
        this.semiFinal = new Stage(2, "Semi-Final", 2);
        this.finalStage = new Stage(3, "Final", 1);
    }

    public Sweepstake(String punterName) {
        this.punterName = punterName;
        this.idSweepstake = 1 + new SweepstakeDAO().getGreaterSweepstakeId();
        this.quarterFinal = new Stage(1, "Quarter-Final", 4);
        this.semiFinal = new Stage(2, "Semi-Final", 2);
        this.finalStage = new Stage(3, "Final", 1);
    }

    public int getIdSweepstake() {
        return idSweepstake;
    }

    public String getPunterName() {
        return punterName;
    }

    public Stage getQuarterFinal() {
        return quarterFinal;
    }

    public Stage getSemiFinal() {
        return semiFinal;
    }

    public Stage getFinalStage() {
        return finalStage;
    }


}
