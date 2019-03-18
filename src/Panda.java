

public class Panda extends Animal {
	
	
	public boolean hitBy(Orangutan or) {
		
		//M�g�tte �ll� l�nc felbont�sa ha van
		if(prevAnimal != null) {
			prevAnimal.unchain();
		}
		
		//Megfelel� setterek
		return true;
	}
	
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
	
	public boolean isInChain() {
		if(prevAnimal == null && nextAnimal== null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void move(Tile tile) {
		if( tile.placeThing(this) ) {
			tile.loseLife(); //Ha siker�l l�pnie, levon egyet a csempe �let�b�l ahov� l�pett
		}
	}
	
	public void kill() {
		unchain();
		//+Megfelel� setterek
		
	}
	
	public void connectChain() {
		//m�g mindig nem tudom mit csin�l
	}
}
