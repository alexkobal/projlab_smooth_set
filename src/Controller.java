import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	private InputStreamReader isr = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(isr);
	private boolean exit = false;

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
		while(!exit)
		{
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (line.length() <= 0) {
				break;
			}

			String[] part = line.split(" ");
			for(String s : part){
				System.out.println(s);
			}

			if(part.length > 1) {
				if (part[0].compareTo("move") == 0 && part.length == 3)
				{
					Animal selected = null;
					for (Animal a: animals)
					{
						if (a.getName().compareTo(part[1]) == 0)
							selected = a;
					}
					if(selected != null)
					{
						selected.move(floor.getTile(Integer.parseInt(part[2])));
						System.out.println(selected.getName()+"moves to "+ part[2]+ "tile");
					}
				}
			}

			if(floor.getEntry() != null)
			{
				Orangutan orangutan = floor.getEntry().nextTurn();
				if(orangutan != null)
				{
					orangutans.add(orangutan);
				}
			}
			if(floor.getExit() != null)
			{
				floor.getExit().nextTurn();
			}
			if(!orangutans.isEmpty())
			{
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