package control;

import model.Score;
import model.Sweepstake;
import model.Team;

import java.sql.*;
import java.util.ArrayList;

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
            ResultSet result = connection.prepareStatement("SELECT * FROM `numberOfTeams`;").executeQuery();
            if(result.next()) {
                return result.getInt("numberTeams");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }


    public int getGreaterSweepstakeId() {
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
                    "INSERT INTO `scores` (`idScore`, `idHomeTeam`, `idAwayTeam`, `goalsHome`, `goalsAway`,  `idStage`, `idSweepstake`) " +
                            "VALUES (NULL, ?, ?, ?, ?, ?, ?);");
            insertScore.setInt(6, swpt.getIdSweepstake());
            for (Score score: swpt.getQuarterFinal().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.setInt(5, 1);
                insertScore.execute();
            }

            for (Score score: swpt.getSemiFinal().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.setInt(5, 2);
                insertScore.execute();
            }

            for (Score score: swpt.getFinalStage().getScores()) {
                insertScore.setInt(1, score.getHomeTeam().getIdTeam());
                insertScore.setInt(2, score.getAwayTeam().getIdTeam());
                insertScore.setInt(3, score.getHomeGoals());
                insertScore.setInt(4, score.getAwayGoals());
                insertScore.setInt(5, 3);
                insertScore.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Sweepstake> findAll(){

        Connection connection = new ConnectionFactory().getConnection();
        ArrayList<Sweepstake> swptList = new ArrayList<Sweepstake>();

        try {
            ResultSet result = connection.prepareStatement("SELECT sweepstakes.idSweepstake, sweepstakes.punterName, scores.idStage,teams1.idTeam \"idHomeTeam\", teams1.Name \"nameHome\",\n" +
                    "       teams1.Abv \"abvHome\", scores.goalsHome, scores.goalsAway,teams1.Abv \"abvAway\", teams2.Name \"nameAway\", teams1.idTeam \"idAwayTeam\"\n" +
                    "FROM sweepstakes,scores,teams teams1, teams teams2\n" +
                    "WHERE sweepstakes.idSweepstake = scores.idSweepstake AND\n" +
                    "      scores.idHomeTeam = teams1.idTeam AND scores.idAwayTeam = teams2.idTeam;").executeQuery();
            Sweepstake swpt = new Sweepstake();
            Score score;
            int countQF = 0;
            int countSF = 0;
            while(result.next()) {
                if(swpt.getIdSweepstake() != result.getInt("idSweepstake")) {
                    swpt = new Sweepstake(result.getInt("idSweepstake"), result.getString("punterName"));
                    countQF = 0;
                    countSF = 0;
                    swptList.add(swpt);
                }
                score = new Score(
                        new Team(result.getInt("idHomeTeam"), result.getString("abvHome"), result.getString("nameHome")),
                        new Team(result.getInt("idAwayTeam"), result.getString("abvAway"), result.getString("nameAway")),
                        result.getInt("goalsHome"),
                        result.getInt("goalsAway")
                );

                switch(result.getInt("idStage")){
                    case 1:
                        swpt.getQuarterFinal().setScoreByIndex(countQF, score);
                        countQF++;
                        break;
                    case 2:
                        swpt.getSemiFinal().setScoreByIndex(countSF, score);
                        countSF++;
                        break;
                    case 3:
                        swpt.getFinalStage().setScoreByIndex(0, score);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return swptList;
    }


    public Team[] getTableTeam() {
        return tableTeam;
    }
}
