import java.util.ArrayList;

/**
 * Entry
 * <p>
 *     This class extends AThing. It represents the entry point of the game.
 *     This class generates new Orangutans on the floor.
 * </p>
 */
public class Entry extends AThing{

	/**
	 * Entry contstructor
	 * <p>
	 *     This is the constructor of the Entry class.
	 * </p>
	 * @param entryTile reference to the entry tile that is going to be set.
	 */
	public Entry(Tile entryTile){
		this.entryTile = entryTile;
	}

	/**
	 * entryTile
	 * <p>
	 *     This is the Tile where Entry places an Orangutan.
	 * </p>
	 */
	private Tile entryTile;
	/**
	 * orangutansToPush
	 * <p>
	 *     Number of orangutans to push to the floor.
	 * </p>
	 */
	//private int orangutansToPush;
	private ArrayList<Orangutan> orangutansToPush = new ArrayList<>();


	/**
	 * addOrangutan
	 * <p>
	 *     Adds orangutans to place on the floor.
	 * </p>
	 * @param oa is the number of Orangutans to add for pushing.
	 */
	public void addOrangutan(ArrayList<Orangutan> oa){
		orangutansToPush = oa;
		/*System.out.println("orangutan lista masolasa");
		System.out.println("Entryben lista merete:" + orangutansToPush.size());*/
	}

	/**
	 * nextTurn
	 * <p>
	 *     This function is called on every turn,
	 *     it places one Orangutan on the floor,
	 *     then decrements the number of Orangutans to push.
	 * </p>
	 */
	public Orangutan nextTurn(){
		Orangutan orangutan = null;
		//System.out.println("entry next turn");
		if(orangutansToPush.size() > 0){
			//System.out.println("orangutanstopush size > 0");
			orangutan = orangutansToPush.remove(0);
			if(orangutan.getPrevAnimal() != null) {
				//System.out.println("Elengedi az orangutan a pandat");
				orangutan.getPrevAnimal().setNextAnimal(null);
				orangutan.setPrevAnimal(null);
			}
			orangutan.setIsOn(entryTile);
			orangutan.prevTile = orangutan.isOn;
			entryTile.setContains(orangutan);
		}
		return orangutan;
	}

	@Override
	public String toString(){
		return "x" + orangutansToPush;
	}
}
