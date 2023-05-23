public class Node {
    private State state;
    private Node root;
    private Action action;

    public Node (State state, Action action, Node root) {
        this.state = state;
        this.root = root;
        this.action = action;
    }
    public Node () {}
    public State getState() {
        return this.state;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public Node getParent() {
        return this.root;
    }
    public void setState(State s) {
        this.state = s;
    }
    public Action getAction() {
        return this.action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    public Node[] expand() {
        Action actions[] = this.state.actions();
        Node[] ret = new Node[actions.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new Node();
            ret[i].root = this;
            ret[i].action = actions[i];
            ret[i].state = this.state.result(actions[i]);
        }
        return ret;
    }

    public int heuristicValue() {
        return misplacesTiles();
    }
    private int linearConflicts() {
        int goalValue = 0, goalRow = 0, goalColumn = 0, currentValue = 0;
        Tile[][] trans = transpose();
        Tile[][] tiles = this.state.getBoard().getTiles();
        int numRows = this.state.getBoard().getTiles().length;
        int numCols = this.state.getBoard().getTiles()[0].length;
        int linearConflicts = 0;
        for (int row = 0; row < numRows; row++) {
            linearConflicts += totalConflictsInLine(tiles[row]); // conflicts in a certain row
            for (int column = 0; column < numCols; column++) {
                if (row == 0)
                    linearConflicts += totalConflictsInLine(trans[column]); // conflicts in a certain column
                currentValue = tiles[row][column].getValue();
                goalRow = calcRowInGoalBoard(currentValue);
                goalColumn = calcColumnInGoalBoard(currentValue);
                linearConflicts += calcManhattanDistance(row, column, goalRow, goalColumn); // adds Manhattan distance of a tile from its goal position.
            }
        }
        return linearConflicts;
    }
    private int totalConflicts() {
        int totalConflicts = 0;
        Tile[][] tiles = this.state.getBoard().getTiles();
        for (int row = 0; row < tiles.length; row++)
            totalConflicts += totalConflictsInLine(tiles[row]);
        for (int column = 0; column < transpose().length; column++)
            totalConflicts += totalConflictsInLine(transpose()[column]);
        return totalConflicts;
        }
    private Tile[][] transpose() {
        Tile[][] thisTiles = this.state.getBoard().getTiles();
        int numRows = this.state.getBoard().getTiles().length;
        int numCols = this.state.getBoard().getTiles()[0].length;
        Tile[][] trans = new Tile[numCols][numRows];
        for (int row = 0; row < numRows; row++)
            for (int column = 0; column < numCols; column++)
                trans[column][row] = new Tile(thisTiles[row][column].getValue());
        return trans;
    }
        private int totalConflictsInLine(Tile[] line) {
        int conflicts = 0;
        int val1, val2;
        for (int first = 0; first < line.length - 1; first++)
            for (int second = first; second < line.length; second++) {
                val1 = line[first].getValue();
                val2 = line[second].getValue();
                if ((line[first].getValue() > line[second].getValue())
                        && (calcRowInGoalBoard(val1) == calcRowInGoalBoard(val2) || calcColumnInGoalBoard(val1) == calcColumnInGoalBoard(val2)))
                    conflicts++;
            }
        return conflicts;
    }
    private int calcRowInGoalBoard (int value) {
        if (value != 0)
            return ((value - 1) / this.getState().getBoard().getTiles()[0].length);
        return this.state.getBoard().getTiles().length - 1;
    }
    private int calcColumnInGoalBoard (int value) {
        if (value != 0)
            return value - (calcRowInGoalBoard(value)*this.state.getBoard().getTiles()[0].length);
        return this.state.getBoard().getTiles()[0].length;
    }
    private int sumOfManhattanDistances () {
        int sum = 0;
        Board board = this.state.getBoard();
        Board goalBoard = board.goalBoard();
        int currentValue=0, indexRow=0, indexCol=0, indexGoalRow=0, indexGoalCol=0;
        Tile currentTile;
        for (int row = 0; row < board.getTiles().length; row++) {
            for (int col = 0; col < board.getTiles()[0].length; col++) {
                currentValue = col + row * board.getTiles().length;
                currentTile = new Tile(currentValue);
                indexRow = board.getRow(currentTile);
                indexCol = board.getColumn(currentTile);
                indexGoalRow = goalBoard.getRow(currentTile);
                indexGoalCol = goalBoard.getColumn(currentTile);
                sum += calcManhattanDistance(indexRow, indexCol, indexGoalRow, indexGoalCol);
            }
        }
        return sum;
    }
    private int misplacesTiles() {
        Board goalBoard = this.state.getBoard().goalBoard();
        Board thisBoard = this.state.getBoard();
        int counter = 0;
        for (int row = 0; row < thisBoard.getTiles().length; row++)
            for (int column = 0; column < thisBoard.getTiles()[0].length; column++) {
                if (!thisBoard.getTiles()[row][column].equals(goalBoard.getTiles()[row][column]))
                    counter++;
            }
        return counter;
    }
    private static int calcManhattanDistance(int x1, int y1, int x2, int y2) {
        int distance = absoluteDifference(x1, x2) + absoluteDifference(y1, y2);
        return distance;
    }

    private static int absoluteDifference(int a, int b) {
        if (a > b) {
            return a - b;
        } else {
            return b - a;
        }
    }
}
