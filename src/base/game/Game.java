package base.game;

import java.util.Random;

public class Game {
    private GameBoard gb;
    private GamePiece[] red = new GamePiece[4];
    private GamePiece[] green = new GamePiece[4];
    private GamePiece[] yellow = new GamePiece[4];
    private GamePiece[] blue = new GamePiece[4];
    private Random rnd = new Random();
    private int sixCounter;

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

    public int makeTurn(PlayerColor clr) {
        int n = rnd.nextInt(1, 7);
        System.out.println("Rolled " + n);
        int rndPieceId = rnd.nextInt(0, 4);
        switch (clr) {
            case RED:
                if (n == 6) {
                    if (GameBoard.bases[0].getFigAmount() != 0) {
                        for (int i = 0; i < red.length; i++) {
                            if (red[i].isOnBase()) {
                                red[i].moveBy(n);
                                return i;
                            }
                        }
                    } else {
                        red[rndPieceId].moveBy(n);
                        return rndPieceId;
                    }
                } else {
                    for (int i = 0; i < red.length; i++) {
                        if (!red[i].isOnBase()) {
                            red[i].moveBy(n);
                            return i;
                        }
                    }
                }
                break;
            case GREEN:
                if (n == 6) {
                    if (GameBoard.bases[1].getFigAmount() != 0) {
                        for (int i = 0; i < green.length; i++) {
                            if (green[i].isOnBase()) {
                                green[i].moveBy(n);
                                return i;
                            }
                        }
                    } else {
                        green[rndPieceId].moveBy(n);
                        return rndPieceId;
                    }
                } else {
                    for (int i = 0; i < green.length; i++) {
                        if (!green[i].isOnBase()) {
                            green[i].moveBy(n);
                            return i;
                        }
                    }
                }
                break;
            case YELLOW:
                if (n == 6) {
                    if (GameBoard.bases[2].getFigAmount() != 0) {
                        for (int i = 0; i < yellow.length; i++) {
                            if (yellow[i].isOnBase()) {
                                yellow[i].moveBy(n);
                                return i;
                            }
                        }
                    } else {
                        yellow[rndPieceId].moveBy(n);
                        return rndPieceId;
                    }
                } else {
                    for (int i = 0; i < yellow.length; i++) {
                        if (!yellow[i].isOnBase()) {
                            yellow[i].moveBy(n);
                            return i;
                        }
                    }
                }
                break;
            case BLUE:
                if (n == 6) {
                    if (GameBoard.bases[3].getFigAmount() != 0) {
                        for (int i = 0; i < blue.length; i++) {
                            if (blue[i].isOnBase()) {
                                blue[i].moveBy(n);
                                return i;
                            }
                        }
                    } else {
                        blue[rndPieceId].moveBy(n);
                        return rndPieceId;
                    }
                } else {
                    for (int i = 0; i < blue.length; i++) {
                        if (!blue[i].isOnBase()) {
                            blue[i].moveBy(n);
                            return i;
                        }
                    }
                }
                break;
        }

        return rndPieceId;
    }
}
