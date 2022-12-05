package view;

import model.Team;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KnockOutFrame extends JFrame {

    Color bgColor = new Color(238,238,227);
    JPanel topPanel, bottomPanel;
    JLabel topTitle;
    Font poppins;
    public KnockOutFrame() throws IOException, FontFormatException {

        try{
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1347, 585);
        this.setLayout(null);

        topTitle = new JLabel("KnockOut");
        topTitle.setBounds(0, 0, 89,29);
        topTitle.setFont(poppins);
        topTitle.setForeground(new Color(0x03122B));

        topPanel = new JPanel();
        topPanel.setBounds(0,0, 1500,37);
        //topPanel.setSize(new Dimension(1360, 37));
        topPanel.setBackground(bgColor);
        topPanel.setLayout(null);
        topPanel.add(topTitle);
        topPanel.setBorder(BorderFactory.createEtchedBorder());

        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,40, 1500,498);
        bottomPanel.setBackground(bgColor);
        bottomPanel.setLayout(null);

        Team team1 = new Team(1, "NED", "NETHERLANDS");
        Team team2 = new Team(1, "BRA", "BRAZIL");

        MatchCard matchCardA = new MatchCard(20, 50, team1, team2);
        MatchCard matchCardB = new MatchCard(20, 322, team1, team2);
        MatchCard matchCardC = new MatchCard(328, 186, team1, team2);
        MatchCard matchCardD = new MatchCard(530, 30, team1, team2);
        MatchCard matchCardE = new MatchCard(731, 186, team1, team2);
        MatchCard matchCardF = new MatchCard(1039, 50, team1, team2);
        MatchCard matchCardG = new MatchCard(1039, 322, team1, team2);

       bottomPanel.add(matchCardA);
       bottomPanel.add(matchCardB);
       bottomPanel.add(matchCardC);
       bottomPanel.add(matchCardD);
       bottomPanel.add(matchCardE);
       bottomPanel.add(matchCardF);
       bottomPanel.add(matchCardG);

        this.add(topPanel);
        this.add(bottomPanel);
        this.setResizable(false);
        this.setVisible(true);
    }


}
