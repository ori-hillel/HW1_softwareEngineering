public class Node {
    /**
     * represents a node, which is substantial for the Greedy First BEst search.
     * Each node consists of the node's state, the previous action, and the root node.
     */
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

    /**
     * expands a node ti its children, according to the possible moves that are available from the current node's state.
     * @return a 1D array of nodes, which represents the node's children.
     */
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

    /**
     * calculates the node's heuristic value.
     * The chosen heuristic function is the misplaced tiles heuristic function.
     * @return an integer, which is the result of the heuristic function for this node.
     */
    public int heuristicValue() {
        return misplacesTiles();
    }

    /**
     * calculates how many misplaced tiles are within the node's board.
     * @return an integer, which is the number of misplaced tiles.
     */
    private int misplacesTiles() {
        Board goalBoard = this.state.getBoard().goalBoard();
        Board thisBoard = this.state.getBoard();
        int counter = 0;
        for (int row = 0; row < thisBoard.getTiles().length; row++)
            for (int column = 0; column < thisBoard.getTiles()[0].length; column++) {
                if (!thisBoard.getTiles()[row][column].equals(goalBoard.getTiles()[row][column])) // if the tile thisBoard[row][column] is misplaced
                    counter++;
            }
        return counter;
    }
}
