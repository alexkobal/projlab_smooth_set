public class GameMachine extends AThing implements IPandaEffective{
	
	private int tinkleTime;
	
	public GameMachine(int t) {	tinkleTime = t;	}
	
	public void effect()
	{
		isOn.notifyNeighbours();
	}
}
