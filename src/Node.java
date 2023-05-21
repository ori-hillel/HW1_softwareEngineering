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
    public void setState(State s) {
        this.state = s;
    }

    public Node[] expand() {
        Action actions[] = this.state.actions();
        Node[] ret = new Node[actions.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i].root = this;
            ret[i].action = actions[i];
            ret[i].state = this.state.result(actions[i]);
        }
        return ret;
    }

    public int heuristicValue() {
        // sum of manhattan distances from goal board
        int sum = 0;
        Board goalBoard = this.state.getBoard().goalBoard();
        int currentValue=0, indexRow=0, indexCol=0, indexGoalRow=0, indexGoalCol=0;
        Tile currentTile;
        for (int row = 0; row < this.state.getBoard().getTiles().length; row++) {
            for (int col = 0; col < this.state.getBoard().getTiles()[0].length; col++) {
                currentValue = col + row*this.state.getBoard().getTiles().length;
                currentTile = new Tile(currentValue);
                indexRow = this.state.getBoard().getRow(currentTile);
                indexCol = this.state.getBoard().getColumn(currentTile);
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
