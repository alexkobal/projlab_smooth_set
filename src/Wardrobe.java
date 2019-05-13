import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;

/**
 * Wardrobe
 * <p>
 *     This class extends AThing. It represents a Wardrobe object witch is
 *     used like a portal to another Wardrobe.
 * </p>
 */
public class Wardrobe extends AThing{

    /**
     * Wardrobe outPoint
     * <p>
     *     Constructor for class Wardrobe
     * </p>
     * @param outPoint Wardrobe to be set as outPoint
     */
    public Wardrobe(Wardrobe outPoint){
        this.outPoint = outPoint;
    }

    /**
     * outPoint
     * <p>
     *     This field is a reference to the Wardrobe where the Animal will come out
     *     if it uses this Wardrobe.
     * </p>
     */
    private Wardrobe outPoint;

    /**
     * setOutPoint
     * <p>
     *     Sets the wardrobe where the current wardrobe lead
     * </p>
     * @param wd out point wardrobe
     */
    public void setOutPoint(Wardrobe wd) {
    	outPoint = wd;
    }

    /**
     * pushOut Animal
     * <p>
     *     This function is used to place the animal out of the end point Wardrobe.
     * </p>
     * @param animal This is the animal that is going through the Wardrobe.
     */
    public boolean pushOut(Animal animal){
        ArrayList<Tile> neighbors = isOn.getNeighbors();
        for(Tile tile : neighbors){
            if(tile.getContains() == null){
                animal.leaveTile(tile);
                return true;
            }
        }
        return false;
    }

    /**
     * hitBy Panda
     * <p>
     *     This function overrides the base function. It teleports the Panda to
     *     the out point Wardrobe.
     * </p>
     * @param  panda is the Thing which collided with this object.
     * @return returns true if the teleportation succeeded.
     */
    @Override
    public boolean hitBy(Panda panda){
        outPoint.pushOut(panda);
        return true;
    }

    /**
     * hitBy Orangutan
     * <p>
     *     This function overrides the base function. It teleports the Orangutan to
     *     the out point Wardrobe.
     * </p>
     * @param  orangutan is the Thing which collided with this object.
     * @return returns true if the teleportation succeeded.
     */
    @Override
    public boolean hitBy(Orangutan orangutan) {
        outPoint.pushOut(orangutan);
        return true;
    }

    /**
     * toString
     * <p>
     *     Overrides the Object.toString in case of better readability
     * </p>
     * @return "w" string
     */
    @Override
    public String toString(){
        return "w";
    }

    /**
     * invokeDraw
     * <p>
     *     Implementation of IDrawable interface
     * </p>
     * @param g graphics object
     */
    @Override
    public void invokeDraw(Graphics g) {
        View.getInstance().draw(this, g);
    }
}
