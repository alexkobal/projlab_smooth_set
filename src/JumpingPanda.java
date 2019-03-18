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
		//Szerintem érdemes az unchaint tenni előre, mert ha meghalna a panda,
		//akkor is szét kell szednie a sort, amiben áll
		
		this.isOn.loseLife();					//Csökkenti az életét a csempének, mert ugrott rajta
		return true;							//Ha ugrik, akkor true
	}								

}
