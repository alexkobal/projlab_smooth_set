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
    // singleton
    private static View instance = null;

    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;

   
    private View()
    {

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

    public static View getInstance()
    {
        if(instance == null)
        {
            instance = new View();
        }

        return instance;
    }

    public void construate(String mapName, Floor _floor, int _floorSize)
    {
        floor = _floor;
        floorSize = _floorSize;
        Deserialize(mapName);

        initComponents();
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

            // TODO gráfkirajzolás vhova ide
        }
    }

    // állatok
    public void draw(Orangutan o)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(o.getIsOn().equals(entry.getKey()))      // megkeressük a map-ben, hogy az orángután alatti csempének mi az x,y-ja
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX() , node.getY(), 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    public void draw(Panda p)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(p.getIsOn().equals(entry.getKey()))      // megkeressük a map-ben, hogy a panda alatti csempének mi az x,y-ja
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Panda.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX(), node.getY(), 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    // csempék
    public void draw(RegularTile rt)
    {
        Node node = nodes.get(rt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            JLabel jl = new JLabel(new ImageIcon((bi)));
            jl.setBounds(node.getX(), node.getY(), 65, 65);


            if(rt.getContains() != null)
            {
                rt.getContains().invokeDraw();

            }
            add(jl);        // Z-Order szerint a később rajzolt kerül alulra, először a rajta lévő cuccot rajzoljuk le

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(BrokenTile bt)
    {
        Node node = nodes.get(bt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/BrokenTile.png"));
            JLabel jl = new JLabel(new ImageIcon((bi)));
            jl.setBounds(node.getX(), node.getY(), 65, 65);


            if(bt.getContains() != null)
            {
                bt.getContains().invokeDraw();

            }
            add(jl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gépettyűk
    public void draw(Armchair a)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(a.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Armchair.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX(), node.getY(), 65, 65);

                    if(a.isOccupied())
                    {
                        a.getPanda().invokeDraw();
                    }

                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    public void draw(VendingMachine vm)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(vm.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/VendingMachine.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX(), node.getY(), 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    public void draw(GameMachine gm)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(gm.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/GameMachine.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX() + 5, node.getY(), 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    // bejárat/kijárat
    public void draw(Exit ex)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(ex.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Exit.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX(), node.getY() - 13, 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    public void draw(Entry en)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(en.getEntryTile().equals(entry.getKey()))    // Megkeressük az a csempét, amire az Entry kiléptet
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Entry.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX() + 2, node.getY() + 5, 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    //etc
    public void draw(Wardrobe w)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(w.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Wardrobe.png"));
                    JLabel jl = new JLabel(new ImageIcon((bi)));
                    jl.setBounds(node.getX() + 2, node.getY() + 5, 65, 65);
                    add(jl);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

}
