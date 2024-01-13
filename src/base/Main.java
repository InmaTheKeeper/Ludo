package base;

import base.drawing.DrawPanel;
import base.drawing.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int screenWidth = (int) screenSize.getWidth();
    public static final int screenHeight = (int) screenSize.getHeight();

    public static void main(String[] args) throws Exception {
        MainWindow mw = new MainWindow();
        mw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mw.setSize(screenWidth, screenHeight);
        mw.setVisible(true);
    }
}