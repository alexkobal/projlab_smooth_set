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

    public boolean placeThing(Panda panda){
        if(contains != null)
        {
            return contains.hitBy(panda);
        }

        // UNDER CONSTRUCTION
        return setContains(panda);
    }
    public boolean placeThing(Orangutan orangutan){
        if(contains != null)
        {
            return contains.hitBy(orangutan);
        }

        // UNDER CONSTRUCTION
        return setContains(orangutan);
    }
    public boolean placeThing(VendingMachine vm){
        if(contains != null)
        {
            return contains.hitBy(vm);
        }

        // UNDER CONSTRUCTION
        return setContains(vm);
    }
    public boolean placeThing(GameMachine gm){
        if(contains != null)
        {
            return contains.hitBy(gm);
        }

        // UNDER CONSTRUCTION
        return setContains(gm);
    }
    public boolean placeThing(Armchair armchair) {
        if (contains != null) {
            return contains.hitBy(armchair);
        }

        // UNDER CONSTRUCTION
        return setContains(armchair);
    }

    /*
    * Minden nem üres szomszédot értesítünk, hogy a rajtunk lévő AThing (itt most Armchair, GameMachine, VendingMachine)
    * akar valamit (=lejátszódik az effect() függvénye)
    * azóta a notifyNeighbors a tárgyak felelősége lett, így ez a komment az AThing-be meg azokra a leszármazottakra
    * vonatkozik, ahol a notifyNeighbors() függvényt felülírták.
    */

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
        if(!neighbors.contains((Tile)tile)) {
            neighbors.add(tile);
            tile.neighbors.add(this);
        }
    }

    //Privát kell, hogy legyen, lefuttat még egy ellenőrzést, hogy valóban üres
    //a csempe, és utána beállítja a contains-t booleannal tér vissza, hogy ezt a
    //placeThing-ben felhasználhassuk. Így ha be szeretnénk állítani a tárgyat a
    //csempén, mindíg a placeThing-et használjuk.
    private boolean setContains(AThing thing)
    {
        if(contains == null) {
            contains = thing;
            return true;
        }
        return false;
    }

    public AThing getContains()
    {
        return contains;
    }
}
