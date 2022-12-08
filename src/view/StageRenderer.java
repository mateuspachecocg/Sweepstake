package view;

import model.Score;
import model.Stage;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StageRenderer extends JPanel implements TableCellRenderer {

    public StageRenderer(){
        super(new FlowLayout(SwingConstants.CENTER));
        this.setBackground(Color.white);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object stage, boolean b, boolean b1, int i, int i1) {
        this.removeAll();
        Stage newStage = (Stage) stage;
        for (Score score : newStage.getScores()) {
            JLabel label = new JLabel(score.toString());
            label.setFont(new Font("Helvetica", Font.PLAIN, 12));
            this.add(label);
        }
        return this;
    }
}
