

public abstract class Animal extends AThing {
	
	protected Animal prevAnimal;
	protected Animal nextAnimal;
	protected Tile prevTile;
	
	public abstract void move(Tile tile);
	public abstract void kill();
	
	public void connectChain() {}
	
	public void leaveTile(Tile t) {
		//megfelelő setterek ami átmozgatja az állatot (loseLife()- al együtt)
	}
	
	public void unchain() {}
	
	
	

}
