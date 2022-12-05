import control.SweepstakeDAO;
import view.KnockOutFrame;
import view.MainMenu;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        // new MainMenu();

        SweepstakeDAO swpt = new SweepstakeDAO();
        System.out.println(swpt.getGreaterSweepstakeIndex());
    }
}