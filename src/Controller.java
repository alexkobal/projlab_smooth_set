import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;

/**
 * Controller
 * <p>
 * This is a control class. It generates the map and moves the pandas.
 **/
public class Controller {
	/**
	 * pandas
	 * <p>
	 *     Pandas located on the floor
	 * </p>
	 */
	private ArrayList<Panda> pandas;
	/**
	 * orangutans
	 * <p>
	 *     Orangutans located on the floor
	 * </p>
	 */
	private ArrayList<Orangutan> orangutans;
	/**
	 * exit
	 * <p>
	 *     This flag tells if the game is over
	 * </p>
	 */
	private boolean exit = false;
	/**
	 * Points
	 * <p>
	 *     Collected point counter
	 * </p>
	 */
	private int Points = 0;

	/**
	 * Controller
	 * <p>
	 *     Default constructor. Initialises pandas, orangutans
	 *     and sets instance
	 * </p>
	 */
	private Controller(){
		pandas = new ArrayList<>();
		orangutans = new ArrayList<>();
		instance = this;
	}

	/**
	 * movePandaRandomly
	 * <p>
	 *     Moves one panda to random neighbor tile
	 * </p>
	 * @param panda panda to move
	 */
	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		if(!neighbors.isEmpty()) {
			int n = Math.abs((new Random().nextInt() % neighbors.size()));
			panda.move(neighbors.get(n));
		}
	}

	/**
	 * start
	 * <p>
	 *     Starts the game
	 *     Runs tha game loop
	 * </p>
	 */
	public void start(){
		addAnimals(2,3);
		while(!exit)
		{
			entryNextTurn();

			exitNextTurn();


			if(!orangutans.isEmpty())
			{
				moveOrangutans();
			}
			thingsNextTurn();
			if(!pandas.isEmpty())
				movePandas();
			exit = checkEnd();
		}
	}

	/**
	 * entryNextTurn
	 * <p>
	 *     Does what should be done with entry in every turn
	 * </p>
	 */
	public void entryNextTurn(){
		if(Floor.getInstance().getEntry() != null)
		{
			try {
				Floor.getInstance().getEntry().addOrangutan(Floor.getInstance().getExit().getOrangutansToPush());
			} catch (Exception e) {

			}

			Orangutan orangutan = Floor.getInstance().getEntry().nextTurn();
			if(orangutan != null)
			{
				orangutans.add(orangutan);
			}
		}
	}

	/**
	 * exitNextTurn
	 * <p>
	 *     Does what should be done with exit every turn
	 * </p>
	 */
	public void exitNextTurn() {
		if(Floor.getInstance().getExit() != null)
		{
			Floor.getInstance().getExit().nextTurn();
		}
	}

	/**
	 * moveOrangutans
	 * <p>
	 *     Iterates the orangutans and asks tha player to make the next move
	 * </p>
	 */
	public void moveOrangutans(){
		Object[] or = orangutans.toArray();
		for(Object o : or){

			View.getInstance().setActiveOrangutan((Orangutan) o);
			Tile selectedTile = View.getInstance().moveActiveOrangutan();
            ((Orangutan)o).move(selectedTile);
		}
		orangutans.removeIf(Orangutan::getIsDead);
	}

	/**
	 * thingsNextTurn
	 * <p>
	 *     Iterates the notify-able things, and makes them notify
	 * </p>
	 */
	public void thingsNextTurn(){
		for(IPandaEffective ip : Floor.getInstance().getNotifiers()){
			ip.effect();
		}
	}

	/**
	 * movePandas
	 * <p>
	 *     Moves all the pandas on the floor
	 * </p>
	 */
	public void movePandas(){
		Object[] panda = pandas.toArray();
		for(Object p : panda){
			if(!((Panda)p).isInChain())
				movePandaRandomly((Panda)p);
		}
	}

	/**
	 * checkEnd
	 * <p>
	 *     Checks if the game is over
	 * </p>
	 * @return true if game over, false if still running
	 */
	public boolean checkEnd(){
		if(pandas.isEmpty() || orangutans.isEmpty()) {return true;}
		return false;
	}

	/**
	 * removePanda
	 * <p>
	 *     Removes a panda from the game
	 * </p>
	 * @param p panda to remove
	 */
	public void removePanda(Panda p){
		pandas.remove(p);
	}

	/**
	 * removeOrangutan
	 * <p>
	 *     Removes an orangutan from the game
	 * </p>
	 * @param o orangutan to remove
	 */
	public void removeOrangutan(Orangutan o){
		orangutans.remove(o);
	}

	/**
	 * instance
	 * <p>
	 *     Singleton implementation
	 *     Represents the one and only one instance of the Controller
	 * </p>
	 */
	private static Controller instance = null;

	/**
	 * getInstance
	 * <p>
	 *     Creates a controller if not created
	 *     Gets the controller if exists
	 * </p>
	 * @return controller instance
	 */
	public static Controller getInstance(){
		if(instance != null){
			return instance;
		}else{
			return instance = new Controller();
		}
	}

	/**
	 * placePandaRandomly
	 * <p>
	 *     Places a given panda on a random tile
	 *     Needed while initialisation
	 * </p>
	 * @param panda panda to place
	 */
	private void placePandaRandomly(Panda panda){
		int size = Floor.getInstance().getTiles().size();
		int random = new Random().nextInt(size);
		Iterator<Tile> iter = Floor.getInstance().getTiles().values().iterator();
		Tile current = null;
		while(size > random && iter.hasNext()){
			current = iter.next();
			size--;
		}
		if(current.getContains() != null){
			placePandaRandomly(panda);
		}else{
			current.setContains(panda);
			pandas.add(panda);
		}
	}

	/**
	 * addAnimals
	 * <p>
	 *     Adds animals to the game/floor
	 * </p>
	 * @param nOrangutans number of orangutans to add
	 * @param nPandas number of pandas to add
	 */
	public void addAnimals(int nOrangutans, int nPandas){
		for(int i = 0; i < nPandas; i++){
			int random = new Random().nextInt(nPandas);
			switch (random){
				case 0:
					placePandaRandomly(new LazyPanda(new Random().nextInt(5)));
					break;
				case 1:
					placePandaRandomly(new JumpingPanda());
					break;
				default:
					placePandaRandomly(new ScaredPanda());
					break;
			}
		}

		ArrayList<Orangutan> or = new ArrayList<>();
		for(int i = 0; i < nOrangutans; i++) {
			or.add(new Orangutan());
		}
		Floor.getInstance().getEntry().addOrangutan(or);
	}
}