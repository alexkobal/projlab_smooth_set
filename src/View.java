import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class View
{
    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;


    public View(String mapName, Floor _floor, int _floorSize)
    {
        floor = _floor;
        floorSize = _floorSize;
        Deserialize(mapName);
    }

    public void Deserialize(String mapName)
    {
        try
        {
            FileInputStream fis = new FileInputStream(mapName + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(int i = 0; i <= floorSize; i++)
            {
                Tile key = floor.getTile(i);
                String pos[] = br.readLine().split("\t");
                Node n = new Node(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                nodes.put(key, n);
            }

            //Akkor, ha a floor esetleg hashmap lenne es kozvetlen indexelheto
            /*
            for(Map.Entry<Integer, Tile> entry : floor.tiles.entrySet())
            {
                Tile value = entry.getValue();
                String pos[] = br.readLine().split("\t");
                Node n = new Node(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                nodes.put(key, n);
            }
            */
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateDraw()
    {
        for(int i = 0; i <= floorSize; i++)
        {
            Tile key = floor.getTile(i);
            //TODO
        }

        //Akkor, ha a floor esetleg hashmap lenne es kozvetlen indexelheto
        /*
        for(Map.Entry<Integer, Tile> entry : floor.tiles.entrySet())
        {
            Tile value = entry.getValue();
            //TODO
        }
        */
    }

    // állatok
    public void draw(Orangutan o)
    {

    }

    public void draw(Panda p)
    {

    }

    // csempék
    public void draw(RegularTile rt)
    {

    }

    public void draw(BrokenTile bt)
    {

    }

    // gépettyűk
    public void draw(Armchair a)
    {

    }

    public void draw(VendingMachine vm)
    {

    }

    public void draw(GameMachine gm)
    {

    }

    // bejárat/kijárat
    public void draw(Exit e)
    {

    }

    public void draw(Entry e)
    {

    }

    //etc
    public void Wardrobe(Armchair a)
    {

    }

}
