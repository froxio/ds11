import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The primary intent of this class is organization and avoiding the creation of a moves list
 * every time one is needed.
 * @author Phillip Langley
 * @version 2014-11-23
 */
public class Moves
{
    //List of moves
    private ArrayList<String[]> moves;
    
    /**
     * Argless and only constructor. Reads from a file containing moves, line-separated triplets,
     * in the order of peg to be moved, peg to jump, and hole to end up in.
     * @throws IOException (I/O exception)
     */
    public Moves() throws IOException
    {
        moves = new ArrayList<String[]>();
        @SuppressWarnings("resource")
        BufferedReader in = new BufferedReader(new FileReader("moves.dat"));
        //There are 36 moves total.
        for (int i = 0; i < 36; i++)
        {
            moves.add(in.readLine().split("\\s+"));
        }
    }
    
    /**
     * Determines if a move can be made based on location.
     * @param move to be made.
     * @return true if move can be made.
     */
    public boolean isValidMove(String[] move)
    {
        return moves.contains(move);
    }
    
    /**
     * Returns the moves list.
     * @return the list of possible moves.
     */
    public ArrayList<String[]> getMoves()
    {
        return moves;
    }
}
