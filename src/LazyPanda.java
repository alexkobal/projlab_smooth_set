



public class LazyPanda extends Panda {
	private int sleepTime; //Jelenlegi alv�si id�
	private int pandasRegularSleepTime; //Pand�ra jellemz� alv�si id�
	
	public LazyPanda(int sleepTime) {
		pandasRegularSleepTime = sleepTime;
	}
	
	/**
	 * HitBy Armchair
	 * <p>
	 * When an Armchair does its effect, this panda gets tired and goes to sleep.
	 * It will step to the Tile of the Armchair and will sleep there.
	 * @param  ac is the Armchair which invites the Panda to sleep.
	 */
	
	public boolean hitBy(Armchair ac) {

		sleepTime = pandasRegularSleepTime;

		//sleepTime = ac.getSleep(); //A fotelre jellemz� alv�si id� - ha �gy j�tszuk
		
		Main.printer.functionCall("lp", "leaveTile", "ac.getIsOn");
		leaveTile(ac.getIsOn());
		Main.printer.returnFromFunctionCall();
		return false;
	}
	
	/**
	 * move(Tile)
	 * <p>
	 * When a LazyPanda moves, first of all it checks its sleepTime attribute if it is able to move.
	 * If it is 0, it can move, otherwise not.
	 * @param  tile is the Tile where the Panda should move.
	 */
	
	public void move(Tile tile) {
		if(sleepTime == 0) {
			super.move(tile);
		}
		else {
			sleepTime -= 1;
		}
	}
	
	/**
	 * leaveTile(Tile)
	 * <p>
	 * If the move is possible, this function will be called.
	 * @param  t is the Tile where the Panda is able to move.
	 */
	
	public void leaveTile(Tile t) {
		//Megfelel� setterek de �gy, hogy a fotelre �ll�tja saj�t mag�t
	}
	
	
	/**
	 * HitBy Orangutan
	 * <p>
	 * When an Orangutan hits a LazyPanda, the Panda will check if it is not sleeping.
	 * If it is sleeping, it wont connect to the chain.
	 * @param  or is the Orangutan which hits the Panda..
	 */
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
