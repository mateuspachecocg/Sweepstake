package view;

import control.SweepstakeDAO;

import javax.swing.*;

public class ExportFrame {
    String fileName;
    public ExportFrame() {

        fileName = JOptionPane.showInputDialog("Enter with the file name:", null);
        SweepstakeDAO sweepstakeDAO = new SweepstakeDAO();
        if(sweepstakeDAO.exportToFile(fileName)) {
            JOptionPane.showMessageDialog(null, "Successfully wrote to the file!", "INFORMATION", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "An error occurred!", "INFORMATION", JOptionPane.WARNING_MESSAGE);
        }

        new MainMenu();
    }
}
