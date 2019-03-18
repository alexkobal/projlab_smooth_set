import java.util.*;

public class Tile
{
    protected AThing contains;          //protected, hogy a BrokenTile ne getteljen/setteljen
    protected ArrayList<Tile> neighbors;

    public Tile()
    {
        contains = null;
        neighbors = new ArrayList<Tile>();
    }


    public boolean placeThing(AThing thing)
    {
        if(contains != null)
        {
            Main.printer.functionCall(contains.name, "hitby", thing.name);
            boolean res = contains.hitBy(thing);
            Main.printer.returnFromFunctionCall();

            return res;
        }

        // UNDER CONSTRUCTION

        return false;
    }

    /*
    * Minden nem üres szomszédot értesítünk, hogy a rajtunk lévő AThing (itt most Armchair, GameMachine, VendingMachine)
    * akar valamit (=lejátszódik az effect() függvénye)
    *
    */
    public void notifyNeighbors()
    {
        for(Tile neighbor : neighbors)
        {
            if(neighbor.getContains() != null)
            {
                Main.printer.functionCall("nt", "placeThing");
                placeThing(contains);
                Main.printer.returnFromFunctionCall();
            }
        }
    }

    // virtuális függvény, hogy a sima tile kompatibilis legyen a brokentile-lal
    public void loseLife(){}

    // Az adott csempét elfelejtetjük a szomszédokkal
    public void unlink()
    {
        for(Tile neighbor : neighbors)
        {
            neighbor.getNeighbors().remove(neighbor);
        }
    }

    public ArrayList<Tile> getNeighbors()
    {
        return neighbors;
    }


    public void addNeighbor(Tile tile)
    {
        neighbors.add(tile);
    }

    public void setContains(AThing thing)
    {
        contains = thing;
    }

    public AThing getContains()
    {
        return contains;
    }
}
