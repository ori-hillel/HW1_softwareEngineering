import java.util.Arrays;

public class Board {
private Tile[][] tiles;
    public Board (String tileString) {
        String[] rows = tileString.split("|");
        int rowsNum = rows.length;
        int colNum = rows[0].split(" ").length;
        tiles = new Tile[rowsNum][colNum];
        String[] cells; // auxiliary
        String cell;
        for (int x = 0; x < rowsNum; x++) {
            String row = rows[x];
            cells = row.split(" ");
            for (int y = 0; y < colNum; y++) {
                cell = cells[y];
                if (cell.equals("_")) {
                    tiles[x][y] = new Tile(0);//Empty cell ("_") will be replaced by ( 0 )
                } else {
                    int value = Integer.parseInt(cell);
                    tiles[x][y] = new Tile(value);
                }
            }
        }
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
