import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Armchair
 * <p>
 *     This class extends AThing and implements IPandaEffective. It represents the armchair of the game.
 *     This class can notifies the neighbors when does its effect.
 * </p>
 */
public class Armchair extends AThing implements IPandaEffective{

	/**
	 * panda
	 * <p>
	 * This can hold the notified (Lazy) panda which wants to sleep.
	 */
	private LazyPanda panda;

	/**
	 * Armchair effect
	 * <p>
	 * This function is called when the controller calls it. (random intervalls)
	 */
	public void effect() {
		notifyNeighbors();
	}

	/**
	 * Armchair isOccupied
	 * <p>
	 * Gives true, if there is a panda in the Armchair.
	 */
	public boolean isOccupied()
	{
		if(panda != null)
			return true;
		return false;
	}

	/**
	 * Armchair notifyNeighbors
	 * <p>
	 * This function is called when an effect is active and need to notify the close neigbors that the Armchair is free.
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
	 *     Overrides Object.toString() method
	 * </p>
	 * @return "a" character
	 */
	@Override
	public String toString(){
		if(panda == null) {
			return "a";
		}
		return "a" + panda.getSleepTime();
	}

	/**
	 * getPanda
	 * <p>
	 *     Gets the panda that is sitting in the armchair
	 * </p>
	 * @return panda
	 */
	public Panda getPanda() { return panda; }

	/**
	 * setPanda
	 * <p>
	 *     Sets the panda that is sitting in the armchair
	 * </p>
	 * @param p panda
	 */
	public void setPanda(LazyPanda p)
	{
		panda = p;
		panda.setIsOn(isOn);
	}

	/**
	 * invokeDraw
	 * <p>
	 *     Implements the IDrawable interface
	 * </p>
	 * @param g graphics object of the drawing panel
	 */
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}
}
