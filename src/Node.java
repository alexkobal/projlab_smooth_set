/**
 * class Node
 * <p>
 *     Stores coordinates of graph nodes
 * </p>
 */
public class Node
{
    /**
     * x
     * <p>
     *     X coordinate
     * </p>
     */
    private int x;
    /**
     * y
     * <p>
     *     Y coordinate
     * </p>
     */
    private int y;

    /**
     * Node
     * <p>
     *     Constructor
     * </p>
     * @param _x x coordinate
     * @param _y y coordinate
     */
    public Node(int _x, int _y)
    {
        x = _x;
        y = _y;
    }

    /**
     * getX
     * <p>
     *     Gets the x coordinate
     * </p>
     * @return x coordinate
     */
    public int getX()
    {
        return x;
    }

    /**
     * getY
     * <p>
     *     Gets the y coordinate
     * </p>
     * @return y coordinate
     */
    public int getY()
    {
        return y;
    }
}
