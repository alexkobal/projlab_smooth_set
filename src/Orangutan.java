
/**
 * Orangutan
 * <p>
 * This is Orangutan which extends the animal class. It represents the Orangutan kind of objects on the map.
 **/
public class Orangutan extends Animal{
	
	private static int count;
	
	/**
	 * move(Tile)
	 * <p>
	 * When an Orangutan moves, this function will be called.
	 * If the move is not possible the Orangutan will release its chain.
	 * @param tile is the Tile, where the Orangutan should move.
	 */
	public void move(Tile tile) {
		
		boolean placeThing_res = tile.placeThing(this);

		if(nextAnimal != null)
		{
			if( placeThing_res && nextAnimal != null ) {
				nextAnimal.move(prevTile);
			} //Ha sikerült mozognia a lehetséges láncot húzza maga után
			else if(nextAnimal != null) {
				nextAnimal.unchain();
			} //Ha nem sikerült mozogni felbomlasztja a láncot
		}
		
		
	}
	
	/**
	 * kill()
	 * <p>
	 * When an Orangutan dies, this function will be called.
	 * The Orangutan will release its chain, then will reduce the count..
	 */
	public void kill() {
		
		prevAnimal.unchain();
		count--;
		//Plusz a sz�ks�ges setterek
		isOn.setContains(null);
		isOn = null;
	}

	@Override
	public void connectChain(Animal animal) { }

	@Override
	public String toString(){
		return "o";
	}
}
