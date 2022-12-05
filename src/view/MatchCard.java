package view;

import model.Score;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MatchCard extends JPanel implements KeyListener {
    JLabel homeTeamFlag, awayTeamFlag, homeTeamName, awayTeamName, statusLabel;
    JTextField homeTf, awayTf;
    ImageIcon xIcon, checkIcon;

    public MatchCard(int boundX0, int boundY0, Score score) {
        super();
        this.setBounds(boundX0, boundY0, 288, 126);
        super.setBackground(new Color(0xFFFFFF));
        this.setBorder(BorderFactory.createEtchedBorder(Color.CYAN, Color.MAGENTA));
        this.setLayout(null);

        xIcon = new ImageIcon("icons/x.png");
        checkIcon = new ImageIcon("icons/check.png");

        statusLabel = new JLabel(xIcon);
        statusLabel.setBounds(136, 84, 16, 16);

        Team home = score.getHomeTeam();
        Team away = score.getAwayTeam();

        homeTeamFlag = new JLabel(home.getFlag());
        homeTeamFlag.setBounds(34, 32, 52, 35);

        awayTeamFlag = new JLabel(away.getFlag());
        awayTeamFlag.setBounds(208, 32, 52, 35);

        homeTeamName = new JLabel(home.getAbv(), SwingConstants.CENTER);
        homeTeamName.setBounds(34, 77, 52, 12);

        awayTeamName = new JLabel(away.getAbv(), SwingConstants.CENTER);
        awayTeamName.setBounds(208, 77, 52, 12);

        homeTf = new JTextField();
        homeTf.setHorizontalAlignment(JTextField.CENTER);
        homeTf.setFont(new Font(null, Font.BOLD, 30));
        homeTf.setBounds(106, 34, 40, 40);
        homeTf.setBorder(BorderFactory.createLineBorder(Color.black));
        homeTf.addKeyListener(this);

        awayTf = new JTextField();
        awayTf.setHorizontalAlignment(JTextField.CENTER);
        awayTf.setFont(new Font(null, Font.BOLD, 30));
        awayTf.setBounds(148, 34, 40, 40);
        awayTf.setBorder(BorderFactory.createLineBorder(Color.black));
        awayTf.addKeyListener(this);
        this.add(statusLabel);
        this.add(homeTeamFlag);
        this.add(awayTeamFlag);
        this.add(homeTeamName);
        this.add(awayTeamName);
        this.add(homeTf);
        this.add(awayTf);

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
            return Integer.parseInt(homeTf.getText());
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getSource() == homeTf && !homeTf.getText().isEmpty()) {
            try {
                if (Integer.parseInt(homeTf.getText()) > 40) {
                    JOptionPane.showMessageDialog(null, "The number of goals have to less than 40", "Error", JOptionPane.INFORMATION_MESSAGE);
                    homeTf.setText("");
                    statusLabel.setIcon(xIcon);

                }
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null, "Please enter with a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                homeTf.setText("");
                statusLabel.setIcon(xIcon);

            }
        } else if(keyEvent.getSource() == awayTf && !awayTf.getText().isEmpty()) {
            try {
                if (Integer.parseInt(awayTf.getText()) > 40) {
                    JOptionPane.showMessageDialog(null, "The number of goals have to less than 40", "Error", JOptionPane.INFORMATION_MESSAGE);
                    awayTf.setText("");
                    statusLabel.setIcon(xIcon);

                }
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null, "Please enter with a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                awayTf.setText("");
                statusLabel.setIcon(xIcon);
            }
        }

        if (!awayTf.getText().isEmpty() && !homeTf.getText().isEmpty()) {
            if (Integer.parseInt(homeTf.getText()) ==Integer.parseInt(awayTf.getText()) ){
                JOptionPane.showMessageDialog(null, "The home and away goals has to be different", "Error", JOptionPane.INFORMATION_MESSAGE);
                awayTf.setText("");
                homeTf.setText("");
                statusLabel.setIcon(xIcon);
            } else {
                statusLabel.setIcon(checkIcon);
            }
        }
    }
}
