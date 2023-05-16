public class State {
    private static final Enum UP = new Enum("UP");
    private static final Enum DOWN=new Enum("DOWN")
    private static final Enum LEFT= new Enum("LEFT");
    private static final Enum RIGHT=new Enum("RIGHT");
    private static final int EMPTY=0;
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
    public State result (Action action) {
        State res = new State()
    int rowIndex=0,colIndex=0;
    for(int i = 0;i<this.board.getTiles().length;i++)
        for(int j = 0;j<this.board.getTiles().length;j++)
        {
            if(this.board.getTiles()[i][j].getValue() == EMPTY)
            {
                colIndex = j;
                rowIndex = i;
                break;
            }

        }
    if(action.getDirection().equals(UP)) {
        Board.swap(this.board.getTiles()[rowIndex][colIndex],this.board.getTiles()[rowIndex+1][colIndex]);
    }
    if(action.getDirection().equals(DOWN)) {
        Board.swap(this.board.getTiles()[rowIndex+1][colIndex],this.board.getTiles()[rowIndex-1][colIndex]);
    }
    if(action.getDirection().equals(LEFT)) {
        Board.swap(this.board.getTiles()[rowIndex][colIndex],this.board.getTiles()[rowIndex][colIndex+1]);
    }
    if(action.getDirection().equals(RIGHT)) {
        Board.swap(this.board.getTiles()[rowIndex][colIndex],this.board.getTiles()[rowIndex][colIndex-1]);
    }



    }


}