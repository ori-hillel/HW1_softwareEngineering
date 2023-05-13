public class Node {
    private State currentState;
    Node root;
    Action prevAction;

    public State getCurrentState() {
        return this.currentState;
    }
    public void setCurrentState(State s) {
        this.currentState = s;
    }

}
