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
        this.setSize(1263, 713);

        topTitle = new JLabel("KnockOut");
        topTitle.setBounds(0, 0, 89,29);
        topTitle.setFont(poppins);
        topTitle.setForeground(new Color(0x03122B));

        topPanel = new JPanel();
        topPanel.setSize(new Dimension(1263, 37));
        topPanel.setBackground(bgColor);
        topPanel.setLayout(null);
        topPanel.add(topTitle);
        topPanel.setBorder(BorderFactory.createEtchedBorder());

        bottomPanel = new JPanel();
        bottomPanel.setSize(1263, 546);
        bottomPanel.setBackground(bgColor);
        bottomPanel.setLayout(null);

        Team team = new Team(1, "NED", "NETHERLANDS");
        MatchCard matchCard1 = new MatchCard(146, 66, team, team);
//        MatchCard matchCard2 = new MatchCard(146, 151);
//        MatchCard matchCard3 = new MatchCard(146, 276);
//        MatchCard matchCard4 = new MatchCard(146, 361);
//        MatchCard matchCard5= new MatchCard(970, 66);
//        MatchCard matchCard6 = new MatchCard(970, 151);
//        MatchCard matchCard7 = new MatchCard(970, 276);
//        MatchCard matchCard8 = new MatchCard(970, 361);
//
//
       bottomPanel.add(matchCard1);
//        bottomPanel.add(matchCard2);
//        bottomPanel.add(matchCard3);
//        bottomPanel.add(matchCard4);
//        bottomPanel.add(matchCard5);
//        bottomPanel.add(matchCard6);
//        bottomPanel.add(matchCard7);
//        bottomPanel.add(matchCard8);

        this.add(topPanel);
        this.add(bottomPanel);
        this.setResizable(false);
        this.setVisible(true);
    }


}
