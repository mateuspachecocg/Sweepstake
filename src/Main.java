import control.SweepstakeDAO;
import model.Sweepstake;
import model.Team;
import view.KnockOutFrame;
import view.MainMenu;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        // new MainMenu();
        //new KnockOutFrame();

        SweepstakeDAO sweepstakeDAO = new SweepstakeDAO();
        System.out.printf("  %s   \\  %s  \t  %s  \t  %s  \t  %s  \n", "ID", "NOME", "QUARTAS", "SEMI", "FINAL");
        for(Sweepstake swpt : sweepstakeDAO.findAll()) {

            System.out.printf("ID: %d\t NAME: %s\t%s\t%s\t%s\n" +
                    "      \t         \t\t%s\t%s\t\n" +
                    "      \t         \t\t%s\t\n" +
                    "      \t         \t\t%s\t\n\n",
                    swpt.getIdSweepstake(), swpt.getPunterName(),
                    swpt.getQuarterFinal().getScoreByIndex(0).toString(),
                    swpt.getSemiFinal().getScoreByIndex(0).toString(),
                    swpt.getFinalStage().getScoreByIndex(0).toString(),
                    swpt.getQuarterFinal().getScoreByIndex(1).toString(),
                    swpt.getSemiFinal().getScoreByIndex(1).toString(),
                    swpt.getQuarterFinal().getScoreByIndex(2).toString(),
                    swpt.getQuarterFinal().getScoreByIndex(3).toString()
                    );

        }
    }
}