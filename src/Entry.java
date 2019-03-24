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
	private int orangutansToPush;
	/**
	 * pushOrangutan
	 * <p>
	 *     This function places an orangutan on the floor.
	 * </p>
	 */
	public void pushOrangutan(){
		Orangutan orangutan = new Orangutan();
		Main.printer.functionCall("entry", "move", "entryTile");
		orangutan.move(entryTile);
		Main.printer.returnFromFunctionCall();
	}

	/**
	 * addOrangutan
	 * <p>
	 *     Adds orangutans to place on the floor.
	 * </p>
	 * @param n is the number of Orangutans to add for pushing.
	 */
	public void addOrangutan(int n){
		orangutansToPush += n;
	}
	/**
	 * nextTurn
	 * <p>
	 *     This function is called on every turn,
	 *     it places one Orangutan on the floor,
	 *     then decrements the number of Orangutans to push.
	 * </p>
	 */
	public void nextTurn(){
		if(orangutansToPush > 0){
			Main.printer.functionCall("entry", "pushOrangutan");
			pushOrangutan();
			Main.printer.returnFromFunctionCall();
			orangutansToPush--;
		}
	}
}
