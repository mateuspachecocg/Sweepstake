package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame implements ActionListener {

    JButton btnNew;
    JButton btnShow;
    JButton btnImport;
    JButton btnExport;

    public MainMenu() {
        super("Welcome to World Cup Sweepstake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 25));


        btnNew = new JButton("Create a new Sweepstake");
        btnShow = new JButton("Show all Sweepstake");
        btnImport = new JButton("Import from a file");
        btnExport = new JButton("Export to a file");

        btnNew.addActionListener(this);
        btnShow.addActionListener(this);
        btnImport.addActionListener(this);
        btnShow.addActionListener(this);

        this.add(btnNew);
        this.add(btnShow);
        this.add(btnImport);
        this.add(btnExport);

        this.setResizable(false);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnNew) {
            this.dispose();
            try {
                String name = JOptionPane.showInputDialog("Punter Name: ");
                new KnockOutFrame(name);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
