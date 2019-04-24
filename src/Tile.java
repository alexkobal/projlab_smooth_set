import java.io.Serializable;
import java.util.*;

/**
 * Tile superclass.
 * 
 * <p>The in-game objects are located on a map paved with tiles.
 * Tiles are either breakable or non-breakable. 
 * This class collects all the common behaviour of these two types and has compatibility purposes.</p>  
 * 
 * <p> Field 'contains' is the in-game object currently standing on the given tile.
 * List of 'neighbors' stores all the adjacent tiles.</p>  
 * 
 */
public abstract class Tile implements Serializable
{
    static public Controller ctrl = null;

    /**
     * contains
     * <p>
     * This can hold the thing, which is on this Tile at the time.
     */
    protected AThing contains;

    /**
     * neighbors
     * <p>
     * This is this Tile's list of the neighbors.
     * The tiles nearby this tile.
     */
    protected ArrayList<Tile> neighbors;

    /**
     * Tile contstructor
     * <p>
     *     This is the constructor of the Tile class.
     * </p>
     * Inizializes the lists, and variables.
     */
    public Tile()
    {
        contains = null;
        neighbors = new ArrayList<>();
    }

    public void setCtrl(Controller c) {ctrl = c;}
    public Controller getCtrl() {return ctrl;}

    /**
     * placeThing(panda: Panda)
     * <p>Operation that checks whether our tile is empty and if so then it steps the panda there. 
     * Otherwise it asks the non-null 'contains' if it allows a panda to step on it by calling the proper hitBy() function.</p>  
     * 
     * @param panda The panda who wants to step on the tile
     * @return A boolean which indicates the success of a step. (true = successful, false = panda cannot step here)
     */
    public boolean placeThing(Panda panda)
    {
        if(contains != null)
        {
        	boolean res = contains.hitBy(panda);
            return res;
        } 
        
    	panda.leaveTile(this);
    	return true;
    }
    
    /**
     * placeThing(orangutan: Orangutan)
     * <p>Operation that checks whether our tile is empty and if so then it steps the orangutan there. 
     * Otherwise it asks the non-null 'contains' if it allows an orangutan to step on it by calling the proper hitBy() function.</p>  
     * 
     * @param orangutan The orangutan who wants to step on the tile
     * @return A boolean which indicates the success of a step. (true = successful, false = orangutan cannot step here)
     */
    public boolean placeThing(Orangutan orangutan)
    {
    	if(contains != null)
        {
        	boolean res = contains.hitBy(orangutan);
            return res;
        } 
        
    	orangutan.leaveTile(this);
    	return true;
    }
    
    /**
     * placeThing(vm: VendingMachine)
     * <p>The operation checks whether our tile is empty. If it's not then the method forwards the notification to 'contains' by calling its hitBy(vm)
     * who will or will not react to that. Only JumpingPanda will react, that's the only scenario we return true. </p>      * 
     * 
     * @param vm The vending machine that wants to notify this tile's 'contains'.
     * @return Returns the success of the notifying operation. (true: JumpingPanda was standing on this tile, false otherwise)
     */
    public boolean placeThing(VendingMachine vm)
    {
    	if(contains != null)
        {
        	boolean res = contains.hitBy(vm);
            return res;
        }

        return false;
    }
    
    /**
     * placeThing(gm: GameMachine)
     * <p>The operation checks whether our tile is empty. If it's not then the method forwards the notification to 'contains' by calling its hitBy(gm)
     * who will or will not react to that. Only ScaredPanda will react, that's the only scenario we return true.</p>     
     * 
     * @param gm The game machine that wants to notify this tile's 'contains'.
     * @return Returns the success of the notifying operation. (true: ScaredPanda was standing on this tile, false otherwise)
     */
    public boolean placeThing(GameMachine gm)
    {
        if(contains != null)
        {
        	boolean res = contains.hitBy(gm);
            return res;
        }
        
        return false;
    }
    
    /**
     * placeThing(armchair: Armchair)
     * <p>The operation checks whether our tile is empty. If it's not then the method forwards the notification to 'contains' by calling its hitBy(armchair)
     * who will or will not react to that. Only LazyPandas can react. They react if the armchair is empty. In any other case we return false</p>   
     * 
     * @param armchair The armchair that wants to notify this tile's 'contains'.
     * @return Returns the success of the notifying operation. (true: LazyPanda was standing on this tile and armchair is empty, false otherwise)
     */
    public boolean placeThing(Armchair armchair){
        if(contains != null)
        {
        	boolean res = contains.hitBy(armchair);
            return res;
        }

        return false;
    }

    /**
     * loseLife()
     * <p>Virtual method. 
     * Decreases the lifetime of a breakable type tile by 1.
     * Does not do anything in this context, only has compatibility purposes.
     * 
     */
    public void loseLife(){}

    /**
     * unlink()
     * <p>Makes the tile unreachable from neighbors by removing it from their list of 'neighbors'.
     * 
     */
    public void unlink()
    {
        for(Tile neighbor : neighbors)
        {
            neighbor.getNeighbors().remove(this);
        }
    }
    
    public ArrayList<Tile> getNeighbors()
    {
        return neighbors;
    }

    public void addNeighbor(Tile tile)
    {
    	for(Tile n : neighbors) {
			if (n.equals(tile)) {
				return;
			}
		}
		neighbors.add(tile);
		tile.getNeighbors().add(this);
    }

    public void setContains(AThing thing)
    {
        contains = thing;
        if(thing != null)
            thing.setIsOn(this);
    }

    public AThing getContains()
    {
        return contains;
    }
}
