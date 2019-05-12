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
	private JMenuItem startMenuItem, openMenuItem;
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



    private View()
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
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setUpMainPanel();

        //Setting up menu bar
        setUpMenuBar();
        //updateDraw();

        setVisible(true);
    }

    private class GameJPanel extends JPanel
    {
        public Graphics graphics;

        public GameJPanel(){}

		@Override
		protected void paintComponent(Graphics g)
        {
            graphics = g;
            super.paintComponent(g);
            if(!nodes.isEmpty()) {
                updateDraw(g);
            }
        }
	}

    private void setUpMainPanel(){
		mainPanel = new GameJPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setPreferredSize(this.getSize());
		mainPanel.setVisible(true);
	}
	private void setUpMenuBar(){
		menuActionListener = new MenuActionListener();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		startMenuItem = new JMenuItem("Start");
		startMenuItem.addActionListener(menuActionListener);
		openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(menuActionListener);

		fileMenu.add(startMenuItem);
		fileMenu.add(openMenuItem);

		menuBar.add(fileMenu);

		this.add(menuBar, BorderLayout.NORTH);
		this.pack();
	}

	private class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openMenuItem){
				openFloor();
				setUpMainPanel();
			}
			if(e.getSource() == startMenuItem){
			    Controller.getInstance().start();
            }
		}
	}
	private void openFloor(){
        Floor.clearInstance();
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.showDialog(this, "OpenFloor");
		Floor.deserialise(fileChooser.getSelectedFile().getPath());
		StringBuilder sb = new StringBuilder(fileChooser.getSelectedFile().getPath());
        int idx = sb.lastIndexOf(".flr");
        sb.replace(idx, idx+4, "");
        deserialize(sb.toString());
        //Controller.getInstance().loadAnimals(sb.toString());
		//Controller.getInstance().openFloor();
		//Controller.getInstance().start();
		mainPanel.repaint();
	}

    public void deserialize(String mapName)
    {
        try
        {
            FileInputStream fis = new FileInputStream(mapName+ ".vw");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(Tile t : Floor.getInstance().getTiles().values())
            {
                String pos[] = br.readLine().split(" ");
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


    public void updateDraw(Graphics g)
    {
        //BasicTile the Tile which is the p0 in the line
        for(Tile bTile : Floor.getInstance().getTiles().values())
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

        drawLines(g);
        for(Tile t : Floor.getInstance().getTiles().values())
        {
            t.invokeDraw(g);
        }
        mainPanel.repaint();
    }

    public void drawLines(Graphics g){
        for(GraphLine gl : lines)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawLine(gl.x0, gl.y0, gl.x1, gl.y1);
        }
    }

    // állatok
    public void draw(Orangutan o, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(o.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Orangutan.png missing");
        }
    }

    public void draw(Panda p, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(p.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Panda.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Panda.png missing");
        }
    }

    // csempék
    public void draw(RegularTile rt, Graphics g)
    {

        try
        {
            Node currNode = nodes.get(rt);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/RegularTile.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(rt.getContains() != null)
            {
                rt.getContains().invokeDraw(g);

            }
        }
        catch (IOException e)
        {
            System.out.println("/images/RegularTile.png missing");
        }
    }

    public void draw(BrokenTile bt, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(bt);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/BrokenTile.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(bt.getContains() != null)
            {
                bt.getContains().invokeDraw(g);

            }
        }
        catch (IOException e)
        {
            System.out.println("/images/BrokenTile.png missing");
        }
    }

    // gépettyűk
    public void draw(Armchair a, Graphics g)
    {

        try
        {
            Node currNode = nodes.get(a.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Armchair.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(a.isOccupied())
            {
                a.getPanda().invokeDraw(g);
            }
        }
        catch (IOException e)
        {
            System.out.println("/images/Armchair.png missing");
        }
    }

    public void draw(VendingMachine vm, Graphics g)
    {

        try
        {
            Node currNode = nodes.get(vm.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/VendingMachine.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/VendingMachine.png missing");
        }
    }


    public void draw(GameMachine gm, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(gm.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/GameMachine.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/GameMachine.png missing");
        }
    }

    // bejárat/kijárat
    public void draw(Exit ex, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(ex.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Exit.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Exit.png missing");
        }
    }

    public void draw(Entry en, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(en.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Entry.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);
        }
        catch (IOException e)
        {
            System.out.println("/images/Entry.png missing");
        }
    }

    //etc
    public void draw(Wardrobe w, Graphics g) {

        try {
            Node currNode = nodes.get(w.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Wardrobe.png"));
            g.drawImage(bi, currNode.getX(), currNode.getY(), null);
        } catch (IOException e) {
            System.out.println("/images/Wardrobe.png missing");
        }
    }
}
