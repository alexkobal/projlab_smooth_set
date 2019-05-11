import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/*

    Kell még:

    ActionListenerek a a JLabelekre.
    Utak a csempék között.


 */

public class View extends JPanel
{
    // singleton
    private static View instance = null;

    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;


    private class GraphLine
    {
        public int x0, y0, x1, y1;

        GraphLine(int x0, int y0, int x1, int y1)
        {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }
    private final LinkedList<GraphLine> lines = new LinkedList<>();

   
    private View()
    {

    }

    private void initComponents()
    {

        setLayout(null);
        updateDraw();
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

            for(int j = i; j < floorSize; j++)
            {
                for(int x = 0; x < key.getNeighbors().size(); x++)
                {
                    if(floor.getTile(j).equals(key.getNeighbors().get(x)))
                    {
                        Node n0 = nodes.get(key);
                        Node n1 = nodes.get(floor.getTile(j));

                        lines.add(new GraphLine(n0.getX() + 45, n0.getY() + 30, n1.getX() + 45, n1.getY() + 30));
                    }
                }
            }
        }

        repaint();


        for(int i = 0; i < floorSize; i++)
        {
            Tile key = floor.getTile(i);
            key.invokeDraw();
        }


        System.out.println(lines.size());
    }

    // állatok
    public void draw(Orangutan o)
    {
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(o.getIsOn().equals(entry.getKey()))      // megkeressük a map-ben, hogy az orángután alatti csempének mi az x,y-ja
            {
                Node node = entry.getValue();
                try
                {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX() , node.getY(), 65, 65);
                    add(jb);


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
        // Először megnézzük, hogy nem véletlen bejárati csempéről van szó.
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(entry.getKey().equals(rt) && floor.getEntry().getEntryTile().equals(entry.getKey()))
            {
                draw(floor.getEntry());
                break;
            }
        }

        Node node = nodes.get(rt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            JButton jb = new JButton(new ImageIcon((bi)));
            jb.addActionListener(new ClickListener());
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(false);
            jb.setBorderPainted(false);
            jb.setBorder(null);
            jb.setBounds(node.getX(), node.getY(), 65, 65);


            if(rt.getContains() != null)
            {
                rt.getContains().invokeDraw();

            }
            add(jb);        // Z-Order szerint a később rajzolt kerül alulra, először a rajta lévő cuccot rajzoljuk le

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(BrokenTile bt)
    {
        Node node = nodes.get(bt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/BrokenTile.png"));
            JButton jb = new JButton(new ImageIcon((bi)));
            jb.addActionListener(new ClickListener());
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(false);
            jb.setBorderPainted(false);
            jb.setBorder(null);
            jb.setBounds(node.getX(), node.getY(), 65, 65);


            if(bt.getContains() != null)
            {
                bt.getContains().invokeDraw();

            }
            add(jb);

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
                    JButton jb = new JButton(new ImageIcon((bi)));
                    jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX(), node.getY(), 65, 65);

                    if(a.isOccupied())
                    {
                        a.getPanda().invokeDraw();
                    }

                    add(jb);

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
                    JButton jb = new JButton(new ImageIcon((bi)));
                    jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX(), node.getY(), 65, 65);
                    add(jb);

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
                    JButton jb = new JButton(new ImageIcon((bi)));
                    jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX() + 5, node.getY(), 65, 65);
                    add(jb);

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
                    jl.setBounds(node.getX() + 2, node.getY() - 6, 65, 65);

                    if(en.getEntryTile().getContains() != null)
                    {
                        en.getEntryTile().getContains().invokeDraw();
                    }

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



    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(GraphLine gl : lines)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawLine(gl.x0, gl.y0, gl.x1, gl.y1);
        }
    }

    public class ClickListener implements ActionListener
    {

        public ClickListener()
        {

        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("xd");
        }
    }


}
