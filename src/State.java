public class State {
    private static final Enum UP="UP"
    private static final Enum DOWN="DOWN"
    private static final Enum LEFT="LEFT"
    private static final Enum RIGHT="RIGHT"
private Board board;
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
    public boolean isGoal() {
        return this.board.equals(this.board.goalBoard());
    }
    public State result(Action action) {
        State res = new State()
    for(int i = 0;i<this.board.getTiles.length;i++)
        for(int j = 0;j<this.board.getTiles.length;j++)
        {
            if(this.board.getTiles == 0)

        }
    if(action.getDirection().equals(UP)) {

    }
    if(action.getDirection().equals(DOWN)) {

    }
    if(action.getDirection().equals(LEFT)) {

    }
    if(action.getDirection().equals(RIGHT)) {

    }



    }


}