package view;

import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MatchCard extends JPanel implements KeyListener {
    JLabel homeTeamFlag, awayTeamFlag, homeTeamName, awayTeamName;
    JTextField homeTf, awayTf;

    public MatchCard(int boundX0, int boundY0, Team home, Team away) {
        super();
        this.setBounds(boundX0, boundY0, 288, 126);
        super.setBackground(new Color(0xFFFFFF));
        this.setBorder(BorderFactory.createEtchedBorder(Color.CYAN, Color.MAGENTA));
        this.setLayout(null);



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
                }
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null, "Please enter with a number", "Error", JOptionPane.INFORMATION_MESSAGE);
                homeTf.setText("");
            }
        }
    }
}
