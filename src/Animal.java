import proj_lab.AThing;
import proj_lab.Animal;
import proj_lab.Tile;

public abstract class Animal extends AThing {
	
	protected Animal prevAnimal;
	protected Animal nextAnimal;
	protected Tile prevTile;
	
	public abstract void move(Tile tile);
	public abstract void kill();
	
	public void connectChain() {}
	
	public void leaveTile(Tile t) {
		//megfelel� setterek ami �tmozgatja az �llatot (loseLife()- al egy�tt)
	}
	
	
	

}
