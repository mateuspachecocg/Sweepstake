package view;

import javax.swing.*;
import java.awt.*;

public class MatchCard extends JPanel {
    JLabel homeTeamFlag, awayTeamFlag, homeTeamName, awayTeamName;
    ImageIcon homeIcon, awayIcon;
    JTextField homeTf, awayTf;

    public MatchCard(int boundX0, int boundY0, String home, String away) {
        super();
        this.setBounds(boundX0, boundY0, 288, 126);
        super.setBackground(new Color(0xFFFFFF));
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setLayout(null);

        homeIcon = new ImageIcon("icons/" + home + ".png");
        awayIcon = new ImageIcon("icons/" + away + ".png");

        homeTeamName = new JLabel(home);
        awayTeamName = new JLabel(away);

        homeTeamFlag = new JLabel(homeIcon);
        homeTeamFlag.setBounds(34, 32, 52, 35);

        awayTeamFlag = new JLabel(awayIcon);
        awayTeamFlag.setBounds(208, 32, 52, 35);

        homeTeamName = new JLabel(home, SwingConstants.CENTER);
        homeTeamName.setBounds(34, 77, 52, 12);

        awayTeamName = new JLabel(away, SwingConstants.CENTER);
        awayTeamName.setBounds(208, 77, 52, 12);

        homeTf = new JTextField();
        homeTf.setHorizontalAlignment(JTextField.CENTER);
        homeTf.setFont(new Font(null, Font.BOLD, 30));
        homeTf.setBounds(106, 34, 40, 40);
        homeTf.setBorder(BorderFactory.createLineBorder(Color.black));

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

    public int getHomeScore() {
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
}
