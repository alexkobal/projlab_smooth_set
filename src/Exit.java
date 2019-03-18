public class Exit extends AThing {

    private Panda nextPanda;

    @Override
    public boolean hitBy(Orangutan orangutan) {
        nextPanda = (Panda) orangutan.getNextAnimal();
        nextPanda.setPrevAnimal(null);
        orangutan = null;
        return true;
    }
    public boolean hitBy(Panda panda){
        if(!panda.isInChain()){
            return false;
        }
        nextPanda = (Panda) panda.getNextAnimal();
        nextPanda.setPrevAnimal(null);
        panda = null;
        return true;
    }

    public void nextTurn(){
        if(nextPanda != null){
            nextPanda.move(isOn);
        }
    }
}
