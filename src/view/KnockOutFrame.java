package view;

import control.SweepstakeDAO;
import model.Score;
import model.Stage;
import model.Sweepstake;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static view.MainMenu.blackBasic;
import static view.MainMenu.qatarRed;

public class KnockOutFrame extends JFrame implements KeyListener, ActionListener {

    static Color bgColorDark = new Color(87, 50, 101);
    static Color bgColorLight = new Color(238, 238, 228);
    private JPanel topPanel, bottomPanel;
    private JButton buttonFinish;
    private JLabel topTitle;
    static Font poppins;
    private Team teams[];
    private int clickCount;

    MatchCard matchCardA, matchCardB, matchCardC, matchCardD, matchCardE, matchCardF, matchCardG;

    Sweepstake sweepstake;

    public KnockOutFrame(String punterName) {
        this.getFontPoppins();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1347, 585);
        this.setLayout(null);
        this.setMainComponents();


        sweepstake = new Sweepstake(punterName);

        this.getAvailableTeams();
        sweepstake.getQuarterFinal().setScoreByIndex(0, new Score(teams[0], teams[1]));
        sweepstake.getQuarterFinal().setScoreByIndex(1, new Score(teams[2], teams[3]));
        sweepstake.getQuarterFinal().setScoreByIndex(2, new Score(teams[4], teams[5]));
        sweepstake.getQuarterFinal().setScoreByIndex(3, new Score(teams[6], teams[7]));

        buttonFinish = new JButton("FINISH");

        buttonFinish.setBounds(578, 430, 200, 30);
        buttonFinish.setBackground(qatarRed);
        buttonFinish.setForeground(Color.white);
        buttonFinish.setFocusable(false);
        buttonFinish.setEnabled(false);
        buttonFinish.addActionListener(this);
        clickCount = 0;

