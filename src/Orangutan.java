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
		/*
		Main.printer.functionCall("tile", "placeThing", "this");
		boolean placeThing_res = tile.placeThing(this);
		Main.printer.returnFromFunctionCall();
		
		if( placeThing_res ) {
			Main.printer.functionCall("prevAnimal", "move", "prevTile");
			prevAnimal.move(prevTile);
			Main.printer.returnFromFunctionCall();
		} //Ha siker�lt mozognia a lehets�ges l�ncot h�zza maga ut�n
		else {
			Main.printer.functionCall("prevAnimal", "unchain");
			prevAnimal.unchain();
			Main.printer.returnFromFunctionCall();
		} //Ha nem siker�lt mozogni felbomlasztja a l�ncot
		*/
		boolean placeThing_res = tile.placeThing(this);
		if(placeThing_res){
			prevTile = tile;
			tile.loseLife();
			leaveTile(tile);
			if(nextAnimal != null) {
				nextAnimal.move(prevTile);
			}
		}else{
			if(nextAnimal != null){
				nextAnimal.unchain();
			}
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
	}

	@Override
	public void connectChain(Animal animal) {

	}
}
