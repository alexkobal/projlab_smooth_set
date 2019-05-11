import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class View extends JFrame
{
    /* HIBÁK:
    *
    * A floorba tárolt Tile-ok nem kirajzolhatók draw-val, mert nincs draw(Tile t) fv.
    * A tile contains-ét nem lehet drawolni, mert nincs draw(AThing a) -> Legyen minden AThing leszármazottnak egy draw(Node hova) függvénye?
    *
    *
    *
     */



    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;


    public View(String mapName, Floor _floor, int _floorSize)
    {
        floor = _floor;
        floorSize = _floorSize;
        Deserialize(mapName);

        initComponents();
    }

    private void initComponents()
    {
        setTitle("Panda Plaza by smooth_set");
        setSize((int)(1920 * 0.75), (int)(1080 * 0.75));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        updateDraw();

        setVisible(true);
    }

    public void Deserialize(String mapName)
    {
        try
        {
            FileInputStream fis = new FileInputStream(mapName + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(int i = 0; i < floorSize; i++)
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
        for(int i = 0; i < floorSize; i++)
        {
            Tile key = floor.getTile(i);
            draw(key); // <- hiba nincs draw(Tile)

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
        for(int i = 0; i < floorSize; i++)
        {
            if(o.getIsOn().equals(floor.getTile(i)))    // megkeressük a cellát amin van
            {
                draw(o.getIsOn()); // <- hiba nincs draw(Tile)
                break;
            }
        }
    }

    public void draw(Panda p)
    {
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Panda.png"));
            Graphics2D g2D = bi.createGraphics();
            AlphaComposite ac= AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  0.2f);
            JLabel jl = new JLabel(new ImageIcon((bi)));
            jl.setBounds(500, 500, 55, 55); // <-
            add(jl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // csempék
    public void draw(RegularTile rt)
    {
        Node node = nodes.get(rt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            JLabel jl = new JLabel(new ImageIcon((bi)));
            jl.setBounds(node.getX(), node.getY(), 55, 55);
            add(jl);

            /*if(rt.getContains() != null)
            {
                if(rt.getContains() instanceof Panda) // NINCSEN draw(AThing) tudni kéne mit rajzolunk
                {
                    System.out.print("HEHEXD");
                    draw(new JumpingPanda());
                }
            }*/



        } catch (IOException e) {
            e.printStackTrace();
        }

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
