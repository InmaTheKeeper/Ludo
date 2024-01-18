package base.drawing;

import base.game.Game;
import base.game.PlayerColor;

import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements KeyListener, ActionListener {
    private Timer timer;
    private final DrawPanel dp;
    private boolean isAuto = false;

    private PlayerColor winner = null;

    private boolean gameFinished = false;

    public MainWindow() throws Exception {
        addKeyListener(this);
        dp = new DrawPanel();
        this.add(dp);
        timer = new Timer(500, this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) {
            timer.stop();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        if (code == KeyEvent.VK_A) {
            isAuto = !isAuto;
            //System.out.println("A pressed, isAuto = " + isAuto);
        }
        if (code == KeyEvent.VK_F && !gameFinished) {
            dp.nextTurn();
            winner = Game.getWinner();
            //System.out.println("F pressed\nMade physical turn");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAuto && !gameFinished) {
            dp.nextTurn();
            winner = Game.getWinner();
            System.out.println("Made Auto turn");
        }

        if (winner != null) {
            timer.stop();
            gameFinished = true;
            System.out.println("Winner is: " + winner);
        }

    }
}
