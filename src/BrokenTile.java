import java.awt.*;

/**
 * BrokenTile
 * 
 * <p>Breakable type of Tile. 
 * <p> 'lifeTime' holds the remaining number of steps the tile can bear until breaking. (default 20)
 */
public class BrokenTile extends Tile
{
    /**
     * lifeTime
     * <p>
     *     Life time counter of a broken tile
     *     Is always 20 at the start
     * </p>
     */
    private int lifeTime;

    /**
     * BrokenTile
     * <p>
     *     Default constructor
     *     Sets the lifetime to default
     * </p>
     */
    public BrokenTile()
    {
        lifeTime = 20;
    }

    /**
     * BrokenTile contstructor
     * <p>
     *     This is the constructor of the BrokenTile class.
     * </p>
     * @param n Is the value that is going to be set as life.
     */
    public BrokenTile(int n) {lifeTime = n;} // A teszt idejére csak

    
    /**
     * loseLife()
     * <p> Decreases the 'lifeTime' by one. 
     * If it reaches 0 then the function unlinks the tile and kills its 'contains'</p>  
     * 
     */    
    @Override
    public void loseLife()
    {
        lifeTime--;
        if(lifeTime == 0)
        {
            unlink();

            ((Animal) contains).kill(Controller.getInstance());
        }
    }

    /**
     * toString
     * <p>
     *     Overrides the Object.toString() function
     * </p>
     * @return "b" string
     */
    @Override
    public String toString(){
        if(lifeTime > 0) return "b" + lifeTime;
        else return "0";
    }

    /**
     * IDrawable interface implementation
     * @param g graphics object of the window
     */
    @Override
    public void invokeDraw(Graphics g) {
        View.getInstance().draw(this, g);
    }
}
