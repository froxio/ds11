/**
 * Single node in a PegState structure (obselete and untested). This class is here 
 * if I ever decide to implement a node structure.  
 * @author Phillip Langley
 * @version 2014-11-23
 */
public class PegNode
{
    //List of neighboring nodes.
    private PegNode[] neighbors;
    @SuppressWarnings("unused")
    private boolean fill;
    
    /**
     * Initializes if this node has a peg and doubly-linked neighbor relationships.
     * @param fill this hole.
     * @param neighbors of this node.
     */
    public PegNode(boolean fill, PegNode... neighbors)
    {
        this.fill = fill;
        this.neighbors = new PegNode[neighbors.length];
        for (int i = 0; i < neighbors.length; i++)
        {
            this.neighbors[i] = neighbors[i];
        }
        for (int i = 0; i < neighbors.length; i++)
        {
            if (!neighbors[i].isNeighbor(this))
            {
                neighbors[i].addNeighbor(this);
            }
        }
    }
    
    /**
     * Adds a neighbor to this node (and a neighbor, this node, to that neighbor).
     * @param p PegNode to be made a neighbor.
     */
    public void addNeighbor(PegNode p)
    {
        if (neighbors.length > 5)
        {
            throw new UnsupportedOperationException();
        }
        PegNode[] temp = new PegNode[neighbors.length + 1];
        for (int i = 0; i < neighbors.length; i++)
        {
            temp[i] = neighbors[i];
        }
        temp[neighbors.length] = p;
        neighbors = temp;
    }
    
    /**
     * Returns a list of neighbors for this.
     * @return this list of neighbors.
     */
    public PegNode[] getNeighbors()
    {
        return neighbors;
    }
    
    /**
     * Removes a peg from this.
     */
    public void removePeg()
    {
        fill = false;
    }
    
    /**
     * Places a peg in this.    
     */
    public void fillPeg()
    {
        fill = true;
    }
    
    /**
     * Determines if a node is a neighbor of this.    
     * @param p node to be tested if neighbor.
     * @return true if p is a neighbor of this (though not if this is a neighbor of p).
     */
    public boolean isNeighbor(PegNode p)
    {
        for (int i = 0; i < neighbors.length; i++)
        {
            if (p == neighbors[i])
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if this and p are symetrically across a single node
     * (do not touch and share a single node).
     * @param p node to be tested if across.
     * @return if this and p are across from one another.
     */
    public boolean isAcross(PegNode p)
    {
        //number of nodes shared by p and this.
        int count = 0;
        for (int i = 0; i < neighbors.length; i++)
        {
            for (int j = 0; j < p.getNeighbors().length; j++)
            {
                if (neighbors[i] == p.getNeighbors()[j])
                {
                    count++;
                }
            }
        }
        //if sharing more than one node or if p and this share any kind of neighbor relationship.
        if (count != 1 || isNeighbor(p) || p.isNeighbor(this))
        {
            return false;
        }
        return true;
    }
}
