package view;

import control.SweepstakeDAO;

import javax.swing.*;
import java.io.File;

public class ImportFrame {

    public ImportFrame() {
        JFileChooser fc = new JFileChooser();

        if (fc.showDialog(null, "Import") == 0) {
            File file = fc.getSelectedFile();

            SweepstakeDAO sweepstakeDAO = new SweepstakeDAO();

            if(sweepstakeDAO.importFromFile(file)) {
                JOptionPane.showMessageDialog(null, "Successfully read the file!", "INFORMATION", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "An error occurred!", "INFORMATION", JOptionPane.WARNING_MESSAGE);
            }
        }
        new MainMenu();
    }
}
