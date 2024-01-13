package base.game;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    BaseTile[] bases = {new BaseTile(PlayerColor.RED), new BaseTile(PlayerColor.GREEN), new BaseTile(PlayerColor.YELLOW), new BaseTile(PlayerColor.BLUE)};
    PlayerColor[] colors = {PlayerColor.RED, PlayerColor.GREEN, PlayerColor.YELLOW, PlayerColor.BLUE};

    public GameBoard() {
        GameTile startTile = new EntryTile(colors[0]);
        GameTile nextTile = startTile;
        for (int i = 2; i <= 52; i++) {
            if ((i - 14) % 13 == 0) {
                nextTile.nextTile = new EntryTile(colors[(i - 14) / 13 + 1]);
            } else {
                nextTile.nextTile = new GameTile();
                if ((i - 3) % 13 == 0) {
                    bases[(i - 3) / 13].nextTile = nextTile.nextTile;
                }
            }
            nextTile = nextTile.nextTile;
        }
        nextTile.nextTile = startTile;
    }

    public static class GameTile {
        int id;
        static int counter = 0;
        GameTile nextTile;

        public GameTile() {
            id = counter;
            counter++;
            //System.out.println(this);
        }

        public String toString() {
            return String.format("%s - %d", getClass().getSimpleName(), id);
        }

        public int getTileId() {
            return id;
        }

    }

    public static class SlottedTile extends GameTile {
        protected GameTile slot1;
        protected GameTile slot2;

        public SlottedTile() {
            super();

        }
    }

    //TODO сделать класс со слотами наследник GameTile и наследовать его всем кроме баз, потому что им слотыне нужны
    private static class BaseTile extends GameTile {
        PlayerColor clr;

        public BaseTile(PlayerColor clr) {
            super();
            this.clr = clr;
        }
    }

    private static class Pathway extends SlottedTile {
        public Pathway() {
            super();
        }

    }

    protected static class EntryTile extends SlottedTile {
        protected GameTile nextEntry;
        PlayerColor clr;

        public EntryTile(PlayerColor clr) {
            super();
            this.clr = clr;
            GameTile tile = new Pathway();
            nextEntry = tile;
            for (int i = 0; i < 4; i++) {
                tile.nextTile = new Pathway();
                tile = tile.nextTile;
            }
        }
    }
}
