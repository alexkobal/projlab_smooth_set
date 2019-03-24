
/**
 * Panda
 * <p>
 * This is Panda which extends the animal class. It represents all Panda kind of objects on the map.
 **/
public class Panda extends Animal {
	
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

			if (nextAnimal == null) {
				//Megfelel� setterek + return, (rekurzi� v�ge)
				prevAnimal.setNextAnimal(null);
				prevAnimal = null;
				return;
			} else {//Rekurziv h�v�sok folytat�sa
				Main.printer.functionCall("prevAnimal", "unchain");
				prevAnimal.unchain();
				Main.printer.returnFromFunctionCall();
			}

			//megfelel� setter + return
			prevAnimal.setNextAnimal(null);
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
		if(prevAnimal == null && nextAnimal== null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * move(tile)
	 * <p>
	 * When a Panda wants to move to a tile this function will be called.
	 * @param  tile is the Tile where the Panda wants to move.
	 */
	public void move(Tile tile) {
		Main.printer.functionCall("tile", "placeThing", "this");
		boolean placeThing_res = tile.placeThing(this);
		Main.printer.returnFromFunctionCall();

		if( placeThing_res ) {
			if(nextAnimal != null){
				Main.printer.functionCall("nextAnimal", "move", "prevTile");
				nextAnimal.move(prevTile);
				Main.printer.returnFromFunctionCall();
			}
		}
	}
	
	/**
	 * kill
	 * <p>
	 * When a Panda dies this function will be called..
	 */
	public void kill() {
		unchain();
		//+Megfelel� setterek
		isOn.setContains(null);
		//Controller.removePanda(this) - ha majd lesz Controller
		isOn = null;
		
	}

	@Override
	public void connectChain(Animal animal) {

	}
}
