public class Action {
    private Tile tile;
    private Enum direction;

    public Action(Tile tile, Enum direction) {
        this.tile = tile;
        this.direction = direction;
    }
    public Enum getDirection() {
        return direction;
    }
    public void setDirection(Enum otherDirection) {
        this.direction = otherDirection;
    }
    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile otherTile) {
        this.tile = otherTile;
    }
    public String toString() {
        return String.format("Move %d %s", tile.getValue(), direction.getDirection().toLowerCase());
    }
}