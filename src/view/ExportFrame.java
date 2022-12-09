package view;

import control.SweepstakeDAO;

import javax.swing.*;
import java.io.File;

public class ExportFrame {
    String fileName;
    public ExportFrame() {

        JFileChooser fc = new JFileChooser();

        if (fc.showDialog(null, "Export") == 0) {
            File file = new File(fc.getSelectedFile().getPath()+".swpt");
            SweepstakeDAO sweepstakeDAO = new SweepstakeDAO();
            if(sweepstakeDAO.exportToFile(file)) {
                JOptionPane.showMessageDialog(null, "Successfully wrote to the file!", "INFORMATION", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "An error occurred!", "INFORMATION", JOptionPane.WARNING_MESSAGE);
            }
        }
        new MainMenu();
    }
}
