import java.io.IOException;

/**
 * Driver for peg gamem.
 * @author Phillip Langley
 * @version 2014-11-14
 */
public class PegGameDriver
{
    /**
     * Runs tests against PegGame. This tests if an initial peg was removed from any position
     * (despite there only being four unique starting positions).
     * @param args for command line.
     * @throws IOException (I/O exception).
     */
    public static void main(String[] args) throws IOException
    {
        for (int i = 0; i < 15; i++)
        {
            PegGame game = new PegGame(i);
            game.findSolution();
        }
    }
}