        this.renderingMatchCards();
        bottomPanel.add(buttonFinish);
        this.add(topPanel);
        this.add(bottomPanel);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setMainComponents() {
        topTitle = new JLabel("KnockOut Stage", SwingConstants.CENTER);
        topTitle.setFont(poppins);
        topTitle.setForeground(new Color(0x03122B));

        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1347, 37);
        topPanel.setBackground(bgColorLight);
        topPanel.add(topTitle, SwingConstants.CENTER);
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setBackground(bgColorLight);

        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 40, 1347, 498);
        bottomPanel.setBackground(bgColorLight);
        bottomPanel.setLayout(null);
    }

    private void getFontPoppins() {
        try {
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("/home/mateuspx/eclipse-workspace/sweepstake/Poppins-Medium.ttf")).deriveFont(Font.BOLD,20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/home/mateuspx/eclipse-workspace/sweepstake/Poppins-Medium.ttf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAvailableTeams() {
        teams = new SweepstakeDAO().getTableTeam();
    }

    private void renderingMatchCards() {
        matchCardA = new MatchCard(20, 50, sweepstake.getQuarterFinal().getScoreByIndex(0), this);
        matchCardB = new MatchCard(20, 322, sweepstake.getQuarterFinal().getScoreByIndex(1), this);
        matchCardF = new MatchCard(1039, 50, sweepstake.getQuarterFinal().getScoreByIndex(2), this);
        matchCardG = new MatchCard(1039, 322, sweepstake.getQuarterFinal().getScoreByIndex(3), this);

        bottomPanel.add(matchCardA);
        bottomPanel.add(matchCardB);
        bottomPanel.add(matchCardF);
        bottomPanel.add(matchCardG);

        matchCardC = new MatchCard(328, 186, "QF1", "QF2", this);
        matchCardE = new MatchCard(731, 186, "QF3", "QF4", this);

        bottomPanel.add(matchCardC);
        bottomPanel.add(matchCardE);

        matchCardD = new MatchCard(530, 30, "SF1", "SF2", this);

        bottomPanel.add(matchCardD);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        if (keyEvent.getSource() == matchCardA.getHomeTf() || keyEvent.getSource() == matchCardA.getAwayTf() ||
                keyEvent.getSource() == matchCardB.getHomeTf() || keyEvent.getSource() == matchCardB.getAwayTf()) {
            if (matchCardA.getWasFilled() && matchCardB.getWasFilled()) {
                bottomPanel.remove(matchCardC);
                matchCardC = new MatchCard(328, 186, new Score(matchCardA.getWinner(), matchCardB.getWinner()), this);
                bottomPanel.add(matchCardC);
                bottomPanel.repaint();
                matchCardA.setTextFieldDisabled();
                matchCardB.setTextFieldDisabled();
            }
        } else if (keyEvent.getSource() == matchCardF.getHomeTf() || keyEvent.getSource() == matchCardF.getAwayTf() ||
                keyEvent.getSource() == matchCardG.getHomeTf() || keyEvent.getSource() == matchCardG.getAwayTf()) {
            if (matchCardF.getWasFilled() && matchCardG.getWasFilled()) {
                bottomPanel.remove(matchCardE);
                matchCardE = new MatchCard(731, 186, new Score(matchCardF.getWinner(), matchCardG.getWinner()), this);
                bottomPanel.add(matchCardE);
                bottomPanel.repaint();
                matchCardF.setTextFieldDisabled();
                matchCardG.setTextFieldDisabled();
            }
        } else if (keyEvent.getSource() == matchCardC.getHomeTf() || keyEvent.getSource() == matchCardC.getAwayTf() ||
                keyEvent.getSource() == matchCardE.getHomeTf() || keyEvent.getSource() == matchCardE.getAwayTf()) {
            if (matchCardC.getWasFilled() && matchCardE.getWasFilled()) {
                bottomPanel.remove(matchCardD);
                matchCardD = new MatchCard(530, 30, new Score(matchCardC.getWinner(), matchCardE.getWinner()), this);
                bottomPanel.add(matchCardD);
                bottomPanel.repaint();
                matchCardC.setTextFieldDisabled();
                matchCardE.setTextFieldDisabled();
            }
        } else if(keyEvent.getSource() == matchCardD.getHomeTf() ||keyEvent.getSource() == matchCardD.getAwayTf()){
            if(matchCardD.getWasFilled()){
                buttonFinish.setEnabled(true);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == buttonFinish) {
            if(clickCount < 1) {
                matchCardD.setTextFieldDisabled();
                buttonFinish.setText("SAVE");
                clickCount++;
                JOptionPane.showMessageDialog(null,
                        matchCardD.getWinner().getName()+" was the Champion",
                        "THE CHAMPION",
                        JOptionPane.QUESTION_MESSAGE,
                        matchCardD.getWinner().getFlag());
            } else {
                sweepstake.getQuarterFinal().getScoreByIndex(0).setGoals(matchCardA.getHomeGoals(), matchCardA.getAwayGoals());
                sweepstake.getQuarterFinal().getScoreByIndex(1).setGoals(matchCardB.getHomeGoals(), matchCardB.getAwayGoals());
                sweepstake.getQuarterFinal().getScoreByIndex(2).setGoals(matchCardF.getHomeGoals(), matchCardF.getAwayGoals());
                sweepstake.getQuarterFinal().getScoreByIndex(3).setGoals(matchCardG.getHomeGoals(), matchCardG.getAwayGoals());

                sweepstake.getSemiFinal().setScoreByIndex(0, new Score(
                        matchCardC.getHome(),
                        matchCardC.getAway(),
                        matchCardC.getHomeGoals(),
                        matchCardC.getAwayGoals()
                ));
                sweepstake.getSemiFinal().setScoreByIndex(1, new Score(
                        matchCardE.getHome(),
                        matchCardE.getAway(),
                        matchCardE.getHomeGoals(),
                        matchCardE.getAwayGoals()
                ));

                sweepstake.getFinalStage().setScoreByIndex(0, new Score(
                        matchCardD.getHome(),
                        matchCardD.getAway(),
                        matchCardD.getHomeGoals(),
                        matchCardD.getAwayGoals()
                ));

                SweepstakeDAO sweepstakeDAO = new SweepstakeDAO();

                sweepstakeDAO.insert(sweepstake);
                this.dispose();
                JOptionPane.showMessageDialog(null, "Success!", "Warning", JOptionPane.PLAIN_MESSAGE);
                new MainMenu();

            }
        }
    }
}
