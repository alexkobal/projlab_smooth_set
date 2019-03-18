

public class LazyPanda extends Panda {
	private int sleepTime; //Jelenlegi alv�si id�
	private final int pandasRegularSleepTime; //Pand�ra jellemz� alv�si id�
	
	public LazyPanda(int n) {
		pandasRegularSleepTime = n;
	}
	
	public boolean hitBy(Armchair ac) {
		
		sleepTime = pandasRegularSleepTime;
		
		//sleepTime = ac.getSleep(); //A fotelre jellemz� alv�si id� - ha �gy j�tszuk
		leaveTile( ac.getIsOn() );
	}
	
	public void move(Tile tile) {
		if(sleepTime == 0) {
			super.move(tile);
		}
		else {
			sleepTime -= 1;
		}
	}
	
	public void leaveTile(Tile t) {
		//Megfelel� setterek de �gy, hogy a fotelre �ll�tja saj�t mag�t
	}
	
	public boolean hitBy(Orangutan or) {
		if(sleepTime == 0) {
			super.hitBy(or);
			return true;
		}
		else {
			return false;
		}
	}

}
