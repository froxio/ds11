import java.io.IOException;
import java.util.HashMap;

/**
 * Peg solitare game algorithm.
 * @author Phillip Langley
 * @version 2014-11-23
 */
@SuppressWarnings("rawtypes")
public class PegGame extends AbstractGame
{
    //Solution map.
    private HashMap<Integer, PegState> solution;   
    //First peg state used mainly to print the final answer.
    private PegState init;
    //Single instance of moves implemented in over PegState for resource efficency.
    private Moves moves;
    //State that acts as a param for current state required methods that accept no parameters.
    private PegState current;
    
    
    /**
     * Argless constructor that removes the peg at index 0 (the top).
     * @throws IOException (I/O exception).
     */
    public PegGame() throws IOException
    {
        super(15, 1);
        solution = new HashMap<Integer, PegState>();
        moves = new Moves();
        current = (PegState) makeInitialState();
    }
    
    /**
     * Arg constructor for starting a game with a specific peg missing.
     * @param start peg to be removed initially.
     * @throws IOException (I/O exception).
     */
    public PegGame(int start) throws IOException
    {
        this();
        init = new PegState(start);
    }
    
    /**
     * Determines if a move is feasible based on the location of the holes relative to one 
     * another and how the holes are filled.
     * @param move to be made.
     * @param state of the board currently.
     * @return if a move is valid.
     */
    public boolean isValidMove(String[] move, PegState state)
    {
        return moves.isValidMove(move) && state.isValidMove(move);
    }
    
    /**
     * Gives a purpose to addChildren.
     */
    public void findSolution()
    {
        addChildren();
    }
    
    /**
     * Recursively find the solution to a peg game.
     * @param state of peg game for which to find the solution.
     */
    private void findSolution(PegState state)
    {
        //To bypass required, paramless methods
        current = state;
        //If there's a winner print it. Granted it's called in a global context it will print
        //the solution ~13 times.
        if (goalState())
        {
            printWinInstructions(init);
        }
        //If this state hasn't been checked yet..
        if (!solution.containsKey(state.hashCode()))
        {
            for (String[] move : moves.getMoves())
            {
                //Test every move on this state.
                if (isValidMove(move, state))
                {
                    //Put the valid move (or overwrite an non-winning move) as a solution step.
                    solution.put(state.hashCode(), PegState.makeMove(move, state));
                    //Recurse down.
                    findSolution(PegState.makeMove(move, state));
                }
            }
        }
    }
    
    /**
     * Actual method that prints winning instructions. This method recursively calls from
     * init down to the winning state a print statement per toString per state.
     * @param state that has its toString printed.
     */
    public void printWinInstructions(PegState state)
    {
        prettyPicture(state);
        if (solution.containsKey(state.hashCode()))
        {
            printWinInstructions(solution.get(state.hashCode()));            
        }
    }
    /**
     * Calls the recursive solution method starting at the initial node.
     */
    @Override
    public void addChildren()
    {
        findSolution(init);        
    }
    /**
     * Calls a method that prints a visual of the board state.
     */
    @Override
    public void prettyPicture(State st)
    {
        System.out.println(st.toString());        
    }
    
    /**
     * Calls a method that describes if the global state param is a winning state.
     * @return true if global state is winning.
     */
    @Override
    public boolean goalState()
    {
        return current.isWinningState();
    }
    
    /**
     * Sets the initial state and current state the first time.
     * @return what current is at first.
     */
    @Override
    public State makeInitialState()
    {
        init = new PegState();
        return init;
    }
}
