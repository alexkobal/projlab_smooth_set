import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class View extends JFrame {
	private JMenuItem newMenuItem, openMenuItem;
	private MenuActionListener menuActionListener;
	private GameJPanel mainPanel;

	private Map<Tile, Node> nodes = new HashMap<>();
	private Floor floor;


	private class GraphLine {
		int x0, y0, x1, y1;

		GraphLine(int x0, int y0, int x1, int y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}
	}

	private final LinkedList<GraphLine> lines = new LinkedList<>();



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

    private class GameJPanel extends JPanel
    {
        public Graphics graphics;

		@Override
		protected void paintComponent(Graphics g)
        {
            graphics = g;
            System.out.println("GameJPanel.paintComponent()");
		}
	}

    private void setUpMainPanel(){
		mainPanel = new GameJPanel();
		this.add(mainPanel, BorderLayout.CENTER);
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

	private class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openMenuItem){
				openFloor();
			}
		}
	}
	private void openFloor(){
        Floor.clearInstance();
		Floor floor = Floor.getInstance();
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.showDialog(this, "OpenFloor");
		floor = Floor.deserialise(fileChooser.getSelectedFile().getPath());
		StringBuilder sb = new StringBuilder(fileChooser.getSelectedFile().getPath());
        int idx = sb.lastIndexOf(".flr");
        sb.replace(idx, idx+4, ".txt");
        deserialize(sb.toString());
		Controller.getInstance().openFloor();
	}

    public void deserialize(String mapName)
    {
        try
        {
            FileInputStream fis = new FileInputStream(mapName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(Tile t : floor.getTiles().values())
            {
                String pos[] = br.readLine().split("\t");
                Node n = new Node(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                nodes.put(t, n);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Point getPivotPosition(Node n, Point size)
    {
        return new Point(n.getX() + (size.y/2),n.getY() + (size.y/2));
    }


    public void updateDraw()
    {
        //BasicTile the Tile which is the p0 in the line
        for(Tile bTile : floor.getTiles().values())
        {
            //NeighborTile is the Tile which can be the p1 in the line if (p1 != p0)
            for(Tile nTile: bTile.getNeighbors())
            {
                Node n0 = nodes.get(bTile);
                Node n1 = nodes.get(nTile);
                Point pivotedN0 = getPivotPosition(n0, new Point(90, 60));
                Point pivotedN1 = getPivotPosition(n1, new Point(90, 60));
                lines.add(new GraphLine(pivotedN0.x, pivotedN0.y, pivotedN1.x, pivotedN1.y));
            }
        }
        /*
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
        */

        repaint();
        for(Tile t : floor.getTiles().values())
        {
            t.invokeDraw();
        }
    }

    // állatok
    public void draw(Orangutan o)
    {
        try
        {
            Node currNode = nodes.get(o.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Orangutan.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(o.getIsOn().equals(entry.getKey()))      // megkeressük a map-ben, hogy az orángután alatti csempének mi az x,y-ja
            {
                Node node = entry.getValue();
                try
                {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
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
        */
    }

    public void draw(Panda p)
    {
        try
        {
            Node currNode = nodes.get(p.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Panda.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Panda.png missing");
        }


        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(p.getIsOn().equals(entry.getKey()))      // megkeressük a map-ben, hogy a panda alatti csempének mi az x,y-ja
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Panda.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
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
        */
    }

    // csempék
    public void draw(RegularTile rt)
    {

        try
        {
            Node currNode = nodes.get(rt);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(rt.getContains() != null)
            {
                rt.getContains().invokeDraw();

            }
        }
        catch (IOException e)
        {
            System.out.println("/images/RegularTile.png missing");
        }


        /*
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
            //jb.addActionListener(new ClickListener());
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
        */
    }

    public void draw(BrokenTile bt)
    {
        try
        {
            Node currNode = nodes.get(bt);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/BrokenTile.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(bt.getContains() != null)
            {
                bt.getContains().invokeDraw();

            }
        }
        catch (IOException e)
        {
            System.out.println("/images/BrokenTile.png missing");
        }

        /*
        Node node = nodes.get(bt);
        try {
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/BrokenTile.png"));
            JButton jb = new JButton(new ImageIcon((bi)));
            //jb.addActionListener(new ClickListener());
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
        */
    }

    // gépettyűk
    public void draw(Armchair a)
    {

        try
        {
            Node currNode = nodes.get(a.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Armchair.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(a.isOccupied())
            {
                a.getPanda().invokeDraw();
            }
        }
        catch (IOException e)
        {
            System.out.println("/images/Armchair.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(a.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Armchair.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
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
        */
    }

    public void draw(VendingMachine vm)
    {

        try
        {
            Node currNode = nodes.get(vm.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/VendingMachine.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/VendingMachine.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(vm.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/VendingMachine.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
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
        */
    }


    public void draw(GameMachine gm)
    {
        try
        {
            Node currNode = nodes.get(gm.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/GameMachine.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/GameMachine.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(gm.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/GameMachine.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
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
        */
    }

    // bejárat/kijárat
    public void draw(Exit ex)
    {
        try
        {
            Node currNode = nodes.get(ex.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Exit.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Exit.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(ex.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Exit.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX(), node.getY() - 13, 65, 65);
                    add(jb);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
        */
    }

    public void draw(Entry en)
    {
        try
        {
            Node currNode = nodes.get(en.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Entry.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Entry.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(en.getEntryTile().equals(entry.getKey()))    // Megkeressük az a csempét, amire az Entry kiléptet
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Entry.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX() + 2, node.getY() - 6, 65, 65);

                    if(en.getEntryTile().getContains() != null)
                    {
                        en.getEntryTile().getContains().invokeDraw();
                    }

                    add(jb);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
        */
    }

    //etc
    public void draw(Wardrobe w)
    {

        try
        {
            Node currNode = nodes.get(w.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Wardrobe.png"));
            mainPanel.graphics.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Wardrobe.png missing");
        }

        /*
        for(Map.Entry<Tile, Node> entry : nodes.entrySet())
        {
            if(w.getIsOn().equals(entry.getKey()))
            {
                Node node = entry.getValue();
                try {
                    BufferedImage bi = ImageIO.read(getClass().getResource("/images/Wardrobe.png"));
                    JButton jb = new JButton(new ImageIcon((bi)));
                    //jb.addActionListener(new ClickListener());
                    jb.setContentAreaFilled(false);
                    jb.setFocusPainted(false);
                    jb.setBorderPainted(false);
                    jb.setBorder(null);
                    jb.setBounds(node.getX() + 2, node.getY() + 5, 65, 65);
                    add(jb);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
        */
    }

	// A pályát egy JPanelben kell kirajzolni, amit egyszerüen
	// elhelyezünk a JFrame-en.
	/*
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

	 */
}
