/**
 * Animal
 * <p>
 *     This class extends AThing. It represents the animals of the game.
 *     This class holds all the virtual methods for the Orangutan and the Panda.
 * </p>
 */
public abstract class Animal extends AThing {
	protected String name; //////////////////////////////////////////////////KONZULTÁLNI!!
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

	public void setName(String n) {this.name = n;}

	public String getName() {return this.name;}

	public void setPrevAnimal(Animal prevAnimal) {
		this.prevAnimal = prevAnimal;
	}

	public void setNextAnimal(Animal nextAnimal) {
		this.nextAnimal = nextAnimal;
	}

	public void setPrevTile(Tile prevTile)
	{
		this.prevTile = prevTile;
	}

	public Animal getPrevAnimal() {
		return prevAnimal;
	}

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
	public abstract void kill();

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
		//megfelelő setterek ami átmozgatja az állatot (loseLife()- al együtt)
		t.setContains(this);
		prevTile = isOn;
		isOn = t;
		prevTile.setContains(null);
		t.loseLife();
	}

	/**
	 * Animal Unchain
	 * <p>
	 * This function is called when an Animal leaves the chain.
	 */
	public void unchain() {}

}