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

	public ArrayList<Animal> animals = new ArrayList<>();

	private ArrayList<Panda> pandas;
	private ArrayList<Orangutan> orangutans;

	private InputStreamReader isr = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(isr);
	private boolean exit = false;

	private Controller(){
		pandas = new ArrayList<>();
		orangutans = new ArrayList<>();
		instance = this;
	}


	public void loadAnimals(String mapName)
	{
		try
		{
			FileInputStream fis = new FileInputStream(mapName+ ".ani");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			while((line = br.readLine()).length() != 0)
			{
				String[] param = line.split(" ");
				switch (param[0])
				{
					case "o":
						Orangutan o = new Orangutan();
						o.setIsOn(Floor.getInstance().getTile(Integer.parseInt(param[2])));
						o.setName(param[1]);
						o.setPrevTile(o.getIsOn());
						animals.add(o);
						orangutans.add(o);
                        Floor.getInstance().getTile(Integer.parseInt(param[2])).setContains(o);
					break;
					case "lp":
						int rnd = Math.abs(new Random().nextInt(4) + 1);
						LazyPanda lp = new LazyPanda(rnd);
						lp.setIsOn(Floor.getInstance().getTile(Integer.parseInt(param[2])));
						lp.setName(param[1]);
						lp.setPrevTile(lp.getIsOn());
						animals.add(lp);
						pandas.add(lp);
                        Floor.getInstance().getTile(Integer.parseInt(param[2])).setContains(lp);
						break;
					case "sp":
						ScaredPanda sp = new ScaredPanda();
						sp.setIsOn(Floor.getInstance().getTile(Integer.parseInt(param[2])));
						sp.setName(param[1]);
						sp.setPrevTile(sp.getIsOn());
						animals.add(sp);
						pandas.add(sp);
                        Floor.getInstance().getTile(Integer.parseInt(param[2])).setContains(sp);
						System.out.println(Floor.getInstance().status());
						break;
					case "jp":
						JumpingPanda jp = new JumpingPanda();
						jp.setIsOn(Floor.getInstance().getTile(Integer.parseInt(param[2])));
						jp.setName(param[1]);
						jp.setPrevTile(jp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						animals.add(jp);
						pandas.add(jp);
                        Floor.getInstance().getTile(Integer.parseInt(param[2])).setContains(jp);
						break;
					case "chain":
						Animal elso = null;
						Animal masodik = null;
						for (Animal a: animals) {
							if(a.getName().compareTo(param[1]) == 0)
								elso = a;
							if(a.getName().compareTo(param[2]) == 0)
								masodik = a;
						}

						if(elso != null && masodik != null){		//Bemeneti nyelv szerint ezt csinálja, de van az animalnak connectChain függvénye!!!
							elso.prevAnimal = masodik;
							masodik.nextAnimal = elso;
						}
						break;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void movePandaRandomly(Panda panda){
		ArrayList<Tile> neighbors = panda.getIsOn().getNeighbors();
		if(!neighbors.isEmpty()) {
			int n = Math.abs((new Random().nextInt() % neighbors.size()));
			panda.move(neighbors.get(n));
		}
	}

	public void start(){
		System.out.println("Started");
		addAnimals(3,3);
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
		System.out.println("Exited");
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
			System.out.println("make a move " + ((Orangutan)o).getName());

			View.getInstance().setActiveOrangutan((Orangutan) o);
			View.getInstance().moveActiveOrangutan();

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
						selected.move(Floor.getInstance().getTile(Integer.parseInt(part[2])));
					}
					System.out.println(Floor.getInstance().status());
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
					System.out.println(Floor.getInstance().status());
				}
			}
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