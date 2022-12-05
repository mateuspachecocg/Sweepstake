package model;

public class Stage {

    private int idStage;
    private String name;
    private int numberMatches;

    private Score[] scores;

    public Stage(int idStage, String name, int numberMatches) {
        this.idStage = idStage;
        this.name = name;
        this.numberMatches = numberMatches;
        this.scores = new Score[numberMatches];
    }

    public int getIdStage() {
        return idStage;
    }

    public String getName() {
        return name;
    }

    public int getNumberMatches() {
        return numberMatches;
    }

    public Score getScoreByIndex(int i){
        return scores[i];
    }

    public void setScoreByIndex(int index, Score score) {
        scores[index] = score;
    }

    public Score[] getScores(){
        return scores;
    }
}
