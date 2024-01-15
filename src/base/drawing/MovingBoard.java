package base.drawing;

import java.awt.*;
import java.io.IOException;

public class MovingBoard {
    private int screenWidth, screenHeight;
    private int vertStartX;
    private int vertStartY;
    private int cellSize;


    public MovingBoard(int screenWidth, int screenHeight) throws IOException {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        cellSize = (int) (screenHeight * 0.055);
        vertStartX = (int) (screenWidth / 2 - 1.5 * cellSize);
        vertStartY = screenHeight / 12;
    }

    public void drawBoardTiles(Graphics2D g) {
        int yOffset;
        int xOffset;
        // vert white board
        yOffset = 0;
        g.setColor(Color.white);
        for (int i = 0; i < 15; i++) {
            g.fillRect(vertStartX, vertStartY + yOffset, cellSize, cellSize);
            g.fillRect(vertStartX + cellSize, vertStartY + yOffset, cellSize, cellSize);
            g.fillRect(vertStartX + 2 * cellSize, vertStartY + yOffset, cellSize, cellSize);
            yOffset += cellSize;
        }
        // horizontal white board
        xOffset = 0;
        for (int i = 0; i < 15; i++) {
            g.fillRect(vertStartX - 6 * cellSize + xOffset, vertStartY + 6 * cellSize, cellSize, cellSize);
            g.fillRect(vertStartX - 6 * cellSize + xOffset, vertStartY + 7 * cellSize, cellSize, cellSize);
            g.fillRect(vertStartX - 6 * cellSize + xOffset, vertStartY + 8 * cellSize, cellSize, cellSize);
            xOffset += cellSize;
        }
        //vert yellow cells
        yOffset = 0;
        g.setColor(new Color(150, 150, 0));
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                g.fillRect(vertStartX + 2 * cellSize, vertStartY + cellSize + yOffset, cellSize, cellSize);
            }
            g.fillRect(vertStartX + cellSize, vertStartY + cellSize + yOffset, cellSize, cellSize);
            yOffset += cellSize;
        }
        //vert red cells
        yOffset = 0;
        g.setColor(new Color(120, 0, 0));
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                g.fillRect(vertStartX, vertStartY + 9 * cellSize + yOffset, cellSize, cellSize);
            }
            g.fillRect(vertStartX + cellSize, vertStartY + 9 * cellSize + yOffset, cellSize, cellSize);
            yOffset += cellSize;
        }
        // horizontal green cells
        xOffset = 0;
        g.setColor(new Color(0, 120, 0));
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                g.fillRect(vertStartX - 5 * cellSize + xOffset, vertStartY + 6 * cellSize, cellSize, cellSize);
            }
            g.fillRect(vertStartX - 5 * cellSize + xOffset, vertStartY + 7 * cellSize, cellSize, cellSize);
            xOffset += cellSize;
        }
        // horizontal blue cells
        xOffset = 0;
        g.setColor(new Color(0, 0, 120));
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                g.fillRect(vertStartX + 3 * cellSize + xOffset, vertStartY + 8 * cellSize, cellSize, cellSize);
            }
            g.fillRect(vertStartX + 3 * cellSize + xOffset, vertStartY + 7 * cellSize, cellSize, cellSize);
            xOffset += cellSize;
        }
    }

    public void drawCenter(Graphics2D g) {
        //center
        int[] yYellowTri = {vertStartY + 6 * cellSize, vertStartY + 6 * cellSize, (int) (vertStartY + 7.5 * cellSize)};
        int[] xYellowTri = {vertStartX, vertStartX + 3 * cellSize, (int) (vertStartX + 1.5 * cellSize)};
        int[] yBlueTri = {vertStartY + 6 * cellSize, vertStartY + 9 * cellSize, (int) (vertStartY + 7.5 * cellSize)};
        int[] xBlueTri = {vertStartX + 3 * cellSize, vertStartX + 3 * cellSize, (int) (vertStartX + 1.5 * cellSize)};
        int[] yGreenTri = {vertStartY + 6 * cellSize, vertStartY + 9 * cellSize, (int) (vertStartY + 7.5 * cellSize)};
        int[] xGreenTri = {vertStartX, vertStartX, (int) (vertStartX + 1.5 * cellSize)};
        int[] xRedTri = {vertStartX, vertStartX + 3 * cellSize, (int) (vertStartX + 1.5 * cellSize)};
        int[] yRedTri = {vertStartY + 9 * cellSize, vertStartY + 9 * cellSize, (int) (vertStartY + 7.5 * cellSize)};
        Polygon yellowTri = new Polygon(xYellowTri, yYellowTri, 3);
        Polygon redTri = new Polygon(xRedTri, yRedTri, 3);
        Polygon blueTri = new Polygon(xBlueTri, yBlueTri, 3);
        Polygon greenTri = new Polygon(xGreenTri, yGreenTri, 3);
        g.setColor(new Color(150, 150, 0));
        g.fillPolygon(yellowTri);
        g.setColor(new Color(120, 0, 0));
        g.fillPolygon(redTri);
        g.setColor(new Color(0, 120, 0));
        g.fillPolygon(greenTri);
        g.setColor(new Color(0, 0, 120));
        g.fillPolygon(blueTri);
    }

    public void drawBases(Graphics2D g) {
        int ovalR = cellSize * 4;
        //colored bases
        g.setColor(new Color(0, 120, 0));
        g.fillOval(vertStartX - 5 * cellSize, vertStartY + cellSize, ovalR, ovalR);
        g.setColor(new Color(150, 150, 0));
        g.fillOval(vertStartX + 4 * cellSize, vertStartY + cellSize, ovalR, ovalR);
        g.setColor(new Color(120, 0, 0));
        g.fillOval(vertStartX - 5 * cellSize, vertStartY + 10 * cellSize, ovalR, ovalR);
        g.setColor(new Color(0, 0, 120));
        g.fillOval(vertStartX + 4 * cellSize, vertStartY + 10 * cellSize, ovalR, ovalR);
    }

    public void drawLayout(Graphics2D g) {
        Stroke str = new BasicStroke(3f);
        g.setStroke(str);
        g.setColor(Color.black);
        g.drawLine(vertStartX, vertStartY, vertStartX, vertStartY + 15 * cellSize);
        g.drawLine(vertStartX + cellSize, vertStartY, vertStartX + cellSize, vertStartY + 15 * cellSize);
        g.drawLine(vertStartX + 2 * cellSize, vertStartY, vertStartX + cellSize * 2, vertStartY + 15 * cellSize);
        g.drawLine(vertStartX + 3 * cellSize, vertStartY, vertStartX + cellSize * 3, vertStartY + 15 * cellSize);

        g.drawLine(vertStartX - 6 * cellSize, vertStartY + 6 * cellSize, vertStartX + 9 * cellSize, vertStartY + 6 * cellSize);
        g.drawLine(vertStartX - 6 * cellSize, vertStartY + 7 * cellSize, vertStartX + 9 * cellSize, vertStartY + 7 * cellSize);
        g.drawLine(vertStartX - 6 * cellSize, vertStartY + 8 * cellSize, vertStartX + 9 * cellSize, vertStartY + 8 * cellSize);
        g.drawLine(vertStartX - 6 * cellSize, vertStartY + 9 * cellSize, vertStartX + 9 * cellSize, vertStartY + 9 * cellSize);

        g.drawLine(vertStartX - 6 * cellSize, vertStartY, vertStartX + 9 * cellSize, vertStartY);
        g.drawLine(vertStartX - 6 * cellSize, vertStartY + 15 * cellSize, vertStartX + 9 * cellSize, vertStartY + 15 * cellSize);
        g.drawLine(vertStartX - 6 * cellSize, vertStartY, vertStartX - 6 * cellSize, vertStartY + 15 * cellSize);
        g.drawLine(vertStartX + 9 * cellSize, vertStartY, vertStartX + 9 * cellSize, vertStartY + 15 * cellSize);

        int lineOffset = 0;
        for (int i = 0; i < 16; i++) {
            g.drawLine(vertStartX, vertStartY + lineOffset, vertStartX + 3 * cellSize, vertStartY + lineOffset);
            lineOffset += cellSize;
        }

        lineOffset = 0;
        for (int i = 0; i < 16; i++) {
            g.drawLine(vertStartX - 6 * cellSize + lineOffset, vertStartY + 6 * cellSize, vertStartX - 6 * cellSize + lineOffset, vertStartY + 9 * cellSize);
            lineOffset += cellSize;
        }
    }

    private void drawText(Graphics2D g) {
        Font font = new Font("Georgia", Font.ITALIC, 30);
        g.setFont(font);
        String textF = "Press \"F\" to make one turn";
        String textA = "Press \"A\" to turn autoplay on/off";
        String textESC = "Press \"ESC\" to close application";
        g.setColor(Color.white);
        g.drawString(textF, (int) (screenWidth * 0.75), (int) (screenHeight * 0.2));
        g.drawString(textA, (int) (screenWidth * 0.75), (int) (screenHeight * 0.25));
        g.drawString(textESC, (int) (screenWidth * 0.75), (int) (screenHeight * 0.3));
    }

    public void drawMovingBoard(Graphics2D g) {
        drawBoardTiles(g);
        drawLayout(g);
        drawCenter(g);
        drawBases(g);
        drawText(g);


    }
}