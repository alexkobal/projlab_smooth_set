import java.io.IOException;
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
				Orangutan orangutan = floor.getEntry().nextTurn();
				if(orangutan != null){
					orangutans.add(orangutan);
				}
			}
			if(floor.getExit() != null){
				floor.getExit().nextTurn();
			}
			if(!orangutans.isEmpty()){
				moveOrangutans();
			}
			System.out.println(floor.status());
		}
	}

	public void moveOrangutans(){
		for(Orangutan o : orangutans){
			System.out.println("make a move " + o.getName());
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePandas(){

	}


}