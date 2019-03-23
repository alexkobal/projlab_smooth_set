import java.util.ArrayList;

public class Panda extends Animal {
	
	/**
	 * HitBy Orangutan
	 * <p>n Orangutan hits a Panda this function will be called.
	 * If the Panda was in another chain, first of all it will release that chain, and will join the new Orangutans chain.
	 * @param  or is the Orangutan which hits the Panda.
	 */
	
	
	public boolean hitBy(Orangutan or) {
		
		//M�g�tte �ll� l�nc felbont�sa ha van
		if(prevAnimal != null) {
			prevAnimal.unchain();
		}
		
		//Megfelel� setterek
		return true;
	}
	
	/**
	 * unchain()
	 * <p>
	 * When a Panda has to release its chain, this function will be called.
	 * The function will recursively find the end of the chain, then will release the hands one by one.
	 */
	
	public void unchain() {
		
		if(prevAnimal == null) {
			//Megfelel� setterek + return, (rekurzi� v�ge)
			return;
		}
		else {//Rekurziv h�v�sok folytat�sa
			prevAnimal.unchain();
		}
		
		//megfelel� setter + return
		return;
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
		boolean placeThing_res = tile.placeThing(this);
		if (placeThing_res) {
			prevTile = isOn;
			tile.loseLife();
			leaveTile(isOn);
			if (nextAnimal != null) {
				nextAnimal.move(prevTile);
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
		
	}

	@Override
	public void connectChain(Animal animal) {

	}
}
