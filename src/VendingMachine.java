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

	private int beepTime;
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

	@Override
	public String toString(){
		return "v" + beepTime;
	}
}
