package model;

public class Sweepstake {

    private int idSweepstake;
    private String punterName;
    private Stage quarterFinal;
    private Stage semiFinal;
    private Stage finalStage;

    public Sweepstake(int idSweepstake, String punterName) {
        this.idSweepstake = idSweepstake;
        this.punterName = punterName;
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

    public void setQuarterFinal(Stage quarterFinal) {
        this.quarterFinal = quarterFinal;
    }

    public void setSemiFinal(Stage semiFinal) {
        this.semiFinal = semiFinal;
    }

    public void setFinalStage(Stage finalStage) {
        this.finalStage = finalStage;
    }
}
