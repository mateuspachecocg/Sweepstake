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
    private JPanel panelButtons;
    public MainMenu() {
        super("Welcome to World Cup Sweepstake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,360);
        this.setBackground(blackBasic);
        this.setLayout(null);
        
        panelButtons = new JPanel(new GridLayout(4,1, 10, 10));
        panelButtons.setBounds(10, 10, 400, 300);

        btnNew = new JButton("CREATE A NEW SWEEPSTAKE");
        btnShow = new JButton("SHOW ALL SWEEPSTAKE");
        btnImport = new JButton("IMPORTA FROM A FILE");
        btnExport = new JButton("EXPORT TO A FILE");


        btnNew.addActionListener(this);
        btnShow.addActionListener(this);
        btnImport.addActionListener(this);
        btnShow.addActionListener(this);
        
        this.personalizingButtons();
        
        panelButtons.add(btnNew);
        panelButtons.add(btnShow);
        panelButtons.add(btnImport);
        panelButtons.add(btnExport);

        this.add(panelButtons);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void personalizingButtons() {
        btnShow.setForeground(Color.white);
        btnShow.setBackground(qatarRed);
        btnShow.setFocusable(false);
        btnShow.setBorder(BorderFactory.createEtchedBorder());
        
        btnNew.setForeground(Color.white);
        btnNew.setBackground(qatarRed);
        btnNew.setFocusable(false);
        btnNew.setBorder(BorderFactory.createEtchedBorder());
        
        btnImport.setForeground(Color.white);
        btnImport.setBackground(qatarRed);
        btnImport.setFocusable(false);
        btnImport.setBorder(BorderFactory.createEtchedBorder());
        
        btnExport.setForeground(Color.white);
        btnExport.setBackground(qatarRed);
        btnExport.setFocusable(false);
        btnExport.setBorder(BorderFactory.createEtchedBorder());

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
