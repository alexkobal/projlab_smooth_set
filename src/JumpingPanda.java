/**
	 * Jumping Panda
	 * <p>
	 * This special panda jumps if a VendingMachine nearby whistle.
	 * When it jumps, it loses hold of the next and the previous Pandas in the line.  
**/
public class JumpingPanda extends Panda
{
	
	/**
	 * HitBy VendingMachine
	 * <p>
	 * When a VendingMachine does its effect, this panda jumps and loses hold of the pandas in the line.
	 * The jump breaks the life of the Tile under the Panda.
	 * @param  vm is the (near) VendingMachine which is scares the Panda.
	 */
	@Override
	public boolean hitBy(VendingMachine vm)
	{
		
		this.unchain();
		if(nextAnimal != null) {
			this.nextAnimal.setPrevAnimal(null);
			this.nextAnimal = null;
		}
		
		this.isOn.loseLife();
		return true;
	}

	@Override
	public String toString(){
		return "j";
	}
}
