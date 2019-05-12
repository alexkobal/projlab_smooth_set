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

	private boolean exit = false;
	private int Points = 0;

	private Controller(){
		pandas = new ArrayList<>();
		orangutans = new ArrayList<>();
		instance = this;
	}


	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		if(!neighbors.isEmpty()) {
			int n = Math.abs((new Random().nextInt() % neighbors.size()));
			panda.move(neighbors.get(n));
		}
	}

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

	public void exitNextTurn() {
		if(Floor.getInstance().getExit() != null)
		{
			Floor.getInstance().getExit().nextTurn();
		}
	}

	public void moveOrangutans(){
		Object[] or = orangutans.toArray();
		for(Object o : or){

			View.getInstance().setActiveOrangutan((Orangutan) o);
			Tile selectedTile = View.getInstance().moveActiveOrangutan();
            ((Orangutan)o).move(selectedTile);
		}
		orangutans.removeIf(Orangutan::getIsDead);
	}

	public void thingsNextTurn(){
		for(IPandaEffective ip : Floor.getInstance().getNotifiers()){
			ip.effect();
		}
	}

	public void movePandas(){
		Object[] panda = pandas.toArray();
		for(Object p : panda){
			if(!((Panda)p).isInChain())
				movePandaRandomly((Panda)p);
		}
	}

	public boolean checkEnd(){
		if(pandas.isEmpty() || orangutans.isEmpty()) {return true;}
		return false;
	}

	public void removePanda(Panda p){
		pandas.remove(p);
	}

	public void removeOrangutan(Orangutan o){
		orangutans.remove(o);
	}

	private static Controller instance = null;
	public static Controller getInstance(){
		if(instance != null){
			return instance;
		}else{
			return instance = new Controller();
		}
	}

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