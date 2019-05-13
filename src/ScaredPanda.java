/**
	 * Scared Panda
	 * <p>
	 * This special panda gets scared if a GameMachine nearby ringing.
	 * When it scared, it loses hold of the next and the previous Pandas in the line.  
**/
public class ScaredPanda extends Panda
{

	/**
	 * HitBy GameMachine
	 * <p>
	 * When a GameMachine does its effect, this panda loses hold of the pandas in the line.
	 * @param  gm is the (near) GameMachine which is scare the Panda.
	 */
	@Override
	public boolean hitBy(GameMachine gm)
	{
		if(nextAnimal != null) {
			this.nextAnimal.setPrevAnimal(null);
			this.nextAnimal = null;
		}
		unchain();
		return true;
	}

	/**
	 * toString
	 * <p>
	 *     Overrides Object.toString() in case of readability
	 * </p>
	 * @return "s" string
	 */
	@Override
	public String toString(){
		return "s";
	}
	
}