

public abstract class Animal extends AThing {
	
	protected Animal prevAnimal;
	protected Animal nextAnimal;
	protected Tile prevTile;

	public void setPrevAnimal(Animal prevAnimal) {
		this.prevAnimal = prevAnimal;
	}

	public void setNextAnimal(Animal nextAnimal) {
		this.nextAnimal = nextAnimal;
	}

	public Animal getPrevAnimal() {
		return prevAnimal;
	}

	public Animal getNextAnimal() {
		return nextAnimal;
	}

	public abstract void move(Tile tile);
	public abstract void kill();
	
	public abstract void connectChain(Animal animal);
	
	public void leaveTile(Tile t) {
		//megfelelő setterek ami átmozgatja az állatot (loseLife()- al együtt)
	}
	
	public void unchain() {}
	
	
	
	
	

}
