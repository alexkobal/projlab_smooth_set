public class Animal extends AThing{

    protected Animal nextAnimal;
    protected Animal prevAnimal;
    protected int valami;

    public Animal getNextAnimal(){
        return nextAnimal;
    }
    public void setNextAnimal(Animal animal){
        nextAnimal = animal;
    }
    public Animal getPrevAnimal(){
        return prevAnimal;
    }
    public void setPrevAnimal(Animal animal){
        prevAnimal = animal;
    }

    public void move(Tile tile){

    }


}
