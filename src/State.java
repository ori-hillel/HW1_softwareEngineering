import java.util.concurrent.RecursiveTask;

public class State {
    private static final Enum  UP= new Enum("UP");
    private static final Enum DOWN = new Enum("DOWN")
    private static final Enum LEFT = new Enum("LEFT");
    private static final Enum RIGHT = new Enum("RIGHT");

    private static final int I_UP= 0;
    private static final int I_DOWN = 1;
    private static final int I_RIGHT= 2;
    private static final int I_LEFT =3;

    private static final int EMPTY = 0;
    private Board board;

    public State(Board board) {
        this.board = board;
    }
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
        State ret = new State(this.board.duplicate());
        int rowIndex = 0, colIndex = 0;
        for (int i = 0; i < ret.board.getTiles().length; i++)
            for (int j = 0; j < ret.board.getTiles().length; j++) {
                if (ret.board.getTiles()[i][j].getValue() == EMPTY) {
                    colIndex = j;
                    rowIndex = i;
                    break;
                }
            }
        if (action.getDirection().equals(UP)) {
            Board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex + 1][colIndex]);
        }
        else if (action.getDirection().equals(DOWN)) {
            Board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex - 1][colIndex]);
        }
        else if (action.getDirection().equals(LEFT)) {
            Board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex][colIndex + 1]);
        }
        else if (action.getDirection().equals(RIGHT)) {
            Board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex][colIndex - 1]);
        }
        return ret;
    }
   public Action[] actions() {
       Action[] array = new Action[4];
       int colNum = this.board.getTiles()[0].length;
       int rowNum = this.board.getTiles().length;
       int rowIndex = 0, colIndex = 0;
       for (int i = 0; i < this.board.getTiles().length; i++)
           for (int j = 0; j < this.board.getTiles()[0].length; j++) {
               if (this.board.getTiles()[i][j].getValue() == EMPTY) {
                   colIndex = j;
                   rowIndex = i;
                   break;
               }
           }
       if (rowIndex + 1 < rowNum && colIndex < colNum)
           array[I_UP] = new Action(this.board.getTiles()[rowIndex + 1][colIndex], UP);
       if (rowIndex - 1 < rowNum && colIndex < colNum)
           array[I_DOWN] = new Action(this.board.getTiles()[rowIndex - 1][colIndex], DOWN);
       if (rowIndex < rowNum && colIndex + 1 < colNum)
           array[I_RIGHT] = new Action(this.board.getTiles()[rowIndex][colIndex + 1], RIGHT);
       if (rowIndex < rowNum && colIndex - 1 < colNum)
           array[I_LEFT] = new Action(this.board.getTiles()[rowIndex][colIndex - 1], LEFT);
       Action[] ret = new Action[countNulls(array)];
       for (int retIndex = 0, auxIndex = 0; retIndex < ret.length; retIndex++, auxIndex++) {
           if (array[auxIndex] == null) {
               auxIndex++;
               continue;
           }
           ret[retIndex] = array[auxIndex];
       }
       return ret;
   }
   private static int countNulls(Action[] array) {
        int nullCounter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) nullCounter++;
        }
        return (array.length - nullCounter);
   }
}
