import java.util.ArrayList;
import java.util.Random;

public class Wardrobe extends AThing{

    private Wardrobe outPoint;

    public void pushOut(Animal animal){
        ArrayList<Tile> neighbors = isOn.getNeighbors();
        Random random = new Random();
        animal.move(neighbors.get(random.nextInt() % neighbors.size()));
        // Nem feltetlen kerul at az allat a kovetkezo mezore.
    }

    @Override
    public boolean hitBy(Panda panda){
        outPoint.pushOut(panda);
        return true;
    }

    @Override
    public boolean hitBy(Orangutan orangutan) {
        outPoint.pushOut(orangutan);
        return true;
    }
}
