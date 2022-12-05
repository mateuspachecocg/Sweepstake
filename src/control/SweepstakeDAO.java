package control;

import model.Score;
import model.Sweepstake;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SweepstakeDAO {

    private Team[] tableTeam;

    public SweepstakeDAO () {
        tableTeam = new Team[this.getNumberOfTeams()];
        this.chargeTeams();
    }

    private void chargeTeams() {
        int index = 0;
        Connection connection = new ConnectionFactory().getConnection();
        try {
            ResultSet result = connection.prepareStatement("SELECT * FROM teams;").executeQuery();
            while(result.next()) {
                tableTeam[index] = new Team(
                        result.getInt("idTeam"),
                        result.getString("Abv"),
                        result.getString("Name")
                );
                index++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private int getNumberOfTeams() {
        Connection connection = new ConnectionFactory().getConnection();

        try {
            ResultSet result = connection.prepareStatement("SELECT * FROM numberOfTeams;").executeQuery();
            if(result.next()) {
                return result.getInt("numberTeams");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }


    public int getGreaterSweepstakeIndex() {
        Connection connection = new ConnectionFactory().getConnection();

        try {
            ResultSet result = connection.prepareStatement("SELECT * FROM `greaterIdSweepstake`;").executeQuery();
            if(result.next()) {
                return result.getInt("maxId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public void insert(Sweepstake swpt) {
        try {
            Connection connection = new ConnectionFactory().getConnection();

            PreparedStatement insertSweepstake = connection.prepareStatement(
                    "INSERT INTO `sweepstakes` (`punterName`, `idChampion`) VALUES (?, ?);");
            insertSweepstake.setString(1, swpt.getPunterName());
            insertSweepstake.setInt(2, swpt.getFinalStage().getScoreByIndex(0).winnerTeam().getIdTeam());

            insertSweepstake.execute();

            PreparedStatement insertScore = connection.prepareStatement(
                    "INSERT INTO `scores` (`idScore`, `idHomeTeam`, `idAwayTeam`, `goalsHome`, `goalsAway`, `idSweepstake`) " +
                            "VALUES (NULL, ?, ?, ?, ?, ?);");
            insertScore.setInt(5, swpt.getIdSweepstake());
            for (Score score: swpt.getQuarterFinal().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.execute();
            }

            for (Score score: swpt.getSemiFinal().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.execute();
            }

            for (Score score: swpt.getFinalStage().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team[] getTableTeam() {
        return tableTeam;
    }
}
