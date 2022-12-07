package view;

import javax.swing.*;
import java.awt.*;

import static view.MainMenu.blackBasic;

public class ShowSweepstakesFrame extends JFrame {
    public ShowSweepstakesFrame() {

        super("Welcome to World Cup Sweepstake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setBackground(blackBasic);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 25));


        this.setResizable(false);
        this.setVisible(true);

    }
}
