import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Controller {

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		Random randomiser = new Random();
		panda.move(neighbors.get(randomiser.nextInt() % neighbors.size()));
	}
}
