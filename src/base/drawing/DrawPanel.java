package base.drawing;

import base.Main;
import base.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrawPanel extends JPanel {
    private Game game;
    private final int screenWidth = Main.screenWidth;
    private final int screenHeight = Main.screenHeight;
    private MovingBoard mb;
    private Image bgd;
    private JButton turnButton;
    private Figure[] redFig = new Figure[4];
    private Figure[] greenFig = new Figure[4];
    private Figure[] yellowFig = new Figure[4];
    private Figure[] blueFig = new Figure[4];
    private int cellSize = (int) (screenHeight * 0.055);
    private int startX = (int) (screenWidth / 2 - 1.5 * cellSize);
    private int startY = screenHeight / 12;
    private int curTurn = 1;
    private int base1X = (int) (startX - 5 * cellSize + (cellSize * 4) * 0.2);
    private int base2X = (int) (startX - 5 * cellSize + (cellSize * 4) * 0.2);
    private int base3X = (int) (startX + 4 * cellSize + (cellSize * 4) * 0.2);
    private int base4X = (int) (startX + 4 * cellSize + (cellSize * 4) * 0.2);

    private int base1Y = (int) (startY + 10 * cellSize + (cellSize * 4) * 0.2);
    private int base2Y = (int) (startY + cellSize + (cellSize * 4) * 0.2);
    private int base3Y = (int) (startY + cellSize + (cellSize * 4) * 0.2);
    private int base4Y = (int) (startY + 10 * cellSize + (cellSize * 4) * 0.2);

    //TODO добавить первые 4 координаты баз
    private final int[] tileX = {base1X, base2X, base3X, base4X, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize,
            startX, startX, startX, startX, startX, startX, startX - cellSize, startX - 2 * cellSize, startX - 3 * cellSize,
            startX - 4 * cellSize, startX - 5 * cellSize, startX - 6 * cellSize, startX - 6 * cellSize, startX - 5 * cellSize,
            startX - 4 * cellSize, startX - 3 * cellSize, startX - 2 * cellSize, startX - cellSize, startX - 6 * cellSize,
            startX - 5 * cellSize, startX - 4 * cellSize, startX - 3 * cellSize, startX - 2 * cellSize, startX - cellSize, startX, startX,
            startX, startX, startX, startX, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize, startX + cellSize,
            startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize,
            startX + 3 * cellSize, startX + 4 * cellSize, startX + 5 * cellSize, startX + 6 * cellSize, startX + 7 * cellSize, startX + 8 * cellSize, startX + 8 * cellSize,
            startX + 7 * cellSize, startX + 6 * cellSize, startX + 5 * cellSize, startX + 4 * cellSize, startX + 3 * cellSize, startX + 8 * cellSize,
            startX + 7 * cellSize, startX + 6 * cellSize, startX + 5 * cellSize, startX + 4 * cellSize, startX + 3 * cellSize, startX + 2 * cellSize,
            startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize, startX + 2 * cellSize};


    private final int[] tileY = {base1Y, base2Y, base3Y, base4Y, startY + 14 * cellSize, startY + 13 * cellSize, startY + 12 * cellSize, startY + 11 * cellSize, startY + 10 * cellSize,
            startY + 9 * cellSize, startY + 14 * cellSize, startY + 13 * cellSize, startY + 12 * cellSize, startY + 11 * cellSize, startY + 10 * cellSize,
            startY + 9 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize,
            startY + 8 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize,
            startY + 7 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize,
            startY + 6 * cellSize, startY + 5 * cellSize, startY + 4 * cellSize, startY + 3 * cellSize, startY + 2 * cellSize, startY + cellSize,
            startY, startY, startY + cellSize, startY + 2 * cellSize, startY + 3 * cellSize, startY + 4 * cellSize, startY + 5 * cellSize, startY,
            startY + cellSize, startY + 2 * cellSize, startY + 3 * cellSize, startY + 4 * cellSize, startY + 5 * cellSize, startY + 6 * cellSize,
            startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 6 * cellSize, startY + 7 * cellSize,
            startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 7 * cellSize, startY + 8 * cellSize,
            startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 8 * cellSize, startY + 9 * cellSize,
            startY + 10 * cellSize, startY + 11 * cellSize, startY + 12 * cellSize, startY + 13 * cellSize, startY + 14 * cellSize};

    public DrawPanel() throws IOException {
        turnButton = new JButton();
        try {
            bgd = ImageIO.read(new File("C:\\InmaTheKeeper\\Idea_Sketches\\OOP\\Task2\\images\\bgd.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mb = new MovingBoard(screenWidth, screenHeight);
        startGame();
        setupFigures();
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        g.drawImage(bgd, 0, 0, screenWidth, screenHeight, null);
        mb.drawMovingBoard(g);
        drawFigures(g);

    }

    private void setupFigures() {
        int width = cellSize * 4;
        redFig[0] = new Figure((int) (startX - 5 * cellSize + width * 0.2), (int) (startY + 10 * cellSize + width * 0.2), width / 5, Color.red);
        redFig[1] = new Figure((int) (startX - 5 * cellSize + width * 0.2), (int) (startY + 10 * cellSize + width * 0.6), width / 5, Color.red);
        redFig[2] = new Figure((int) (startX - 5 * cellSize + width * 0.6), (int) (startY + 10 * cellSize + width * 0.2), width / 5, Color.red);
        redFig[3] = new Figure((int) (startX - 5 * cellSize + width * 0.6), (int) (startY + 10 * cellSize + width * 0.6), width / 5, Color.red);

        greenFig[0] = new Figure((int) (startX - 5 * cellSize + width * 0.2), (int) (startY + cellSize + width * 0.2), width / 5, Color.green);
        greenFig[1] = new Figure((int) (startX - 5 * cellSize + width * 0.2), (int) (startY + cellSize + width * 0.6), width / 5, Color.green);
        greenFig[2] = new Figure((int) (startX - 5 * cellSize + width * 0.6), (int) (startY + cellSize + width * 0.2), width / 5, Color.green);
        greenFig[3] = new Figure((int) (startX - 5 * cellSize + width * 0.6), (int) (startY + cellSize + width * 0.6), width / 5, Color.green);

        yellowFig[0] = new Figure((int) (startX + 4 * cellSize + width * 0.2), (int) (startY + cellSize + width * 0.2), width / 5, Color.yellow);
        yellowFig[1] = new Figure((int) (startX + 4 * cellSize + width * 0.2), (int) (startY + cellSize + width * 0.6), width / 5, Color.yellow);
        yellowFig[2] = new Figure((int) (startX + 4 * cellSize + width * 0.6), (int) (startY + cellSize + width * 0.2), width / 5, Color.yellow);
        yellowFig[3] = new Figure((int) (startX + 4 * cellSize + width * 0.6), (int) (startY + cellSize + width * 0.6), width / 5, Color.yellow);

        blueFig[0] = new Figure((int) (startX + 4 * cellSize + width * 0.2), (int) (startY + 10 * cellSize + width * 0.2), width / 5, Color.blue);
        blueFig[1] = new Figure((int) (startX + 4 * cellSize + width * 0.2), (int) (startY + 10 * cellSize + width * 0.6), width / 5, Color.blue);
        blueFig[2] = new Figure((int) (startX + 4 * cellSize + width * 0.6), (int) (startY + 10 * cellSize + width * 0.2), width / 5, Color.blue);
        blueFig[3] = new Figure((int) (startX + 4 * cellSize + width * 0.6), (int) (startY + 10 * cellSize + width * 0.6), width / 5, Color.blue);

    }

    private void drawFigures(Graphics2D g) {
        for (int i = 0; i < 4; i++) {
            redFig[i].drawFigure(i + 1, g);
            greenFig[i].drawFigure(i + 1, g);
            yellowFig[i].drawFigure(i + 1, g);
            blueFig[i].drawFigure(i + 1, g);
        }
    }

    public void startGame() {
        game = new Game();
    }

    public void nextTurn() {
        int coordId;
        int pieceId;
        switch (curTurn) {
            case 1 -> {
                pieceId = game.makeTurn(PlayerColor.RED);
                coordId = game.getRedFigs()[pieceId].getCurTile().getTileId();
                redFig[pieceId].setX(tileX[coordId]);
                redFig[pieceId].setY(tileY[coordId]);
                curTurn++;
            }
            case 2 -> {
                pieceId = game.makeTurn(PlayerColor.YELLOW);
                coordId = game.getYellowFigs()[pieceId].getCurTile().getTileId();
                yellowFig[pieceId].setX(tileX[coordId]);
                yellowFig[pieceId].setY(tileY[coordId]);
                curTurn++;
            }
            case 3 -> {
                pieceId = game.makeTurn(PlayerColor.GREEN);
                coordId = game.getGreenFigs()[pieceId].getCurTile().getTileId();
                greenFig[pieceId].setX(tileX[coordId]);
                greenFig[pieceId].setY(tileY[coordId]);
                curTurn++;
            }
            case 4 -> {
                pieceId = game.makeTurn(PlayerColor.BLUE);
                coordId = game.getBlueFigs()[pieceId].getCurTile().getTileId();
                blueFig[pieceId].setX(tileX[coordId]);
                blueFig[pieceId].setY(tileY[coordId]);
                curTurn = 1;
            }
        }
        repaint();
    }
}
