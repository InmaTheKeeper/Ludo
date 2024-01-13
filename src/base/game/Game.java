package base.game;

import java.util.Random;

public class Game {
    private GameBoard gb;
    private GamePiece[] red = new GamePiece[4];
    private GamePiece[] green = new GamePiece[4];
    private GamePiece[] yellow = new GamePiece[4];
    private GamePiece[] blue = new GamePiece[4];
    private Random rnd = new Random();

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
        int pieceId = rnd.nextInt(0, 4);
        switch (clr) {
            case RED:
                red[pieceId].moveBy(n);
                break;
            case GREEN:
                green[pieceId].moveBy(n);
                break;
            case YELLOW:
                yellow[pieceId].moveBy(n);
                break;
            case BLUE:
                blue[pieceId].moveBy(n);
                break;
        }
        return pieceId;
    }
}
