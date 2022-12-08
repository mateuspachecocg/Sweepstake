package model;

public class Score {

    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;

    public Score(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public Score(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public String toString(){
        return String.format("%s %d x %d %s",
                homeTeam.getAbv(), homeGoals,  awayGoals,awayTeam.getAbv());
    }

    public String toStringFileFormated(){
        return String.format("%s;%d;%s;%d",
                homeTeam.getAbv(), homeGoals, awayTeam.getAbv(), awayGoals);
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setGoals(int homeGoals, int awayGoals){
        this.setHomeGoals(homeGoals);
        this.setAwayGoals(awayGoals);
    }
    public Team winnerTeam() {
        if (homeGoals > awayGoals)
            return homeTeam;
        else if (awayGoals > homeGoals)
            return awayTeam;
        else
            return null;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
}
