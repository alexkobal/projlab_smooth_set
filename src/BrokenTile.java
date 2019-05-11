/**
 * BrokenTile
 * 
 * <p>Breakable type of Tile. 
 * <p> 'lifeTime' holds the remaining number of steps the tile can bear until breaking. (default 20)
 */
public class BrokenTile extends Tile
{
    private int lifeTime;

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

            ((Animal) contains).kill(ctrl);
        }
    }

    @Override
    public String toString(){
        if(lifeTime > 0) return "b" + lifeTime;
        else return "0";
    }

    @Override
    public void invokeDraw() {
        View.getInstance().draw(this);
    }
}
