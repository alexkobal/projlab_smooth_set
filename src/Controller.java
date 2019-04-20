import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Controller
 * <p>
 * This is a control class. It generates the map and moves the pandas.
 **/
public class Controller {

	public ArrayList<Animal> animals = new ArrayList<>();

	private Floor floor;
	private ArrayList<Panda> pandas;
	private ArrayList<Orangutan> orangutans;

	public Controller(Floor floor){
		this.floor = floor;
		pandas = new ArrayList<>();
		orangutans = new ArrayList<>();
	}

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		Random randomiser = new Random();
		panda.move(neighbors.get(randomiser.nextInt() % neighbors.size()));
	}

	public void start(){
		while(true) {
			if(floor.getEntry() != null){
				floor.getEntry().nextTurn();
			}
			if(floor.getExit() != null){
				floor.getExit().nextTurn();
			}
			System.out.println(floor.status());
		}
	}

	public void moveOrangutans(){

	}

	public void movePandas(){

	}


}