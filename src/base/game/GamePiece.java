package base.game;

public class GamePiece {
    private final PlayerColor clr;
    private GameBoard.GameTile currentTile;
    private int toHome;
    protected boolean isHome;

    public GamePiece(PlayerColor clr, GameBoard.GameTile currentTile) {
        this.clr = clr;
        this.currentTile = currentTile;
        toHome = 57;
        isHome = false;
    }

    public PlayerColor getClr() {
        return clr;
    }

    public boolean isOnBase() {
        return currentTile instanceof GameBoard.BaseTile;
    }

    public boolean isHome() {
        return isHome;
    }

    public GameBoard.GameTile getCurTile() {
        return currentTile;
    }

    public void moveBy(int n) {
        if (n > toHome) {
            return;
        }
        clearSlot();
        if (n == toHome) {
            isHome = true;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (currentTile.nextTile.isBlocked()) {
                break;
            } else if (currentTile instanceof GameBoard.BaseTile baseTile) {
                baseTile.figAmount--;
                currentTile = currentTile.nextTile;
            } else if (currentTile instanceof GameBoard.EntryTile entryTile && entryTile.clr == clr) {
                currentTile = entryTile.nextEntry;
            } else {
                currentTile = currentTile.nextTile;
            }
            toHome--;
            System.out.println("Current tile: " + currentTile);
        }
        checkTileSlots(currentTile);
        System.out.println("****************************");
    }

    private void checkTileSlots(GameBoard.GameTile gt) {
        if (gt.getSlot1() == null) {
            gt.setSlot1(this);
            if (gt.getSlot2() != null && gt.getSlot2().getClr() != this.clr) {
                moveTileToBase(gt.getSlot2().getClr(), gt.getSlot2());
                gt.setSlot2(null);
            }
        } else if (gt.getSlot2() == null) {
            gt.setSlot2(this);
            if (gt.getSlot1() != null && gt.getSlot1().getClr() != this.clr) {
                moveTileToBase(gt.getSlot1().getClr(), gt.getSlot1());
                gt.setSlot2(null);
            }
        }
    }

    private void moveTileToBase(PlayerColor clr, GamePiece piece) {
        GameBoard.BaseTile gt = null;
        switch (clr) {
            case RED -> {
                gt = GameBoard.bases[0];
                piece.currentTile = gt;
            }
            case GREEN -> {
                gt = GameBoard.bases[1];
                piece.currentTile = gt;
            }
            case YELLOW -> {
                gt = GameBoard.bases[2];
                piece.currentTile = gt;
            }
            case BLUE -> {
                gt = GameBoard.bases[3];
                piece.currentTile = gt;
            }
        }
        if (gt.getSlot1() == null) {
            gt.setSlot1(this);
        } else if (gt.getSlot2() == null) {
            gt.setSlot2(this);
        } else if (gt.getSlot3() == null) {
            gt.setSlot3(this);
        } else if (gt.getSlot4() == null) {
            gt.setSlot4(this);
        }
    }

    private void clearSlot() {
        if (currentTile.getSlot1() == this) {
            currentTile.setSlot1(null);
        } else {
            currentTile.setSlot2(null);
        }
    }
}