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
        return this.sumOfManhattanDistances() + 2*this.totalConflicts();
    }
    private int totalConflicts() {
        Board board = this.state.getBoard();
        int sum = 0;
        for (int row = 0; row < board.getTiles().length; row++) {
            for (int column = 0; column < board.getTiles()[0].length; column++) {

            }
        }
    }
    private static int totalConflictsInLine(Tile[] line) {
        return 0;
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
