import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class View extends JFrame
{
    private JMenuItem newMenuItem, openMenuItem;
    private MenuActionListener menuActionListener;

    private Map<Tile, Node> nodes = new HashMap<>();
    private Floor floor;
    private int floorSize;



    private View() // Why do we need name and size params???
    {
        instance = this;
        initComponents();
    }

    private static View instance = null;
    public static View getInstance(){
        if(instance != null){
            return instance;
        }else{
            return instance = new View();
        }
    }

    private void initComponents()
    {
        setTitle("Panda Plaza by smooth_set");
        setSize((int)(1920 * 0.75), (int)(1080 * 0.75));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Setting up menu bar
        setUpMenuBar();
        //updateDraw();

        setVisible(true);
    }

    private void setUpMenuBar(){
        menuActionListener = new MenuActionListener();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(menuActionListener);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);

        menuBar.add(fileMenu);

        this.add(menuBar, BorderLayout.NORTH);
    }

    private class MenuActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == openMenuItem){
                openFloor();
            }
        }
    }
    private void openFloor(){
        Floor floor = Floor.getInstance();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "OpenFloor");
        floor = Floor.deserialise(fileChooser.getSelectedFile().getPath());

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

}
