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

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		Random randomiser = new Random();
		panda.move(neighbors.get(randomiser.nextInt() % neighbors.size()));
	}
}