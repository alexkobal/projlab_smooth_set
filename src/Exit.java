/**
 * Exit
 * <p>
 *     This class extends AThing. It represents the exit point of the floor.
 * </p>
 */
public class Exit extends AThing {
    /**
     * nextPanda
     * <p>
     *     This field is a reference for next Panda that should go through the exit.
     *     It matters when an Orangutan leads a chain of Pandas to the exit.
     * </p>
     */
    private Panda nextPanda;
    /**
     * hitBy Orangutan
     * <p>
     *     This function overrides the base function and it is called when an Orangutan tries to exit.
     * </p>
     * @param  orangutan is the Thing which collided with this object.
     * @return returns true if the orangutan succeeded to exit.
     */
    @Override
    public boolean hitBy(Orangutan orangutan) {
        if(nextPanda == null) {
            nextPanda = (Panda) orangutan.getNextAnimal();
            nextPanda.setPrevAnimal(null);
            orangutan = null;
            return true;
        }
        return false;
    }
    /**
     * hitBy Panda
     * <p>
     *     This function overrides the base function and it is called when a chain
     *     of Pandas is going through the exit.
     * </p>
     * @param  panda is the Thing which collided with this object.
     * @return returns false, if Panda isn't in chain, and true if Panda succeeded to exit
     */
    public boolean hitBy(Panda panda){
        if(!panda.isInChain()){
            return false;
        }
        nextPanda = (Panda) panda.getNextAnimal();
        nextPanda.setPrevAnimal(null);
        panda = null;
        return true;
    }

    /**
     * nextTurn
     * <p>
     *     This function is called on every turn, if there is a panda to receive,
     *     than it tries to move it through the exit.
     * </p>
     */
    public void nextTurn(){
        if(nextPanda != null){
            nextPanda.move(isOn);
        }
    }
}
