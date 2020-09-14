import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * class View
 * <p>
 *     Representing the main frame of the game
 *     extends JFrame
 * </p>
 */
public class View extends JFrame {
    /**
     * startMenuItem
     * openMenuItem
     * <p>
     *     startMenuItem - "Start" menu item
     *     openMenuItem - "Open" menu item
     * </p>
     */
    
	private JMenuItem startMenuItem, openMenuItem;
    /**
     * menuActionListener
     * <p>
     *     Handles events on the menu bar
     * </p>
     */
	private MenuActionListener menuActionListener;

    /**
     * mainPanel
     * <p>
     *     Panel where the game user interface is shown
     * </p>
     */
	private GameJPanel mainPanel;

    /**
     * nodes
     * <p>
     *     Pairs of nodes and tiles
     *     Needed to draw the floor
     * </p>
     */
	private Map<Tile, Node> nodes = new HashMap<>();

    /**
     * activeOrangutan
     * <p>
     *     Reference to the currently selected orangutan
     * </p>
     */
	private Orangutan activeOrangutan;

    /**
     * activeNeighbor
     * <p>
     *     Reference to the currently selected neighbor where player can step next
     * </p>
     */
	private Tile activeNeighbor;

    /**
     * confirmed
     * <p>
     *     Flag:
     *     false if the next step target tile is still not selected
     *     true if the next step target tile has been selected
     * </p>
     */
	private boolean confirmed = false;

