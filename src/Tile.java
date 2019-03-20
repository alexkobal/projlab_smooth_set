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


    /*public boolean placeThing(AThing thing)
    {
        if(contains != null)
        {
            Main.printer.functionCall("Thing", "hitby", "Thing");   //temporary commented but need to be deleted
            boolean res = contains.hitBy(thing);
            Main.printer.returnFromFunctionCall();

            return res;
        }

        // UNDER CONSTRUCTION

        return false;
    }*/

    public boolean placeThing(Panda panda){
        if(contains != null)
        {
            return contains.hitBy(panda);
        }

        // UNDER CONSTRUCTION

        return false;
    }
    public boolean placeThing(Orangutan orangutan){
        if(contains != null)
        {
            return contains.hitBy(orangutan);
        }

        // UNDER CONSTRUCTION

        return false;
    }
    public boolean placeThing(VendingMachine vm){
        if(contains != null)
        {
            return contains.hitBy(vm);
        }

        // UNDER CONSTRUCTION

        return false;
    }
    public boolean placeThing(GameMachine gm){
        if(contains != null)
        {
            return contains.hitBy(gm);
        }

        // UNDER CONSTRUCTION

        return false;
    }
    public boolean placeThing(Armchair armchair){
        if(contains != null)
        {
            return contains.hitBy(armchair);
        }

        // UNDER CONSTRUCTION

        return false;
    }




    /*
    * Minden nem üres szomszédot értesítünk, hogy a rajtunk lévő AThing (itt most Armchair, GameMachine, VendingMachine)
    * akar valamit (=lejátszódik az effect() függvénye)
    *
    */
    /*
    public void notifyNeighbors()
    {
        for(Tile neighbor : neighbors)
        {
            if(neighbor.getContains() != null)
            {
                Main.printer.functionCall("nt", "placeThing");  // Need to be implemented in AThing and deleted here
                placeThing(contains);
                Main.printer.returnFromFunctionCall();
            }
        }
    }*/

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
