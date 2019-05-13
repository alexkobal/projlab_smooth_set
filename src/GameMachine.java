import java.awt.*;
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
	/**
	 * baseTinkleTime
	 * <p>
	 *     Tinkle time set by default, this is the revert value
	 * </p>
	 */
	private int baseTinkleTime;
	/**
	 * tinkleTime
	 * <p>
	 *     Actual tinkle time (time left to before next tinkle)
	 * </p>
	 */
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
				neighbor.placeThing(this);
			}
		}
	}

	/**
	 * toString
	 * <p>
	 *     Overrides Object.toString() in case of better readability
	 * </p>
	 * @return "g" string
	 */
	@Override
	public String toString(){
		return "g";
	}

	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}
}