    /**
     * inner class MyKeyListener
     * <p>
     *     Implementation of KeyListener
     *     if 'd' key is pressed moves forward in the selectable neighbor list
     *     if 'a' key is pressed moves backwards in the selectable neighbor list
     *     if ' ' key is pressed moves the currently active orangutan to selected tile
     *     if 'r' key is pressed releases the panda chain of currently selected orangutan
     * </p>
     */
    class MyKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyChar()){
                case 'a':
                    if(!confirmed)
                    {
                        for(int i = 0; i < activeOrangutan.getIsOn().getNeighbors().size(); i++)
                        {
                            if (activeOrangutan.getIsOn().getNeighbors().get(i) == activeNeighbor)
                            {
                                if (i == activeOrangutan.getIsOn().getNeighbors().size() - 1) {
                                    activeNeighbor = activeOrangutan.getIsOn().getNeighbors().get(0);
                                    break;
                                }
                                else {
                                    activeNeighbor = activeOrangutan.getIsOn().getNeighbors().get(i + 1);
                                    break;
                                }
                            }

                        }
                    }
                    break;

                case 'd':
                    if(!confirmed)
                    {
                        for(int i = 0; i < activeOrangutan.getIsOn().getNeighbors().size(); i++)
                        {
                            if (activeOrangutan.getIsOn().getNeighbors().get(i) == activeNeighbor)
                            {
                                if (i == 0) {
                                    activeNeighbor = activeOrangutan.getIsOn().getNeighbors().get(activeOrangutan.getIsOn().getNeighbors().size()-1);
                                    break;
                                }
                                else {
                                    activeNeighbor = activeOrangutan.getIsOn().getNeighbors().get(i - 1);
                                    break;
                                }
                            }

                        }
                    }
                    break;

                case ' ':
                    if(!confirmed) {
                        confirmed = true;
                    }
                    break;

                case 'r':
                    if(!confirmed)
                    {
                        activeOrangutan.manualUnchain();
                        confirmed = true;
                    }
                    break;

            }
            mainPanel.repaint();
        }
        
        @Override
        public void keyReleased(KeyEvent e) {}
    }

    /**
     * inner class GraphLine
     * <p>
     *     represents a line of the graph
     * </p>
     */
    private class GraphLine {
		int x0, y0, x1, y1;

		GraphLine(int x0, int y0, int x1, int y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.x1 = x1;
			this.y1 = y1;
		}
	}

    /**
     * lines
     * <p>
     *     Lines of the graph
     * </p>
     */
	private final LinkedList<GraphLine> lines = new LinkedList<>();


    /**
     * View
     * <p>
     *     Constructor
     *     Initialises the View
     *     Sets instance
     * </p>
     */
    private View()
    {
        instance = this;
        initComponents();
    }

    /**
     * instance
     * <p>
     *     Singleton implementation
     *     Represents a reference to the one and only one view instance
     * </p>
     */
    private static View instance = null;

    /**
     * getInstance
     * <p>
     *     Creates new instance if not existing yet
     *     Gets the singleton instance of the view
     * </p>
     * @return instance of view
     */
    public static View getInstance(){
        if(instance != null){
            return instance;
        }else{
            return instance = new View();
        }
    }

    /**
     * initComponents
     * <p>
     *     Sets the main frame parameters
     *     Calls the inner panel init functions
     * </p>
     */
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

        this.addKeyListener(new MyKeyListener());

        setVisible(true);
    }

    private class GameJPanel extends JPanel
    {
        public GameJPanel(){}

		@Override
		protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            super.setBackground(Color.CYAN);
            if(!nodes.isEmpty()) {
                updateDraw(g);
            }
            boolean forceRefresh = false;
            if(forceRefresh)
            {
                updateDraw(g);

            }
        }
	}

    /**
     * setUpMainPanel
     * <p>
     *     SetsUp the main game panel
     *     There the game is shown
     * </p>
     */
    private void setUpMainPanel(){
		mainPanel = new GameJPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setPreferredSize(this.getSize());
        mainPanel.setVisible(true);
	}

    /**
     * setUpMenuBar
     * <p>
     *     Initialises top menu bar
     *     Creates one "File" menu
     *     Creates one "Open" menu item
     *     Creates one "Start" menu item
     * </p>
     */
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

    /**
     * inner class MenuActionListener
     * <p>
     *     Implements interface ActionListener
     *     Handles menu click events
     *     If "Open" menu item is clicked, the program asks the user to choose a floor file for the game
     *     If "Start" menu item is clicked, the loaded game starts
     * </p>
     */
	private class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openMenuItem){
				openFloor();
				setUpMainPanel();
			}
			if(e.getSource() == startMenuItem)
			{
                SwingWorker<Void, Void> swGame = new SwingWorker<Void, Void>()
                {
                    @Override
                    protected Void doInBackground() throws Exception
                    {
                        Controller.getInstance().start();
                        return null;
                    }
                    @Override
                    protected void done()
                    {
                        JOptionPane.showMessageDialog(getParent(),"Game Over!");
                    }
                };
			    swGame.execute();
                setVisible(true);
            }
		}
	}

    /**
     * openFloor
     * <p>
     *     Function opens a JFileChooser dialog
     *     The player can choose witch floor he wants to play
     *     And the selected floor is loaded
     * </p>
     */
	private void openFloor(){
        Floor.clearInstance();
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.showDialog(this, "OpenFloor");
		Floor.deserialise(fileChooser.getSelectedFile().getPath());
		StringBuilder sb = new StringBuilder(fileChooser.getSelectedFile().getPath());
        int idx = sb.lastIndexOf(".flr");
        sb.replace(idx, idx+4, "");
        deserialize(sb.toString());
		mainPanel.repaint();
	}

    /**
     * deserialize
     * <p>
     *     Reads the coordinate file for the floor
     * </p>
     * @param mapName file name of the coordinate map
     */
    public void deserialize(String mapName)
    {
        float zoom = 0.85f;

        try
        {
            FileInputStream fis = new FileInputStream(mapName+ ".vw");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(Tile t : Floor.getInstance().getTiles().values())
            {
                String pos[] = br.readLine().split(" ");
                Node n = new Node((int)(Integer.parseInt(pos[0])*zoom), (int)(Integer.parseInt(pos[1])*zoom));
                nodes.put(t, n);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * getPivotPosition
     * <p>
     *     Gets the center of the graphical object
     * </p>
     * @param n
     * @param size
     * @return
     */
    public Point getPivotPosition(Node n, Point size)
    {
        return new Point(n.getX() + (size.y/2),n.getY() + (size.y/2));
    }

    /**
     * updateDraw
     * <p>
     *     Updates the view
     *     Draws the tiles(nodes) and the connecting lines (connecting neighbors)
     * </p>
     * @param g
     */
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

    /**
     * drawLines
     * <p>
     *     Draws the lines between neighbors
     * </p>
     * @param g
     */
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

    /**
     * setActiveOrangutan
     * <p>
     *     Sets the active orangutan
     *     Gets the neighbors of the tile where orangutan is standing
     * </p>
     * @param o
     */
    public void setActiveOrangutan(Orangutan o)
    {
        activeOrangutan = o;
        activeNeighbor = o.getIsOn().getNeighbors().get(0);
        mainPanel.repaint();
    }

    /**
     * moveActiveOrangutan
     * <p>
     *     Moves the selected orangutan to the selected tile if the selection is confirmed
     * </p>
     * @return
     */
    public Tile moveActiveOrangutan()
    {
        try {
            while (!confirmed) {
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        catch (Exception e) {}
        confirmed = false;

        return activeNeighbor;
    }

    //Draw functions for things

    //Animal draw functions

    /**
     * draw
     * <p>
     *     Draws an orangutan over it's tile
     * </p>
     * @param o orangutan to draw
     * @param g graphics object
     */
    public void draw(Orangutan o, Graphics g)
    {
        try
        {
            Node currNode = nodes.get(o.isOn);
            BufferedImage bi = ImageIO.read(getClass().getResource("/images/Orangutan.png"));
            g.drawImage(bi,currNode.getX(), currNode.getY(), null);

            if(activeOrangutan == o){
                g.setColor(Color.BLUE);
                g.drawOval(currNode.getX(),currNode.getY(),75,75);
                g.setColor(Color.yellow);
                for(int i = 0; i < o.getIsOn().neighbors.size(); i++){
                    Node tile = nodes.get(o.getIsOn().getNeighbors().get(i));
                    g.drawOval(tile.getX(), tile.getY(), 75, 75);
                }
                if(activeNeighbor != null) {
                    g.setColor(Color.pink);
                    g.drawOval(nodes.get(activeNeighbor).getX(), nodes.get(activeNeighbor).getY(), 75, 75);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("/images/Orangutan.png missing");
        }
    }

    /**
     * draw
     * <p>
     *     Draws a panda over it's tile
     * </p>
     * @param p panda to draw
     * @param g graphics object
     */
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

    // Tile draw functions

    /**
     * draw
     * <p>
     *     Draws a regular tile
     * </p>
     * @param rt RegularTile to draw
     * @param g graphics object
     */
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

    /**
     * draw
     * <p>
     *     Draws a broken tile
     * </p>
     * @param bt BrokenTile to draw
     * @param g graphics object
     */
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

    //Drawing notify-capable machines

    /**
     * draw
     * <p>
     *     Drawing an armchair over the tile it's located on
     * </p>
     * @param a Armchair
     * @param g graphics object
     */
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

    /**
     * draw
     * <p>
     *     Drawing a VendingMachine over the tile it is located on
     * </p>
     * @param vm VendingMachine
     * @param g graphics object
     */
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

    /**
     * draw
     * <p>
     *     Drawing a Game machine over the tile it is located on
     * </p>
     * @param gm game machine to draw
     * @param g graphics object
     */
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

    // Exit and Entry drawing functions

    /**
     * draw
     * <p>
     *     Drawing an Exit object over the tile it is located on
     * </p>
     * @param ex exit
     * @param g graphics object
     */
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

    /**
     * draw
     * <p>
     *     Drawing an Entry over the tile it is located on
     * </p>
     * @param en entry object
     * @param g graphics object
     */
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

    //Wardrobe drawing function

    /**
     * draw
     * <p>
     *     Drawing a Wardrobe over the tile it is located on
     * </p>
     * @param w Wardrobe to draw
     * @param g graphics objects
     */
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
