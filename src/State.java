import java.util.concurrent.RecursiveTask;

public class State {
    private static final Enum  UP= new Enum("UP");
    private static final Enum DOWN = new Enum("DOWN");
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
    public Board getBoard() {
        return this.board;
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
        int rowIndex = ret.board.getRow(new Tile(EMPTY));
        int colIndex = ret.board.getColumn(new Tile(EMPTY));
        if (action.getDirection().equals(UP)) {
            ret.board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex + 1][colIndex]);
        }
        else if (action.getDirection().equals(DOWN)) {
            ret.board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex - 1][colIndex]);
        }
        else if (action.getDirection().equals(LEFT)) {
            ret.board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex][colIndex + 1]);
        }
        else if (action.getDirection().equals(RIGHT)) {
            ret.board.swap(ret.board.getTiles()[rowIndex][colIndex], ret.board.getTiles()[rowIndex][colIndex - 1]);
        }
        return ret;
    }
   public Action[] actions() {
       Action[] array = new Action[4];
       int colNum = this.board.getTiles()[0].length;
       int rowNum = this.board.getTiles().length;
       int rowIndex = this.board.getRow(new Tile(EMPTY));
       int colIndex = this.board.getColumn(new Tile(EMPTY));
       if (rowIndex + 1 < rowNum)
           array[I_UP] = new Action(this.board.getTiles()[rowIndex + 1][colIndex], UP);
       if (rowIndex - 1 >= 0)
           array[I_DOWN] = new Action(this.board.getTiles()[rowIndex - 1][colIndex], DOWN);
       if (colIndex - 1 >= 0)
           array[I_RIGHT] = new Action(this.board.getTiles()[rowIndex][colIndex - 1], RIGHT);
       if (colIndex + 1 < colNum)
           array[I_LEFT] = new Action(this.board.getTiles()[rowIndex][colIndex + 1], LEFT);
       Action[] ret = new Action[countNulls(array)];
       for (int retIndex = 0, auxIndex = 0; auxIndex < array.length; auxIndex++) {
           if (array[auxIndex] instanceof Action) {
               ret[retIndex] = array[auxIndex];
               retIndex++;
           }
       }
       return ret;
   }
   private static int countNulls(Action[] array) {
        int nullCounter = 0;
       for (Action action : array) {
           if (action == null) nullCounter++;
       }
        return (array.length - nullCounter);
   }
}
