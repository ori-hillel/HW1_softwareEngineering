public class Node {
    private State state;
    private Node root;
    private Action action;

    public Node () {

    }
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
        Action actions[] = this.state.actions();
        Node[] ret = new Node[actions.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i].root = this;
            ret[i].action = actions[i];
            ret[i].state = this.state.result(actions[i]);
        }
        return ret;
    }
}
