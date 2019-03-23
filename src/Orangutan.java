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

		boolean placeThing_res = tile.placeThing(this);
		if(placeThing_res){
			prevTile = isOn;
			tile.loseLife();
			leaveTile(isOn);
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
