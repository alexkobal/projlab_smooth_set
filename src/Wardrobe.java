public class Wardrobe extends AThing{

    private Wardrobe outPoint;

    public void pushOut(Animal animal){

    }

    @Override
    public boolean hitBy(Panda panda){

        return false;
    }

    @Override
    public boolean hitBy(Orangutan orangutan) {

        return false;
    }
}
