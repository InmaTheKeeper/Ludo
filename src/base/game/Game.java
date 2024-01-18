package base.game;

import java.util.Random;

public class Game {
    private GameBoard gb;
    private GamePiece[] red = new GamePiece[4];
    private GamePiece[] green = new GamePiece[4];
    private GamePiece[] yellow = new GamePiece[4];
    private GamePiece[] blue = new GamePiece[4];
    private Random rnd = new Random();
    private static int redHomeCounter = 0;
    private static int greenHomeCounter = 0;
    private static int yellowHomeCounter = 0;
    private static int blueHomeCounter = 0;

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

    public int getBlueHomeCounter() {
        return blueHomeCounter;
    }

    public int getGreenHomeCounter() {
        return greenHomeCounter;
    }

    public int getRedHomeCounter() {
        return redHomeCounter;
    }

    public int getYellowHomeCounter() {
        return yellowHomeCounter;
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

    private int findPlayableFig(PlayerColor clr) {
        int pieceId = -1;
        switch (clr) {
            case RED -> {
                for (int i = 0; i < red.length; i++) {
                    if (!red[i].isOnBase() && !red[i].isHome()) {
                        pieceId = i;
                    }

                }
            }
            case GREEN -> {
                for (int i = 0; i < green.length; i++) {
                    if (!green[i].isOnBase() && !green[i].isHome()) {
                        pieceId = i;
                    }

                }
            }
            case YELLOW -> {
                for (int i = 0; i < yellow.length; i++) {
                    if (!yellow[i].isOnBase() && !yellow[i].isHome()) {
                        pieceId = i;
                    }

                }
            }
            case BLUE -> {
                for (int i = 0; i < blue.length; i++) {
                    if (!blue[i].isOnBase() && !blue[i].isHome()) {
                        pieceId = i;
                    }

                }
            }
        }
        return pieceId;
    }

    private void updateHomes() {
        redHomeCounter = 0;
        greenHomeCounter = 0;
        yellowHomeCounter = 0;
        blueHomeCounter = 0;
        for (int i = 0; i < 4; i++) {
            if (red[i].isHome()) {
                redHomeCounter++;
            }
            if (green[i].isHome()) {
                greenHomeCounter++;
            }
            if (yellow[i].isHome()) {
                yellowHomeCounter++;
            }
            if (blue[i].isHome()) {
                blueHomeCounter++;
            }
        }

    }

    public static PlayerColor getWinner() {
        if (redHomeCounter == 4) {
            return PlayerColor.RED;
        }
        if (greenHomeCounter == 4) {
            return PlayerColor.GREEN;
        }
        if (yellowHomeCounter == 4) {
            return PlayerColor.YELLOW;
        }
        if (blueHomeCounter == 4) {
            return PlayerColor.BLUE;
        }
        return null;
    }

    //Не сделано:
    //TODO: 3) Конец игры
    //TODO: 4) Обновленная отрисовка
    public int makeTurn(PlayerColor clr, int n) {
        int rndPieceId = rnd.nextInt(0, 4);
        int pieceId = -1;
        switch (clr) {
            case RED -> {
                if (n == 6) {
                    if (GameBoard.bases[0].getFigAmount() != 0) {
                        n = 1;
                        pieceId = findFigOnBase(PlayerColor.RED);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findPlayableFig(PlayerColor.RED);
                    if (pieceId == -1) {
                        System.out.println("Player: " + clr + "\nTurn skipped");
                        return -1;
                    }
                }
                System.out.println("PieceId: " + (pieceId + 1) + "\nPlayer: " + clr);
                red[pieceId].moveBy(n);
            }
            case GREEN -> {
                if (n == 6) {
                    if (GameBoard.bases[1].getFigAmount() != 0) {
                        n = 1;
                        pieceId = findFigOnBase(PlayerColor.GREEN);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findPlayableFig(PlayerColor.GREEN);
                    if (pieceId == -1) {
                        System.out.println("Player: " + clr + "\nTurn skipped");
                        return -1;
                    }
                }
                System.out.println("PieceId: " + (pieceId + 1) + "\nPlayer: " + clr);
                green[pieceId].moveBy(n);
            }
            case YELLOW -> {
                if (n == 6) {
                    if (GameBoard.bases[2].getFigAmount() != 0) {
                        n = 1;
                        pieceId = findFigOnBase(PlayerColor.YELLOW);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findPlayableFig(PlayerColor.YELLOW);
                    if (pieceId == -1) {
                        System.out.println("Player: " + clr + "\nTurn skipped");
                        return -1;
                    }
                }
                System.out.println("PieceId: " + (pieceId + 1) + "\nPlayer: " + clr);
                yellow[pieceId].moveBy(n);
            }
            case BLUE -> {
                if (n == 6) {
                    if (GameBoard.bases[3].getFigAmount() != 0) {
                        n = 1;
                        pieceId = findFigOnBase(PlayerColor.BLUE);
                    } else {
                        pieceId = rndPieceId;
                    }
                } else {
                    pieceId = findPlayableFig(PlayerColor.BLUE);
                    if (pieceId == -1) {
                        System.out.println("Player: " + clr + "\nTurn skipped");
                        return -1;
                    }
                }
                System.out.println("PieceId: " + (pieceId + 1) + "\nPlayer: " + clr);
                blue[pieceId].moveBy(n);
            }
        }
        updateHomes();
        System.out.println("***Game progress***");
        System.out.println("redHome: "+ redHomeCounter);
        System.out.println("greenHome: "+ greenHomeCounter);
        System.out.println("yellowHome: "+ yellowHomeCounter);
        System.out.println("blueHome: "+ blueHomeCounter);
        System.out.println("Turn end");
        System.out.println("****************************");
        return pieceId;
    }
}
