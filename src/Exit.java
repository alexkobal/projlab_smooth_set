import java.util.ArrayList;

/**
 * Exit
 * <p>
 *     This class extends AThing. It represents the exit point of the floor.
 * </p>
 */
public class Exit extends AThing {
    private ArrayList<Orangutan> orangutansToPush = new ArrayList<>();

    /**
     * prevPanda
     * <p>
     *     This field is a reference for next Panda that should go through the exit.
     *     It matters when an Orangutan leads a chain of Pandas to the exit.
     * </p>
     */
    private Panda prevPanda;

    public ArrayList<Orangutan> getOrangutansToPush() {
        ArrayList<Orangutan> temp = new ArrayList<Orangutan>(orangutansToPush);
        //System.out.println("temp lista mérete:" + temp.size());
        orangutansToPush.clear();
        return temp;
    }

    /*public int orangutansToPushSize(){

        return orangutansToPush.size();
    }*/

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
        if(prevPanda == null){
            orangutan.prevTile = orangutan.isOn;
            orangutan.isOn.setContains(null);
            orangutan.setIsOn(Tile.ctrl.floor.getEntry().getIsOn());
            orangutansToPush.add(orangutan);
            if(orangutansToPush.size() > 0){
                //System.out.println("Exit-orangutanstopush > 0");
            }
            if(orangutan.getPrevAnimal() != null){
                prevPanda = (Panda) orangutan.getPrevAnimal();
            }
            return true;

        }
        else{
            return false;
        }


        /*if(prevPanda == null) {
            prevPanda = (Panda) orangutan.getPrevAnimal();
            prevPanda.setNextAnimal(null);
            orangutan.prevAnimal.move(orangutan.isOn);
            orangutan.setPrevAnimal(null);
            orangutan.isOn.setContains(null);
            orangutan.setIsOn(Tile.ctrl.floor.getEntry().getIsOn());
            orangutansToPush.add(orangutan);
            return true;
        }
        return false;*/
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
        if(prevPanda != null && panda == prevPanda){
            panda.prevTile = panda.isOn;
            panda.isOn.setContains(null);
            panda.isOn = this.isOn;
            prevPanda = (Panda)panda.getPrevAnimal();
            panda.shouldIKillMyself = true;
            //panda.kill(Tile.ctrl);
            return true;
        }
        else{
            return false;
        }

        /*prevPanda = (Panda) panda.getPrevAnimal();
        if(prevPanda != null){
            prevPanda.setNextAnimal(null);
            panda.setPrevAnimal(null);
        }
        panda.kill(Tile.ctrl);
        return true;*/
    }

    /**
     * nextTurn
     * <p>
     *     This function is called on every turn, if there is a panda to receive,
     *     than it tries to move it through the exit.
     * </p>
     */
    public void nextTurn(){
        if(prevPanda != null){
            prevPanda.move(isOn);
        }
    }

    @Override
    public String toString(){
        return "X";
    }

    @Override
    public void invokeDraw() {
        View.getInstance().draw(this);
    }
}
