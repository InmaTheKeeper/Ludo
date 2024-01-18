package base.game;

public class GameBoard {
    protected static BaseTile[] bases = {new BaseTile(PlayerColor.RED), new BaseTile(PlayerColor.GREEN), new BaseTile(PlayerColor.YELLOW), new BaseTile(PlayerColor.BLUE)};
    private PlayerColor[] colors = {PlayerColor.RED, PlayerColor.GREEN, PlayerColor.YELLOW, PlayerColor.BLUE};

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
        protected GameTile nextTile;
        protected GamePiece slot1, slot2;

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

        public GamePiece getSlot1() {
            return slot1;
        }

        public GamePiece getSlot2() {
            return slot2;
        }

        public void setSlot1(GamePiece slot1) {
            this.slot1 = slot1;
        }

        public void setSlot2(GamePiece slot2) {
            this.slot2 = slot2;
        }

        protected boolean isBlocked() {
            return (slot1 != null && slot2 != null && slot1.getClr().equals(slot2.getClr()));
        }
    }

    protected static class BaseTile extends GameTile {
        PlayerColor clr;
        protected int figAmount;
        protected GamePiece slot3, slot4;

        public BaseTile(PlayerColor clr) {
            super();
            this.clr = clr;
            figAmount = 4;
        }

        protected int getFigAmount() {
            return figAmount;
        }

        protected GamePiece getSlot3() {
            return slot3;
        }

        protected GamePiece getSlot4() {
            return slot4;
        }

        protected void setSlot3(GamePiece slot3) {
            this.slot3 = slot3;
        }

        protected void setSlot4(GamePiece slot4) {
            this.slot4 = slot4;
        }

    }

    protected  class HomeTile extends BaseTile{
        public HomeTile(PlayerColor clr) {
            super(clr);
        }
    }


    private static class Pathway extends GameTile {
        public Pathway() {
            super();
        }

    }

    protected static class EntryTile extends GameTile {
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
