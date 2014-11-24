/**
 * A peg solitare game board state.
 * @author Phillip Langley
 * @version 2014-11-14
 */
public class PegState implements State
{
    //array of pegs. True if peg is removed otherwise false.
    private boolean[] board;
    
    /**
     * Default constructor. Initializes the topmost peg of index 0 to removed.
     */
    public PegState()
    {
        board = new boolean[15];
        board[0] = false;
        for (int i = 1; i < 15; i++)
        {
            board[i] = true;
        }
    }
    
    /**
     * Initializes a board with a certain peg removed.
     * @param start peg 0 - 14 to be removed
     */
    public PegState(int start)
    {
        this();
        board[0] = true;
        board[start] = false;
    }
    
    /**
     * Returns the game state board array.
     * @return game board.
     */
    public boolean[] getBoard()
    {
        return board;
    }
    
    public void setBoard(boolean[] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            this.board[i] = board[i];
        }
    }
    
    /**
     * Sets a peg on the board.
     * @param index of peg to be set.
     */
    private void fillPeg(int index)
    {
        getBoard()[index] = true;
    }
    
    /**
     * Removes a peg on the board.
     * @param index of peg to be removed.
     */
    private void removePeg(int index)
    {
        getBoard()[index] = false;
    } 
    
    /**
     * Determines if a move can be made based on the presence of pegs in the correct locations (110).
     * @param move to be tested for validity.
     * @return true if a move is valid.
     */
    public boolean isValidMove(String[] move)
    {
        if (getBoard()[Integer.parseInt(move[0])]
                && getBoard()[Integer.parseInt(move[1])]
                && !getBoard()[Integer.parseInt(move[2])])
        {
            return true;
        }
        return false;
    }
    
    /**
     * Counts number of pegs left on the board.    
     * @return number of pegs left on the board.
     */
    public int count()
    {
        int count = 0;
        for (int i = 0; i < 15; i++)
        {
            if (board[i])
            {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Determines if this is a winning state.
     * @return true if this is a winning state.
     */
    public boolean isWinningState()
    {
        return count() == 1;
    }
    
    /**
     * Prints the toString for this.
     */
    public void print()
    {
        System.out.println(toString());
    }
    
    /**
     * Generates a hashCode based on this toString
     * @return unique hash code.
     */
    public int hashCode()
    {
        return toString().hashCode();
    }
    
    /**
     * Compares toStrings of this and peg
     * @param peg is the PegState to compare to this.
     * @return false if toStrings do not match
     */
    public boolean equals(PegState peg)
    {
        return toString().equals(peg.toString());
    }
    
    /**
     * Generates a visual for the board at this state. Zeros are empty. Ones are filled (with a peg)
     * @return the String representation of this board.
     */
    public String toString()
    {
        return "    " + (board[0] ? 1 : 0) + "\n"
                + "   " + (board[1] ? 1 : 0) + " " + (board[2] ? 1 : 0) + "\n"
                + "  " + (board[3] ? 1 : 0) + " " + (board[4] ? 1 : 0) + " " + (board[5] ? 1 : 0) + "\n"
                + " " + (board[6] ? 1 : 0) + " " + (board[7] ? 1 : 0) + " " + (board[8] ? 1 : 0) + " " + (board[9] ? 1 : 0) + "\n"
                + (board[10] ? 1 : 0) + " " + (board[11] ? 1 : 0) + " " + (board[12] ? 1 : 0) + " " + (board[13] ? 1 : 0) + " " + (board[14] ? 1 : 0) + "\n";
    }
    
    /**
     * Makes a move on a board. This is static because it makes code more minimal higher up.
     * @param move to be made on a state.
     * @param state to be moved on.
     * @return the state after a move has been made on it.
     */
    public static PegState makeMove(String[] move, PegState state)
    {
        PegState movedOn = (PegState) state.clone();
        movedOn.removePeg(Integer.parseInt(move[0]));
        movedOn.removePeg(Integer.parseInt(move[1]));
        movedOn.fillPeg(Integer.parseInt(move[2]));
        return movedOn;
    }
    
    /**
     * Sets passed state to this.
     * @param anotherState that is set to this.
     */
    @Override
    public void copy(State anotherState)
    {
        anotherState = clone();        
    }
    
    /**
     * Makes a clone of this.
     * @return a clone of this.
     */
    @Override
    public State clone()
    {
        PegState state = new PegState();
        state.setBoard(getBoard());
        return state;
    }
}
