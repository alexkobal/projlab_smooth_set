public class Panda extends Animal{

    public boolean isInChain(){
        if(prevAnimal != null){
            return true;
        }
        return false;
    }
}
