package base.drawing;

import base.Main;
import base.game.*;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static base.Main.screenHeight;
import static base.Main.screenWidth;

public class DrawPanel extends JPanel {
    private Game game;
    private final int screenWidth = Main.screenWidth;
    private final int screenHeight = Main.screenHeight;
    private final MovingBoard mb;
    private Image bgd;
    private final Random rnd = new Random();
    private final Figure[] redFig = new Figure[4];
    private final Figure[] greenFig = new Figure[4];
    private final Figure[] yellowFig = new Figure[4];
    private final Figure[] blueFig = new Figure[4];
    private final int cellSize = (int) (screenHeight * 0.055);
    private int startX = (int) (screenWidth / 2 - 1.5 * cellSize);
    private int startY = screenHeight / 12;
    private int curTurn = 0;
    private final int base1X = (int) (startX - 5 * cellSize + (cellSize * 4) * 0.2);
    private final int base2X = (int) (startX - 5 * cellSize + (cellSize * 4) * 0.2);
    private final int base3X = (int) (startX + 4 * cellSize + (cellSize * 4) * 0.2);
    private final int base4X = (int) (startX + 4 * cellSize + (cellSize * 4) * 0.2);

    private final int base1Y = (int) (startY + 10 * cellSize + (cellSize * 4) * 0.2);
    private final int base2Y = (int) (startY + cellSize + (cellSize * 4) * 0.2);
    private final int base3Y = (int) (startY + cellSize + (cellSize * 4) * 0.2);
    private final int base4Y = (int) (startY + 10 * cellSize + (cellSize * 4) * 0.2);

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
        try {
            bgd = ImageIO.read(new File("images/bgd.jpg"));
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
        int width = cellSize * 3;

        redFig[0] = new Figure((int) (startX - 4.5 * cellSize + width * 0.2), (int) (startY + 10.5 * cellSize + width * 0.2), width / 5, Color.red);
        redFig[1] = new Figure((int) (startX - 4.5 * cellSize + width * 0.2), (int) (startY + 10.5 * cellSize + width * 0.6), width / 5, Color.red);
        redFig[2] = new Figure((int) (startX - 4.5 * cellSize + width * 0.6), (int) (startY + 10.5 * cellSize + width * 0.2), width / 5, Color.red);
        redFig[3] = new Figure((int) (startX - 4.5 * cellSize + width * 0.6), (int) (startY + 10.5 * cellSize + width * 0.6), width / 5, Color.red);

        greenFig[0] = new Figure((int) (startX - 4.5 * cellSize + width * 0.2), (int) (startY + 1.5 * cellSize + width * 0.2), width / 5, Color.green);
        greenFig[1] = new Figure((int) (startX - 4.5 * cellSize + width * 0.2), (int) (startY + 1.5 * cellSize + width * 0.6), width / 5, Color.green);
        greenFig[2] = new Figure((int) (startX - 4.5 * cellSize + width * 0.6), (int) (startY + 1.5 * cellSize + width * 0.2), width / 5, Color.green);
        greenFig[3] = new Figure((int) (startX - 4.5 * cellSize + width * 0.6), (int) (startY + 1.5 * cellSize + width * 0.6), width / 5, Color.green);

        yellowFig[0] = new Figure((int) (startX + 4.5 * cellSize + width * 0.2), (int) (startY + 1.5 * cellSize + width * 0.2), width / 5, Color.yellow);
        yellowFig[1] = new Figure((int) (startX + 4.5 * cellSize + width * 0.2), (int) (startY + 1.5 * cellSize + width * 0.6), width / 5, Color.yellow);
        yellowFig[2] = new Figure((int) (startX + 4.5 * cellSize + width * 0.6), (int) (startY + 1.5 * cellSize + width * 0.2), width / 5, Color.yellow);
        yellowFig[3] = new Figure((int) (startX + 4.5 * cellSize + width * 0.6), (int) (startY + 1.5 * cellSize + width * 0.6), width / 5, Color.yellow);

        blueFig[0] = new Figure((int) (startX + 4.5 * cellSize + width * 0.2), (int) (startY + 10.5 * cellSize + width * 0.2), width / 5, Color.blue);
        blueFig[1] = new Figure((int) (startX + 4.5 * cellSize + width * 0.2), (int) (startY + 10.5 * cellSize + width * 0.6), width / 5, Color.blue);
        blueFig[2] = new Figure((int) (startX + 4.5 * cellSize + width * 0.6), (int) (startY + 10.5 * cellSize + width * 0.2), width / 5, Color.blue);
        blueFig[3] = new Figure((int) (startX + 4.5 * cellSize + width * 0.6), (int) (startY + 10.5 * cellSize + width * 0.6), width / 5, Color.blue);

    }

