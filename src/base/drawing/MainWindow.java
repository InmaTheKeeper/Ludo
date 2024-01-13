package base.drawing;

import base.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements KeyListener {
    private final DrawPanel dp;

    public MainWindow() throws Exception {
        addKeyListener(this);
        dp = new DrawPanel();
        this.add(dp);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("key pressed");
        if (c == 'f' || c == 'F') {
            dp.nextTurn();
            System.out.println("F pressed");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
