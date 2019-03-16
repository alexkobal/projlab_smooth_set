public class Entry {

    private Tile entryTile;
    private int orangutansToPush;

    public void pushOrangutan(){
        Orangutan orangutan = new Orangutan();
        orangutan.move(entryTile);
    }

    public void addOrangutan(int n){
        orangutansToPush += n;
    }

    public void nextTurn(){
        if(orangutansToPush > 0){
            pushOrangutan();
        }
    }
}
