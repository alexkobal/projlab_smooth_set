import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * VendingMachine
 * <p>
 *     This class extends AThing and implements IPandaEffective. It represents the VendingMachine of the game.
 *     This class can notifies the neighbors when does its effect.
 * </p>
 */
public class VendingMachine extends AThing implements IPandaEffective{

	/**
	 * beepTime
	 * <p>
	 *     Time left to the next beep
	 *     After beeped is equal with the baseBeepTime
	 * </p>
	 */
	private int beepTime;

	/**
	 * baseBeepTime
	 * <p>
	 *     Default time left to the next beep
	 *     beepTime is set to this value after beeped
	 * </p>
	 */
	private int baseBeepTime;

	/**
	 * VendingMachine contstructor
	 * <p>
	 *     This is the constructor of the VendingMachine class.
	 * </p>
	 * @param t Is the time between the effect callings.
	 */
	public VendingMachine(int t) {
		beepTime = t;
		baseBeepTime = t;
	}

	/**
	 * VendingMachine effect
	 * <p>
	 * This function is called when the controller calls it. (random intervalls usually)
	 */
	public void effect()
	{
		if(beepTime <= 0) {
			notifyNeighbors();
			beepTime = baseBeepTime;
		}else{
			beepTime--;
		}
	}

	/**
	 * VendingMachine notifyNeighbors
	 * <p>
	 * This function is called when an effect is active and need to notify the close neigbors that the VendingMachine is ringing.
	 */
	@Override
	public void notifyNeighbors() {
		Object[] neighbors = isOn.getNeighbors().toArray();
		for(Object neighbor : neighbors)
		{
			if(((Tile)neighbor).getContains() != null)
			{
				((Tile)neighbor).placeThing(this);
			}
		}
	}

	/**
	 * toString
	 * <p>
	 *     Overrides the Object.toString() in case of readability
	 * </p>
	 * @return "v" string and the time left to the next beep
	 */
	@Override
	public String toString(){
		return "v" + beepTime;
	}

	/**
	 * invokeDraw
	 * <p>
	 *     Implementation of IDrawable interface
	 * </p>
	 * @param g graphics object
	 */
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}
}
