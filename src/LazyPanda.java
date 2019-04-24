/**
 * Lazy Panda
 * <p>
 * This special panda is sits down if an Armchair nearby notifies about itself.
 * The panda is sits in the armchair and sleeps in it.
 **/
public class LazyPanda extends Panda {

	/**
	 * sleepTime
	 * <p>
	 * This is the variable which defines how long sleeps the panda in an armchair.
	 */
	private int sleepTime; //Jelenlegi alv�si id�
	private int pandasRegularSleepTime; //Pand�ra jellemz� alv�si id�

	/**
	 * Lazy Panda contstructor
	 * <p>
	 *     This is the constructor of the Lazy Panda class.
	 * </p>
	 * @param n This is the sleep time that going to be set when a Panda sits into an Armchair.
	 */
	public LazyPanda(int n) {
		pandasRegularSleepTime = n;
	}
	
	/**
	 * HitBy Armchair
	 * <p>
	 * When an Armchair does its effect, this panda gets tired and goes to sleep.
	 * It will step to the Tile of the Armchair and will sleep there.
	 * @param  ac is the Armchair which invites the Panda to sleep.
	 */
	public boolean hitBy(Armchair ac) {
		if(!ac.isOccupied())
		{
			sleepTime = pandasRegularSleepTime;
			ac.setPanda(this);
			prevTile = isOn;
			isOn = ac.getIsOn();
			prevTile.setContains(null);


			if(nextAnimal != null) {
				this.nextAnimal.setPrevAnimal(null);
				this.nextAnimal = null;
			}
			this.unchain();
			return false;
		}

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

	public int getSleepTime() {
		return sleepTime;
	}

	@Override
	public String toString(){
		return "l";
	}
}
