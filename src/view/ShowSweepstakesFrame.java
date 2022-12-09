package view;

import com.sun.tools.javac.Main;
import control.SweepstakeDAO;
import model.Stage;
import model.Sweepstake;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static view.MainMenu.blackBasic;
import static view.MainMenu.qatarRed;

public class ShowSweepstakesFrame extends JFrame implements ActionListener {
    private JPanel tablePanel;
    private JButton btnBack;
    private JTable table;
    private JScrollPane scrollPane;
    public ShowSweepstakesFrame() {
        super("SWEEPSTAKE ON THE DATABASE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setBackground(blackBasic);
        this.setLayout(null);

        table = new JTable(new MyTableModel());
        table.setRowHeight(100);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        table.setDefaultRenderer(Stage.class,new StageRenderer());

        // Add the scroll pane to this panel.
        tablePanel = new JPanel(new GridLayout(1, 0));
        tablePanel.setBounds(100, 25, 700, 500);
        tablePanel.add(scrollPane);
        
        // Creating the Button Back to Main Menu
        btnBack = new JButton("BACK TO MAIN MENU");
        btnBack.setBounds(350, 530, 200, 30);
        btnBack.setBackground(qatarRed);
        btnBack.setForeground(Color.white);
        btnBack.setFocusable(false);
        btnBack.addActionListener(this);
        
        this.add(btnBack);
        this.add(tablePanel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.dispose();
        new MainMenu();
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Punter Name", "Quarter-Final", "Semi-Final", "Final", "Champion" };
        private Object[][] data;
        public MyTableModel() {
            ArrayList<Sweepstake> swptList = new SweepstakeDAO().findAll();
            data = new Object[swptList.size()][5];
            int index = 0;
            for(Sweepstake swpt : swptList)  {
                data[index][0] = swpt.getPunterName().toUpperCase();
                data[index][1] = swpt.getQuarterFinal();
                data[index][2] =  swpt.getSemiFinal();
                data[index][3] = swpt.getFinalStage();
                data[index][4] = swpt.getFinalStage().getScoreByIndex(0).winnerTeam().getName();
                index++;
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 5) {
                return false;
            } else {
                return true;
            }
        }
    }



}
