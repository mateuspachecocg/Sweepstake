package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    static Color blackBasic = new Color(0x202124);
    static Color qatarRed = new Color(0x8a1538);
    private JButton btnNew;
    private JButton btnShow;
    private JButton btnImport;
    private JButton btnExport;

    public MainMenu() {
        super("Welcome to World Cup Sweepstake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setBackground(blackBasic);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 25));


        btnNew = new JButton("Create a new Sweepstake");
        btnShow = new JButton("Show all Sweepstake");
        btnImport = new JButton("Import from a file");
        btnExport = new JButton("Export to a file");


        btnNew.addActionListener(this);
        btnShow.addActionListener(this);
        btnImport.addActionListener(this);
        btnShow.addActionListener(this);
        this.personalizingButtons();
        this.add(btnNew);
        this.add(btnShow);
        this.add(btnImport);
        this.add(btnExport);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void personalizingButtons() {
        btnShow.setForeground(Color.white);
        btnShow.setBackground(qatarRed);
        btnShow.setFocusable(false);
        btnNew.setForeground(Color.white);
        btnNew.setBackground(qatarRed);
        btnNew.setFocusable(false);
        btnImport.setForeground(Color.white);
        btnImport.setBackground(qatarRed);
        btnImport.setFocusable(false);
        btnExport.setForeground(Color.white);
        btnExport.setBackground(qatarRed);
        btnExport.setForeground(Color.white);
        btnExport.setFocusable(false);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnNew) {
            this.dispose();
            String name = JOptionPane.showInputDialog("Punter Name: ");
            new KnockOutFrame(name);
        } else if(actionEvent.getSource() == btnShow) {
            this.dispose();
            new ShowSweepstakesFrame();
        }
    }
}
