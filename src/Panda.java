import java.awt.*;

/**
 * Panda
 * <p>
 * This is Panda which extends the animal class. It represents all Panda kind of objects on the map.
 **/
public abstract class Panda extends Animal {


	public boolean shouldIKillMyself = false;
	/**
	 * HitBy Orangutan
	 * <p>n Orangutan hits a Panda this function will be called.
	 * If the Panda was in another chain, first of all it will release that chain, and will join the new Orangutans chain.
	 * @param  or is the Orangutan which hits the Panda.
	 */
	public boolean hitBy(Orangutan or) {
		
		//Mögötte álló lánc felbontása ha van
		if(prevAnimal != null) {
			prevAnimal.unchain();
		}

		Tile orTile_temp = or.getIsOn(); //Temporális változó
		or.setPrevTile(or.getIsOn());
		or.setIsOn(this.isOn);

		isOn.setContains(or);

		prevTile = isOn;
		isOn = orTile_temp;
		orTile_temp.setContains(this);

		if(nextAnimal != null) {
			this.nextAnimal.setPrevAnimal(null);    //Qrva ronda, de mivel az unchain úgy lett specifikálva, hogy csak a mögötte lévő láncot bontja fel, inkább nem írtam át
			this.nextAnimal = null;
		}

		or.connectChain(this);

		return true;
	}
	
	/**
	 * unchain()
	 * <p>
	 * When a Panda has to release its chain, this function will be called.
	 * The function will recursively find the end of the chain, then will release the hands one by one.
	 */
	public void unchain() {
		if( isInChain() ) {
			if(prevAnimal != null) {
				prevAnimal.unchain();
			}

			nextAnimal = null;
			prevAnimal = null;
			return;
		}
	}
	
	/**
	 * isInChain()
	 * <p>
	 * Returns whether a Panda is in a chain or not. 
	 */
	public boolean isInChain() {
		if(nextAnimal != null || prevAnimal != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * move(tile)
	 * <p>
	 * When a Panda wants to move to a tile this function will be called.
	 * @param  tile is the Tile where the Panda wants to move.
	 */
	public void move(Tile tile) {
		boolean placeThing_res = tile.placeThing(this);

		if( placeThing_res ) {
			if(prevAnimal != null){
				prevAnimal.move(prevTile);
			}
		}

		if(shouldIKillMyself){
			//System.out.println("a panda has been removed");
			Controller.getInstance().removePanda(this);
		}

	}
	
	/**
	 * kill
	 * <p>
	 * When a Panda dies this function will be called..
	 */
	public void kill(Controller c) {
		//System.out.println("A panda commited suicide");
		unchain();
		isOn.setContains(null);
		isOn = null;
		c.removePanda(this);
	}

	/**
	 * connectChain
	 * <p>
	 *     Overrides base function
	 *     Does nothing, cause panda cant connect other animals
	 * </p>
	 * @param  animal The animal, which joins to the chain.
	 */
	@Override
	public void connectChain(Animal animal) {}

	/**
	 * invokeDraw
	 * <p>
	 *     Implements IDrawable
	 * </p>
	 * @param g graphics object
	 */
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}
}
