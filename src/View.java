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
    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;

   

    public View(String mapName, Floor _floor, int _floorSize) // Why do we need name and size params???
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
            key.invokeDraw();
        }
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
        System.out.println(floorSize); // instancenak a cuccai.. vmiért full üresek...


        Node node = nodes.get(rt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            JLabel jl = new JLabel(new ImageIcon((bi)));
            jl.setBounds(500, 500, 55, 55);
            jl.setLocation(500, 500);
            add(jl);

            if(rt.getContains() != null)
            {
                rt.getContains().invokeDraw();
            }



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
    public void draw(Wardrobe w)
    {

    }

    private static View instance = null;
    public static View getInstance(){
        if(instance != null){
            return instance;
        }else{
            return instance = new View("", Floor.getInstance(), 0);
        }
    }
}
