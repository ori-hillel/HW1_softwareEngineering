public class Node {
    private State state;
    Node root;
    Action action;

    public State getState() {
        return this.state;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public void setCurrentState(State s) {
        this.state = s;
    }

    public Node[] expand() {
        Node[] res = new Node(this.state.actions().length);
        for (int i = 0; i < res.length; i++) {
            res[i].setRoot(this);
            res.
        }
    }
}
