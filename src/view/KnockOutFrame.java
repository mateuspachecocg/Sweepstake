package view;

import control.SweepstakeDAO;
import model.Score;
import model.Stage;
import model.Sweepstake;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class KnockOutFrame extends JFrame {

    Color bgColor = new Color(238,238,227);
    JPanel topPanel, bottomPanel;
    JLabel topTitle;
    Font poppins;
    Team teams[];

    MatchCard matchCardA, matchCardB,matchCardC,matchCardD,matchCardE, matchCardF, matchCardG;

    Sweepstake sweepstake;
    public KnockOutFrame(String punterName) throws IOException, FontFormatException {
        this.getFontPoppins();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1347, 585);
        this.setLayout(null);
        this.setMainComponents();

        int id = 1 + new SweepstakeDAO().getGreaterSweepstakeId();
        sweepstake = new Sweepstake(id, punterName);
        this.getAvailableTeams();
        sweepstake.getQuarterFinal().setScoreByIndex(0, new Score(teams[0], teams[1]));
        sweepstake.getQuarterFinal().setScoreByIndex(1, new Score(teams[2], teams[3]));
        sweepstake.getQuarterFinal().setScoreByIndex(2, new Score(teams[4], teams[5]));
        sweepstake.getQuarterFinal().setScoreByIndex(3, new Score(teams[6], teams[7]));

        this.renderingMatchCards();

        this.add(topPanel);
        this.add(bottomPanel);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setMainComponents() {
        topTitle = new JLabel("KnockOut");
        topTitle.setBounds(0, 0, 89,29);
        topTitle.setFont(poppins);
        topTitle.setForeground(new Color(0x03122B));

        topPanel = new JPanel();
        topPanel.setBounds(0,0, 1500,37);
        topPanel.setBackground(bgColor);
        topPanel.setLayout(null);
        topPanel.add(topTitle);
        topPanel.setBorder(BorderFactory.createEtchedBorder());

        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,40, 1500,498);
        bottomPanel.setBackground(bgColor);
        bottomPanel.setLayout(null);
    }

    private void getFontPoppins() {
        try{
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAvailableTeams(){
        teams = new SweepstakeDAO().getTableTeam();
    }

    private void renderingMatchCards() {
        matchCardA = new MatchCard(20, 50, sweepstake.getQuarterFinal().getScoreByIndex(0));
        matchCardB = new MatchCard(20, 322, sweepstake.getQuarterFinal().getScoreByIndex(1));
        matchCardF = new MatchCard(1039, 50, sweepstake.getQuarterFinal().getScoreByIndex(2));
        matchCardG = new MatchCard(1039, 322, sweepstake.getQuarterFinal().getScoreByIndex(3));

        bottomPanel.add(matchCardA);
        bottomPanel.add(matchCardB);
        bottomPanel.add(matchCardF);
        bottomPanel.add(matchCardG);
    }
}
