public class GameMachine extends AThing implements IPandaEffective{

	private int baseTinkleTime;
	private int tinkleTime;
	
	public GameMachine(int t) {
		tinkleTime = t;
		baseTinkleTime = t;
	}
	
	public void effect()
	{
		if(tinkleTime <= 0) {
			isOn.notifyNeighbors();
			tinkleTime = baseTinkleTime;
		}else{
			tinkleTime--;
		}
	}
}
