import java.util.ArrayList;

/*Main.printer.functionCall("nt", "placeThing");
neighbor.placeThing(this);
Main.printer.returnFromFunctionCall();*/

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
		
		Main.printer.functionCall("tile", "placeThing", "this");
		boolean placeThing_res = tile.placeThing(this);
		Main.printer.returnFromFunctionCall();
		
		if(nextAnimal != null)
		{
			if( placeThing_res && nextAnimal != null ) {
				Main.printer.functionCall("nextAnimal", "move", "prevTile");
				nextAnimal.move(prevTile);
				Main.printer.returnFromFunctionCall();
			} //Ha sikerült mozognia a lehetséges láncot húzza maga után
			else if(nextAnimal != null) {
				Main.printer.functionCall("nextAnimal", "unchain");
				nextAnimal.unchain();
				Main.printer.returnFromFunctionCall();
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
		
		Main.printer.functionCall("prevAnimal", "unchain");
		prevAnimal.unchain();
		Main.printer.returnFromFunctionCall();
		count--;
		//Plusz a sz�ks�ges setterek
		isOn.setContains(null);
		isOn = null;
	}

	@Override
	public void connectChain(Animal animal) {

	}
}
