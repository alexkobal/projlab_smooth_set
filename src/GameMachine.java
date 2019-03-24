import java.util.ArrayList;
import java.util.ArrayList;

/**
 * GameMachine
 * <p>
 *     This class extends AThing and implements IPandaEffective. It represents the GameMachine of the game.
 *     This class can notifies the neighbors when does its effect.
 * </p>
 */
public class GameMachine extends AThing implements IPandaEffective{

	private int baseTinkleTime;

	private int tinkleTime;

	/**
	 * GameMachine contstructor
	 * <p>
	 *     This is the constructor of the GameMachine class.
	 * </p>
	 * @param t Is the time between the effect callings.
	 */
	public GameMachine(int t) {
		tinkleTime = t;
		baseTinkleTime = t;
	}

	/**
	 * GameMachine effect
	 * <p>
	 * This function is called when the controller calls it. (random intervalls usually)
	 */
	public void effect()
	{
		if(tinkleTime <= 0) {
			notifyNeighbors();
			tinkleTime = baseTinkleTime;
		}else{
			tinkleTime--;
		}
	}

	/**
	 * GameMachine notifyNeighbors
	 * <p>
	 * This function is called when an effect is active and need to notify the close neigbors that the GameMachine is ringing.
	 */
	@Override
	public void notifyNeighbors() {
		ArrayList<Tile> neighbors = isOn.getNeighbors();
		for(Tile neighbor : neighbors)
		{
			if(neighbor.getContains() != null)
			{
				Main.printer.functionCall("nt", "placeThing", "gm");
				neighbor.placeThing(this);
				Main.printer.returnFromFunctionCall();
			}
		}
	}
}
