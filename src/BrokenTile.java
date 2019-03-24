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
            Main.printer.functionCall("this", "unlink");		//selfstimulus
            unlink();
            Main.printer.returnFromFunctionCall();

            Main.printer.functionCall("contains", "kill");
            ((Animal) contains).kill();
            Main.printer.returnFromFunctionCall();
        }
    }
}
