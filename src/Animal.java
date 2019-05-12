/**
 * Animal
 * <p>
 *     This class extends AThing. It represents the animals of the game.
 *     This class holds all the virtual methods for the Orangutan and the Panda.
 * </p>
 */
public abstract class Animal extends AThing {
	protected String name;
	/**
	 * prevAnimal
	 * <p>
	 *     This is the animal behind this animal in the chain.
	 *     prevAnimal = null - if the animal is not in a chain.
	 * </p>
	 */
	protected Animal prevAnimal;

	/**
	 * nextAnimal
	 * <p>
	 *     This is the animal front of this animal in the chain.
	 *     nextAnimal = null - if the animal is not in a chain.
	 * </p>
	 */
	protected Animal nextAnimal;

	/**
	 * prevTile
	 * <p>
	 *     This is the tile, from the animal come.
	 * </p>
	 */
	protected Tile prevTile;

	/**
	 * setName
	 * <p>
	 *     Sets the name parameter
	 * </p>
	 * @param n name string
	 */
	public void setName(String n) {this.name = n;}

	/**
	 * setPrevAnimal
	 * <p>
	 *     Sets the animal that stands before the current
	 *     (closer to orangutan)
	 * </p>
	 * @param prevAnimal animal to be set a previous
	 */
	public void setPrevAnimal(Animal prevAnimal) {
		this.prevAnimal = prevAnimal;
	}

	/**
	 * setNextAnimal
	 * <p>
	 *     Sets the animal behind the current
	 *     (Further from orangutan)
	 * </p>
	 * @param nextAnimal animal to be set as next
	 */
	public void setNextAnimal(Animal nextAnimal) {
		this.nextAnimal = nextAnimal;
	}

	/**
	 * setPrevTile
	 * <p>
	 *     Sets the tile from where the animal came
	 * </p>
	 * @param prevTile previous tile
	 */
	public void setPrevTile(Tile prevTile)
	{
		this.prevTile = prevTile;
	}

	/**
	 * getPrevAnimal
	 * <p>
	 *     Gets the previous animal
	 *     (closer to orangutan)
	 * </p>
	 * @return reference to previous animal
	 */
	public Animal getPrevAnimal() {
		return prevAnimal;
	}

	/**
	 * getNextAnimal
	 * <p>
	 *     Gets the next animal
	 *     (further from orangutan)
	 * </p>
	 * @return reference to the next animal
	 */
	public Animal getNextAnimal() {
		return nextAnimal;
	}

	/**
	 * Animal move
	 * <p>
	 * This function is called when the animal moves to the next tile.
	 * @param  tile is the Tile where the animal goes.
	 */
	public abstract void move(Tile tile);

	/**
	 * Animal Kill
	 * <p>
	 * This function is called when the Brokentile brokes and the Animal falls with it.
	 */
	public abstract void kill(Controller c);

	/**
	 * Animal connect chain
	 * <p>
	 * This function is called when an animal joining to a chain, or adds an other animal, to its chain.
	 * @param  animal The animal, which joins to the chain.
	 */
	public abstract void connectChain(Animal animal);

	/**
	 * Animal leavesTile
	 * <p>
	 * This function is called when an Animal goes to another Tile.
	 * @param  t is the Tile where the Animal goes to.
	 */
	public void leaveTile(Tile t) {

		isOn.setContains(null);
		prevTile = isOn;
		isOn = t;
		t.setContains(this);
		t.loseLife();
	}

	/**
	 * Animal Unchain
	 * <p>
	 * This function is called when an Animal leaves the chain.
	 */
	public void unchain() {}

}