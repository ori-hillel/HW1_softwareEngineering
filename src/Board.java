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

    public void swap(Tile tile1, Tile tile2) {
        int row1 = -1;
        int col1 = -1;
        int row2 = -1;
        int col2 = -1;

        // Find the indices of obj1 and obj2 in the 2D array
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.getTiles()[i][j] == tile1) {
                    row1 = i;
                    col1 = j;
                } else if (this.tiles[i][j] == tile2) {
                    row2 = i;
                    col2 = j;
                }
                // Break early if both indices are found
                if (row1 != -1 && col1 != -1 && row2 != -1 && col2 != -1) {
                    break;
                }
            }

            // Break early if both indices are found
            if (row1 != -1 && col1 != -1 && row2 != -1 && col2 != -1) {
                break;
            }
        }
        // Swap the elements if both indices are found
        if (row1 != -1 && col1 != -1 && row2 != -1 && col2 != -1) {
            Tile temp = this.tiles[row1][col1];
            this.tiles[row1][col1] = this.tiles[row2][col2];
            this.tiles[row2][col2] = temp;
        }
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
