import java.io.Serializable;

/**
	 * AThing
	 * <p>
	 * This is an abstract class. It represents all of the animals and objects on the map. 
	 * This class has all of the collide (virtual) methods.
	 * isOn is the Tile where the thing is placed on.
**/
public abstract class AThing implements Serializable
{
    protected Tile isOn;

	public Tile getIsOn() {
		return isOn;
	}

	public void setIsOn(Tile isOn) {
		this.isOn = isOn;
	}

	/**
	 * HitBy Panda
	 * <p>
	 * This function is called when a Panda collides with the object (this object).
	 * @param  panda is the Thing which collided with this object.
	 */
    public boolean hitBy(Panda panda)
    {
        return false;
    }

    /**
	 * HitBy Orangutan
	 * <p>
	 * This function is called when a Orangutan collides with the object (this object).
	 * @param  orangutan is the Thing which collided with this object.
	 */
    public boolean hitBy(Orangutan orangutan)
    {
        return false;
    }

    /**
	 * HitBy VendingMachine
	 * <p>
	 * This function is called when a VendingMachine collides with the object (this object).
	 * @param  vm is the Thing which collided with this object.
	 */
    public boolean hitBy(VendingMachine vm)
    {
        return false;
    }
    
    /**
	 * HitBy GameMachine
	 * <p>
	 * This function is called when a GameMachine collides with the object (this object).
	 * @param  gm is the Thing which collided with this object.
	 */
    public boolean hitBy(GameMachine gm)
    {
        return false;
    }

    /**
	 * HitBy Armchair
	 * <p>
	 * This function is called when an Armchair collides with the object (this object).
	 * @param  armchair is the Thing which collided with this object.
	 */
    public boolean hitBy(Armchair armchair)
    {
        return false;
    }

	public void notifyNeighbors(){}

}
