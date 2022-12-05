package view;

import model.Score;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MatchCard extends JPanel implements KeyListener {
    private Color bdMCColor = new Color(0xFFFFFF);
    private Color jLMCColor = new Color(0xd4c164);

    private JLabel homeTeamFlag, awayTeamFlag, homeTeamName, awayTeamName, statusLabel;
    private JTextField homeTf, awayTf;
    private ImageIcon xIcon, checkIcon;



    private Team home, away;
    private Boolean wasFilled;

    public MatchCard(int boundX0, int boundY0, Score score, KeyListener mainFrame) {
        super();
        this.setBounds(boundX0, boundY0, 288, 126);
        super.setBackground(bdMCColor);
        this.setBorder(BorderFactory.createEtchedBorder(KnockOutFrame.bgColorLight, KnockOutFrame.bgColorDark));
        this.setLayout(null);

        xIcon = new ImageIcon("icons/x.png");
        checkIcon = new ImageIcon("icons/check.png");

        statusLabel = new JLabel(xIcon);
        home = score.getHomeTeam();
        away = score.getAwayTeam();

        this.setHomeTeam(home);
        this.setAwayTeam(away);

        this.setComponentsBounds();
        this.add(statusLabel);
        this.add(homeTeamFlag);
        this.add(awayTeamFlag);
        this.add(homeTeamName);
        this.add(awayTeamName);
        this.add(homeTf);
        this.add(awayTf);

        awayTf.addKeyListener(this);
        awayTf.addKeyListener(mainFrame);
        homeTf.addKeyListener(this);
        homeTf.addKeyListener(mainFrame);
        wasFilled = false;
    }

    public MatchCard(int boundX0, int boundY0, String matchIdHome, String mathcIdAway, KeyListener mainFrame) {
        super();
        this.setBounds(boundX0, boundY0, 288, 126);
        super.setBackground(bdMCColor);
        this.setBorder(BorderFactory.createEtchedBorder(KnockOutFrame.bgColorLight, KnockOutFrame.bgColorDark));
        this.setLayout(null);

        xIcon = new ImageIcon("icons/x.png");
        checkIcon = new ImageIcon("icons/check.png");
        ImageIcon def = new ImageIcon("icons/def.png");
        statusLabel = new JLabel(xIcon);

        this.setHomeTeam(new Team(matchIdHome, def));
        this.setAwayTeam(new Team(mathcIdAway, def));

        this.setComponentsBounds();
        this.add(statusLabel);
        this.add(homeTeamFlag);
        this.add(awayTeamFlag);
        this.add(homeTeamName);
        this.add(awayTeamName);
        this.add(homeTf);
        this.add(awayTf);

        awayTf.addKeyListener(this);
        awayTf.addKeyListener(mainFrame);
        homeTf.addKeyListener(this);
        homeTf.addKeyListener(mainFrame);
        this.setTextFieldDisabled();
        wasFilled = false;
    }

    private void setComponentsBounds() {

        homeTf = new JTextField();
        homeTf.setHorizontalAlignment(JTextField.CENTER);
        homeTf.setFont(new Font(null, Font.BOLD, 30));
        homeTf.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        awayTf = new JTextField();
        awayTf.setHorizontalAlignment(JTextField.CENTER);
        awayTf.setFont(new Font(null, Font.BOLD, 30));
        awayTf.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        statusLabel.setBounds(136, 84, 16, 16);
        homeTeamFlag.setBounds(34, 32, 52, 35);
        awayTeamFlag.setBounds(208, 32, 52, 35);
        homeTeamName.setBounds(34, 77, 52, 12);
        awayTeamName.setBounds(208, 77, 52, 12);
        homeTf.setBounds(106, 34, 40, 40);
        awayTf.setBounds(148, 34, 40, 40);
    }

    public int getHomeGoals() {
        try {
            return Integer.parseInt(homeTf.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return -1;
    }

    public int getAwayGoals() {
        try {
            return Integer.parseInt(awayTf.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return -1;
    }

    public int getAwayScore() {
        try {
            return Integer.parseInt(awayTf.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return -1;
    }

    public Team getWinner() {
        if (this.getHomeGoals() > this.getAwayGoals()) {
            return home;
        } else {
            return away;
        }
    }

    public void setHomeTeam(Team team) {
        home = team;
        homeTeamFlag = new JLabel(home.getFlag());
        homeTeamFlag.setBorder(BorderFactory.createLineBorder(jLMCColor));
        homeTeamName = new JLabel(home.getAbv(), SwingConstants.CENTER);
    }

    public void setAwayTeam(Team team) {
        away = team;
        awayTeamFlag = new JLabel(away.getFlag());
        awayTeamFlag.setBorder(BorderFactory.createLineBorder(jLMCColor));
        awayTeamName = new JLabel(away.getAbv(), SwingConstants.CENTER);
    }

    public void setTextFieldDisabled() {
        homeTf.setEnabled(false);
        awayTf.setEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getSource() == homeTf && !homeTf.getText().isEmpty()) {
            try {
                if (Integer.parseInt(homeTf.getText()) > 40) {
                    JOptionPane.showMessageDialog(null, "The number of goals have to less than 40", "Error", JOptionPane.INFORMATION_MESSAGE);
                    homeTf.setText("");
                    statusLabel.setIcon(xIcon);
                    this.wasFilled = false;
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Please enter with a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                homeTf.setText("");
                statusLabel.setIcon(xIcon);
                this.wasFilled = false;
            }
        } else if (keyEvent.getSource() == awayTf && !awayTf.getText().isEmpty()) {
            try {
                if (Integer.parseInt(awayTf.getText()) > 40) {
                    JOptionPane.showMessageDialog(null, "The number of goals have to less than 40", "Error", JOptionPane.INFORMATION_MESSAGE);
                    awayTf.setText("");
                    statusLabel.setIcon(xIcon);
                    this.wasFilled = false;
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Please enter with a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                awayTf.setText("");
                statusLabel.setIcon(xIcon);
                this.wasFilled = false;
            }
        }

        if (!awayTf.getText().isEmpty() && !homeTf.getText().isEmpty()) {
            if (Integer.parseInt(homeTf.getText()) == Integer.parseInt(awayTf.getText())) {
                JOptionPane.showMessageDialog(null, "The home and away goals has to be different", "Error", JOptionPane.INFORMATION_MESSAGE);
                awayTf.setText("");
                homeTf.setText("");
                statusLabel.setIcon(xIcon);
                this.wasFilled = false;
            } else {
                statusLabel.setIcon(checkIcon);
                this.wasFilled = true;
            }
        }
    }

    public Boolean getWasFilled() {
        return wasFilled;
    }

    public JTextField getHomeTf() {
        return homeTf;
    }

    public JTextField getAwayTf() {
        return awayTf;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }
}
