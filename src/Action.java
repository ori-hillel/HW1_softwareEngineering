public class Action {
    /**
     * The action class represents a made move.
     * An action consists of a direction, and the tile which the move was made on.
     */
    private Tile tile;
    private Enum direction;

    public Action(Tile tile, Enum direction) {
        this.tile = tile;
        this.direction = direction;
    }
    public Action() {}

    public Enum getDirection() {
        return direction;
    }

    public void setDirection(Enum otherDirection) {
        this.direction = otherDirection;
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile otherTile) {
        this.tile = otherTile;
    }

    /**
     * An override to the  Object Class's toString() method.
     * @return a string, which describes the action.
     */
    public String toString() {
        if (this.direction != null)
            return String.format("Move %d %s", tile.getValue(), direction.getDirection().toLowerCase());
        return "Initial Board";
    }


}
