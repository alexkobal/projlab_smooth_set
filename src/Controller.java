import java.util.ArrayList;
import java.util.Random;

public class Controller {

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		Random randomizer = new Random();
		panda.move(neighbors.get(randomizer.nextInt() % neighbors.size()));
	}
}
