import java.awt.*;
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
	public ArrayList<Panda> pandas;
	public ArrayList<Orangutan> orangutans;

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
			entryNextTurn();
			exitNextTurn();
			if(!orangutans.isEmpty())
			{
				moveOrangutans();
			}
			thingsNextTurn();
			movePandas();
			exit = checkEnd();
		}
	}

	public void entryNextTurn(){
		if(floor.getEntry() != null)
		{
			floor.getEntry().addOrangutan(floor.getExit().getOrangutansToPush());
			Orangutan orangutan = floor.getEntry().nextTurn();
			if(orangutan != null)
			{
				orangutans.add(orangutan);
			}
		}
	}

	public void exitNextTurn() {
		if(floor.getExit() != null)
		{
			floor.getExit().nextTurn();
		}
	}

	public void moveOrangutans(){
		for(Orangutan o : orangutans){
			System.out.println("make a move " + o.getName());

			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
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
						System.out.println(floor.status());
					}
				}
			}
		}
	}

	public void thingsNextTurn(){
		for(IPandaEffective ip : floor.getNotifiers()){
			ip.effect();
		}
	}

	public void movePandas(){
		for(Panda p : pandas){
			movePandaRandomly(p);
		}
	}

	public boolean checkEnd(){
		if(pandas.isEmpty() || orangutans.isEmpty()) {return true;}
		return false;
	}

}