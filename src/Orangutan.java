import java.util.ArrayList;

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
		
		if( tile.placeThing(this) ) {
			prevAnimal.move(prevTile);
		} //Ha siker�lt mozognia a lehets�ges l�ncot h�zza maga ut�n
		else {
			
			prevAnimal.unchain();
		} //Ha nem siker�lt mozogni felbomlasztja a l�ncot
		
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
	}

	@Override
	public void connectChain(Animal animal) {

	}

	@Override
	public void notifyNeighbors() {
		ArrayList<Tile> neighbors = isOn.getNeighbors();
		for(Tile neighbor : neighbors)
		{
			if(neighbor.getContains() != null)
			{
				Main.printer.functionCall("nt", "placeThing");
				neighbor.placeThing(this);
				Main.printer.returnFromFunctionCall();
			}
		}
	}
}
