import java.awt.*;

/**
 * Orangutan
 * <p>
 * This is Orangutan which extends the animal class. It represents the Orangutan kind of objects on the map.
 **/
public class Orangutan extends Animal{
	/**
	 * isDead
	 * <p>
	 *     Flag that tells if the orangutan is dead
	 * </p>
	 */
	private boolean isDead = false;

	/**
	 * getIsDead
	 * <p>
	 *     Gets the value of isDead flag
	 * </p>
	 * @return isDad flag
	 */
	public boolean getIsDead(){
		return isDead;
	}

	/**
	 * move(Tile)
	 * <p>
	 * When an Orangutan moves, this function will be called.
	 * If the move is not possible the Orangutan will release its chain.
	 * @param tile is the Tile, where the Orangutan should move.
	 */
	public void move(Tile tile) {
		
		boolean placeThing_res = tile.placeThing(this);

		if(prevAnimal != null)
		{
			if( placeThing_res && prevAnimal != null ) {
				prevAnimal.move(prevTile);
			} //Ha sikerült mozognia a lehetséges láncot húzza maga után
			else if(prevAnimal != null) {
				prevAnimal.unchain();
			} //Ha nem sikerült mozogni felbomlasztja a láncot
		}
		
		
	}
	
	/**
	 * kill()
	 * <p>
	 * When an Orangutan dies, this function will be called.
	 * The Orangutan will release its chain, then will reduce the count..
	 */
	public void kill(Controller c) {

		if(prevAnimal != null) prevAnimal.unchain();
		isOn.setContains(null);
		isOn = null;
		isDead = true;
		c.removeOrangutan(this);
	}

	/**
	 * connectChain
	 * <p>
	 *     Overrides base function
	 *     Connects an orangutan with another animal
	 * </p>
	 * @param  animal The animal, which joins to the chain.
	 */
	@Override
	public void connectChain(Animal animal) {
	    if(this.prevAnimal == null){
	        this.prevAnimal = animal;
	        animal.setNextAnimal(this);
        }
	    else{
	        this.prevAnimal.setNextAnimal(animal);
	        animal.setPrevAnimal(this.prevAnimal);
	        animal.setNextAnimal(this);
	        this.prevAnimal = animal;
        }
    }

	/**
	 * manualUnchain
	 * <p>
	 *     Provides opportunity to manually unchain pandas behind the orangutan
	 * </p>
	 */
	public void manualUnchain(){
		if(prevAnimal != null) {
			prevAnimal.unchain();
			prevAnimal = null;
		}

	}

	/**
	 * toString
	 * <p>
	 *     Overrides Object.toString for better readability
	 * </p>
	 * @return "o" string
	 */
	@Override
	public String toString(){
		return "o";
	}

	/**
	 * invokeDraw
	 * <p>
	 *     Implementation of IDrawable
	 * </p>
	 * @param g graphics object
	 */
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}
}
