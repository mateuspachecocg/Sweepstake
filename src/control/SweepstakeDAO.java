package control;

import model.Score;
import model.Sweepstake;
import model.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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
            ResultSet result = connection.prepareStatement("" +
                    "").executeQuery();
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
                    "       teams1.Abv \"abvHome\", scores.goalsHome, scores.goalsAway,teams2.Abv \"abvAway\", teams2.Name \"nameAway\", teams1.idTeam \"idAwayTeam\"\n" +
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


    public boolean exportToFile(File file) {

        try {

            FileWriter fileWriter = new FileWriter(file);
            String strFormatted;
            for(Sweepstake swpt : this.findAll()) {
                strFormatted =  String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        swpt.getPunterName().toUpperCase(),
                        swpt.getQuarterFinal().getScoreByIndex(0).toStringFileFormated(),
                        swpt.getQuarterFinal().getScoreByIndex(1).toStringFileFormated(),
                        swpt.getQuarterFinal().getScoreByIndex(2).toStringFileFormated(),
                        swpt.getQuarterFinal().getScoreByIndex(3).toStringFileFormated(),
                        swpt.getSemiFinal().getScoreByIndex(0).toStringFileFormated(),
                        swpt.getSemiFinal().getScoreByIndex(1).toStringFileFormated(),
                        swpt.getFinalStage().getScoreByIndex(0).toStringFileFormated(),
                        swpt.getFinalStage().getScoreByIndex(0).winnerTeam().getAbv()
                );
                fileWriter.write(strFormatted);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
            return false;
        }
    }

    public boolean importFromFile(File data) {
        ArrayList<Sweepstake> swptList = new ArrayList<Sweepstake>();
        try {
            Scanner myScanner = new Scanner(data);
            Sweepstake swpt;
            while(myScanner.hasNextLine()) {
                String[] strSplit =  myScanner.nextLine().split(";");

                swpt = new Sweepstake(strSplit[0]);
                swpt.getQuarterFinal().setScoreByIndex(0, new Score(this.getTeamByAbv(strSplit[1]), this.getTeamByAbv(strSplit[3]), Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[4])));
                swpt.getQuarterFinal().setScoreByIndex(1, new Score(this.getTeamByAbv(strSplit[5]), this.getTeamByAbv(strSplit[7]), Integer.parseInt(strSplit[6]), Integer.parseInt(strSplit[8])));
                swpt.getQuarterFinal().setScoreByIndex(2, new Score(this.getTeamByAbv(strSplit[9]), this.getTeamByAbv(strSplit[11]), Integer.parseInt(strSplit[10]), Integer.parseInt(strSplit[12])));
                swpt.getQuarterFinal().setScoreByIndex(3, new Score(this.getTeamByAbv(strSplit[13]), this.getTeamByAbv(strSplit[15]), Integer.parseInt(strSplit[14]), Integer.parseInt(strSplit[16])));

                swpt.getSemiFinal().setScoreByIndex(0, new Score(this.getTeamByAbv(strSplit[17]), this.getTeamByAbv(strSplit[19]), Integer.parseInt(strSplit[18]), Integer.parseInt(strSplit[20])));
                swpt.getSemiFinal().setScoreByIndex(1, new Score(this.getTeamByAbv(strSplit[21]), this.getTeamByAbv(strSplit[23]), Integer.parseInt(strSplit[22]), Integer.parseInt(strSplit[24])));

                swpt.getFinalStage().setScoreByIndex(0, new Score(this.getTeamByAbv(strSplit[25]), this.getTeamByAbv(strSplit[27]), Integer.parseInt(strSplit[26]), Integer.parseInt(strSplit[28])));

                this.insert(swpt);

            }
            return true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //Method to return the id of a team based on abreviation
    public Team getTeamByAbv(String teamAbv) {
        for(Team team : tableTeam) {
            if(team.getAbv().equals(teamAbv)) {
                return team;
            }
        }
        return null;
    }



    public Team[] getTableTeam() {
        return tableTeam;
    }
}
