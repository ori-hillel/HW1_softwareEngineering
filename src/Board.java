import java.util.Arrays;

public class Board {
private Tile[][] tiles;
    public Board (String tileString) {
        String[] rows = tileString.split("\\|");
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
    public Board (Tile[][] tiles) {
        this.tiles = tiles;
    }
    public Board duplicate() {
        Board ret = new Board(new Tile[this.tiles.length][this.tiles[0].length]);
        for (int row = 0; row < this.tiles.length; row++)
            for (int col = 0; col < this.tiles[0].length; col++)
                ret.tiles[row][col] = new Tile(this.tiles[row][col].getValue());
        return ret;
    }
    public Board goalBoard() {
        int rows = this.tiles.length;
        int cols = this.tiles[0].length;
        Tile[][] goalBoard = new Tile[rows][cols];
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == rows - 1 && j == cols - 1)
                    goalBoard[i][j] = new Tile(0);
                else {
                    counter++;
                    goalBoard[i][j] = new Tile(counter);
                }
            }
        }
        return new Board(goalBoard);
    }
    public Tile[][] getTiles() {
        return this.tiles;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    public static void swap(Tile tile1, Tile tile2) {
        Tile temp = tile1;
        tile1 = tile2;
        tile2 = tile1;
    }
    public int getRow(Tile tile) {
        for (int row = 0; row < this.tiles.length; row++) {
            for (int col = 0; col < this.tiles[0].length; col++) {
                if (this.tiles[row][col].equals(tile))
                    return row;
            }
        }
        return this.tiles.length - 1; // will never get to this line.
    }
    public int getColumn(Tile tile) {
        for (int row = 0; row < this.tiles.length; row++) {
            for (int col = 0; col < this.tiles[0].length; col++) {
                if (this.tiles[row][col].equals(tile))
                    return col;
            }
        }
        return this.tiles[0].length - 1; // will never get to this line.
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

}
