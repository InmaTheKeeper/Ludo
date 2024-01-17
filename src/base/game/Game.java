package base.game;

import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Game {
    private GameBoard gb;
    private GamePiece[] red = new GamePiece[4];
    private GamePiece[] green = new GamePiece[4];
    private GamePiece[] yellow = new GamePiece[4];
    private GamePiece[] blue = new GamePiece[4];
    private Random rnd = new Random();
    private int sixCounter;
    public int redHomeCounter = 0;
    public int greenHomeCounter = 0;
    public int yellowHomeCounter = 0;
    public int blueHomeCounter = 0;

    public Game() {
        //создаем поле
        gb = new GameBoard();
        //создаем фигурки
        spawnFigures();
    }

    public GamePiece[] getRedFigs() {
        return red;
    }

    public GamePiece[] getGreenFigs() {
        return green;
    }

    public GamePiece[] getYellowFigs() {
        return yellow;
    }

    public GamePiece[] getBlueFigs() {
        return blue;
    }


    private void spawnFigures() {
        for (int i = 0; i < 4; i++) {
            red[i] = new GamePiece(PlayerColor.RED, gb.bases[0]);
            green[i] = new GamePiece(PlayerColor.GREEN, gb.bases[1]);
            yellow[i] = new GamePiece(PlayerColor.YELLOW, gb.bases[2]);
            blue[i] = new GamePiece(PlayerColor.BLUE, gb.bases[3]);
        }
    }

    private int findFigOnBase(PlayerColor clr) {
        int pieceId = -1;
        switch (clr) {
            case RED -> {
                for (int i = 0; i < red.length; i++) {
                    if (red[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case GREEN -> {
                for (int i = 0; i < green.length; i++) {
                    if (green[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case YELLOW -> {
                for (int i = 0; i < yellow.length; i++) {
                    if (yellow[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case BLUE -> {
                for (int i = 0; i < blue.length; i++) {
                    if (blue[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
        }
        return pieceId;
    }

    private int findFigNotOnBase(PlayerColor clr) {
        int pieceId = -1;
        switch (clr) {
            case RED -> {
                for (int i = 0; i < red.length; i++) {
                    if (!red[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case GREEN -> {
                for (int i = 0; i < green.length; i++) {
                    if (!green[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case YELLOW -> {
                for (int i = 0; i < yellow.length; i++) {
                    if (!yellow[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
            case BLUE -> {
                for (int i = 0; i < blue.length; i++) {
                    if (!blue[i].isOnBase()) {
                        pieceId = i;
                    }

                }
            }
        }
        return pieceId;
    }

    //Не сделано:
    //TODO: 2) Повторение хода при шестерках
    //TODO: 3) Конец игры
    //TODO: 4) Обновленная отрисовка
    public int makeTurn(PlayerColor clr) {
        int n = rnd.nextInt(1, 7);
        System.out.println("****************************");
        System.out.println("Rolled " + n);
        int rndPieceId = rnd.nextInt(0, 4);
        int pieceId = -1;
        switch (clr) {
            case RED -> {
                if (n == 6) {
                    n = 1;
                    if (GameBoard.bases[0].getFigAmount() != 0) {
                        pieceId = findFigOnBase(PlayerColor.RED);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findFigNotOnBase(PlayerColor.RED);
                    if (pieceId == -1) {
                        break;
                    }
                }
                System.out.println("PieceId: " + pieceId);
                red[pieceId].moveBy(n);
            }
            case GREEN -> {
                if (n == 6) {
                    n = 1;
                    if (GameBoard.bases[1].getFigAmount() != 0) {
                        pieceId = findFigOnBase(PlayerColor.GREEN);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findFigNotOnBase(PlayerColor.GREEN);
                    if (pieceId == -1) {
                        break;
                    }
                }
                System.out.println("PieceId: " + pieceId);
                green[pieceId].moveBy(n);
            }
            case YELLOW -> {
                if (n == 6) {
                    n = 1;
                    if (GameBoard.bases[2].getFigAmount() != 0) {
                        pieceId = findFigOnBase(PlayerColor.YELLOW);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findFigNotOnBase(PlayerColor.YELLOW);
                    if (pieceId == -1) {
                        break;
                    }
                }
                System.out.println("PieceId: " + pieceId);
                yellow[pieceId].moveBy(n);
            }
            case BLUE -> {
                if (n == 6) {
                    n = 1;
                    if (GameBoard.bases[3].getFigAmount() != 0) {
                        pieceId = findFigOnBase(PlayerColor.BLUE);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findFigNotOnBase(PlayerColor.BLUE);
                    if (pieceId == -1) {
                        break;
                    }
                }
                System.out.println("PieceId: " + pieceId);
                blue[pieceId].moveBy(n);
            }
        }
        return pieceId;
    }
}
