package base.game;

public class GamePiece {
    private final PlayerColor clr;
    private GameBoard.GameTile currentTile;

    public GamePiece(PlayerColor clr, GameBoard.GameTile currentTile) {
        this.clr = clr;
        this.currentTile = currentTile;
    }

    public GameBoard.GameTile getCurTile() {
        return currentTile;
    }
    //TODO сделать функцию для проверки следующей клетки по слотам, да и вообще здесь обработка правил
    public void moveBy(int n) {
        System.out.println("Rolled " + n);
        for (int i = 0; i < n; i++) {
            if (currentTile instanceof GameBoard.EntryTile entryTile && entryTile.clr == clr) {
                currentTile = entryTile.nextEntry;
            } else {
                currentTile = currentTile.nextTile;
            }
            System.out.println("Current tile: " + currentTile);
        }
        System.out.println("****************************");
    }
}