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
            System.out.println("Overthrown home");
            return;
        }
        clearSlot();
        if (n == toHome) {
            isHome = true;
            System.out.println("Got to home");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (currentTile.nextTile.isBlocked()) {
                System.out.println("Block ahead, can't move");
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
    }

    private void checkTileSlots(GameBoard.GameTile gt) {
        if (gt.getSlot1() == null) {
            gt.setSlot1(this);
            if (gt.getSlot2() != null && gt.getSlot2().getClr() != this.clr) {
                System.out.println("Killed another piece");
                moveTileToBase(gt.getSlot2());
                gt.setSlot2(null);
            }
        } else if (gt.getSlot2() == null) {
            gt.setSlot2(this);
            if (gt.getSlot1() != null && gt.getSlot1().getClr() != this.clr) {
                System.out.println("Killed another piece");
                moveTileToBase(gt.getSlot1());
                gt.setSlot2(null);
            }
        }
    }

    private void moveTileToBase(GamePiece piece) {
        PlayerColor temp = piece.getClr();
        GameBoard.BaseTile gt = null;
        switch (temp) {
            case RED -> {
                gt = GameBoard.bases[0];
                piece.currentTile = gt;
                gt.figAmount++;
            }
            case GREEN -> {
                gt = GameBoard.bases[1];
                piece.currentTile = gt;
                gt.figAmount++;
            }
            case YELLOW -> {
                gt = GameBoard.bases[2];
                piece.currentTile = gt;
                gt.figAmount++;
            }
            case BLUE -> {
                gt = GameBoard.bases[3];
                piece.currentTile = gt;
                gt.figAmount++;
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
        if (currentTile instanceof GameBoard.BaseTile baseTile) {
            if (baseTile.getSlot1() == this) {
                baseTile.setSlot1(null);
            } else if (baseTile.getSlot2() == this) {
                baseTile.setSlot2(null);
            } else if (baseTile.getSlot3() == this) {
                baseTile.setSlot3(null);
            } else if (baseTile.getSlot4() == this) {
                baseTile.setSlot4(null);
            }
        } else if (currentTile.getSlot1() == this) {
            currentTile.setSlot1(null);
        } else if (currentTile.getSlot2() == this) {
            currentTile.setSlot2(null);
        }
    }
}