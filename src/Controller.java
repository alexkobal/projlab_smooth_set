import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Controller
 * <p>
 * This is a control class. It generates the map and moves the pandas.
 **/
public class Controller {

	public ArrayList<Animal> animals = new ArrayList<>();

	public Floor floor;
	public ArrayList<Panda> pandas;
	public ArrayList<Orangutan> orangutans;

	private InputStreamReader isr = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(isr);
	private boolean exit = false;

	public Controller(Floor floor){
		this.floor = floor;
		pandas = new ArrayList<>();
		orangutans = new ArrayList<>();
		Tile.ctrl = this;
	}

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		Random randomiser = new Random();
		if(!neighbors.isEmpty()) {
			int n = Math.abs((randomiser.nextInt() % neighbors.size()));
			panda.move(neighbors.get(n));
		}
	}

	public void start(){
		System.out.println(floor.status());
		while(!exit)
		{
			entryNextTurn();
			if(!orangutans.isEmpty())
			{
				moveOrangutans();
			}
			thingsNextTurn();
			if(!pandas.isEmpty())
				movePandas();
			exitNextTurn();
			exit = checkEnd();
		}
	}

	public void entryNextTurn(){
		if(floor.getEntry() != null)
		{
			try {
				floor.getEntry().addOrangutan(floor.getExit().getOrangutansToPush());
			} catch (Exception e) {

			}

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
		Object[] or = orangutans.toArray();
		for(Object o : or){
			System.out.println("make a move " + ((Orangutan)o).getName());

			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] part = line.split(" ");

			if(part.length > 1) {
				if (part[0].compareTo("move") == 0 && part.length == 3)
				{
					Orangutan selected = null;
					for (Orangutan a: orangutans)
					{
						if (a.getName().compareTo(part[1]) == 0)
							selected = a;
					}
					if(selected != null)
					{
						selected.move(floor.getTile(Integer.parseInt(part[2])));
					}
					System.out.println(floor.status());
				}
				if(part[0].compareTo("unchain") == 0 && part.length == 2){

					Orangutan selected = null;
					for (Orangutan a: orangutans)
					{
						if (a.getName().compareTo(part[1]) == 0)
							selected = a;
					}
					if(selected != null)
					{
						selected.manualUnchain();
					}
					System.out.println(floor.status());
				}
			}
		}
		orangutans.removeIf(Orangutan::getIsDead);
	}

	public void thingsNextTurn(){
		for(IPandaEffective ip : floor.getNotifiers()){
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

}