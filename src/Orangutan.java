

public class Orangutan extends Animal{
	
	private static int count;
	
	public void move(Tile tile) {
		
		if( tile.placeThing(this) ) {
			prevAnimal.move(prevTile);
		} //Ha siker�lt mozognia a lehets�ges l�ncot h�zza maga ut�n
		else {
			
			prevAnimal.unchain();
		} //Ha nem siker�lt mozogni felbomlasztja a l�ncot
		
	}
	
	public void kill() {
		
		prevAnimal.unchain();
		count--;
		//Plusz a sz�ks�ges setterek
	}
	
	public void connectChain() {
		//nem �rtem mit csin�l
	}

}
