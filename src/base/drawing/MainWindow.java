package base.drawing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements KeyListener, ActionListener {
    private Timer timer;
    private final DrawPanel dp;
    private boolean isAuto = false;

    public MainWindow() throws Exception {
        addKeyListener(this);
        dp = new DrawPanel();
        this.add(dp);
        timer = new Timer(750, this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'f' || c == 'F') {
            dp.nextTurn();
            System.out.println("F pressed\nMade physical turn");
        }
        if (c == 'a' || c == 'A') {
            isAuto = !isAuto;
            System.out.println("A pressed, isAuto = " + isAuto);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAuto) {
            dp.nextTurn();
            System.out.println("Made Auto turn");
        }
    }
}