    private void drawFigures(Graphics2D g) {
        for (int i = 0; i < 4; i++) {
            redFig[i].drawFigure(i + 1, g);
            greenFig[i].drawFigure(i + 1, g);
            yellowFig[i].drawFigure(i + 1, g);
            blueFig[i].drawFigure(i + 1, g);
        }
    }

    public void drawWinnerScreen(Graphics2D g, PlayerColor clr) {
        int rectX = (int) (screenWidth * 0.4);
        int rectY = (int) (screenHeight * 0.3);
        int rectWidth = (int) (screenWidth * 0.2);
        int rectHeight = (int) (screenHeight * 0.3);
        g.setColor(new Color(229, 152, 18));
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
        Font font = new Font("Georgia", Font.ITALIC, 30);
        g.setFont(font);
        String text = "The winner is: " + clr;
        g.drawString(text, rectX + rectWidth / 2, rectY + rectHeight / 2);
    }

    public void startGame() {
        game = new Game();
    }

    public void nextTurn() {
        int offset = (int) (cellSize * 0.1);
        int coordId;
        int pieceId;
        int n;
        int sixCounter = 0;
        do {
            sixCounter++;
            n = rnd.nextInt(1, 7);
            System.out.println("Rolled: " + n);
            switch (curTurn) {
                case 0 -> {
                    pieceId = game.makeTurn(PlayerColor.RED, n);
                    if (pieceId != -1) {
                        coordId = game.getRedFigs()[pieceId].getCurTile().getTileId();
                        if (game.getRedFigs()[pieceId].getCurTile().isBlocked()) {
                            redFig[pieceId].setX(tileX[coordId] + 5 * offset);
                            redFig[pieceId].setY(tileY[coordId] + offset);
                        } else {
                            redFig[pieceId].setX(tileX[coordId]);
                            redFig[pieceId].setY(tileY[coordId] + offset);
                        }
                    }
                }
                case 1 -> {
                    pieceId = game.makeTurn(PlayerColor.GREEN, n);
                    if (pieceId != -1) {
                        coordId = game.getGreenFigs()[pieceId].getCurTile().getTileId();
                        if (game.getGreenFigs()[pieceId].getCurTile().isBlocked()) {
                            greenFig[pieceId].setX(tileX[coordId] + 5 * offset);
                            greenFig[pieceId].setY(tileY[coordId] + offset);
                        } else {
                            greenFig[pieceId].setX(tileX[coordId]);
                            greenFig[pieceId].setY(tileY[coordId] + offset);
                        }
                    }
                }
                case 2 -> {
                    pieceId = game.makeTurn(PlayerColor.YELLOW, n);
                    if (pieceId != -1) {
                        coordId = game.getYellowFigs()[pieceId].getCurTile().getTileId();
                        if (game.getYellowFigs()[pieceId].getCurTile().isBlocked()) {
                            yellowFig[pieceId].setX(tileX[coordId] + 5 * offset);
                            yellowFig[pieceId].setY(tileY[coordId] + offset);
                        } else {
                            yellowFig[pieceId].setX(tileX[coordId]);
                            yellowFig[pieceId].setY(tileY[coordId] + offset);
                        }
                    }
                }
                case 3 -> {
                    pieceId = game.makeTurn(PlayerColor.BLUE, n);
                    if (pieceId != -1) {
                        coordId = game.getBlueFigs()[pieceId].getCurTile().getTileId();
                        if (game.getBlueFigs()[pieceId].getCurTile().isBlocked()) {
                            blueFig[pieceId].setX(tileX[coordId] + 5 * offset);
                            blueFig[pieceId].setY(tileY[coordId] + offset);
                        } else {
                            blueFig[pieceId].setX(tileX[coordId]);
                            blueFig[pieceId].setY(tileY[coordId] + offset);
                        }
                    }
                }
            }
        } while (n == 6 && sixCounter < 3);
        curTurn = (curTurn + 1) % 4;
        repaint();
    }
}
