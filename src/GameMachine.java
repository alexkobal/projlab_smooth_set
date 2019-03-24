import java.util.ArrayList;
import java.util.HashSet;

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
			notifyNeighbors();
			tinkleTime = baseTinkleTime;
		}else{
			tinkleTime--;
		}
	}

	@Override
	public void notifyNeighbors() {
		HashSet<Tile> neighbors = isOn.getNeighbors();
		for(Tile neighbor : neighbors)
		{
			if(neighbor.getContains() != null)
			{
				Main.printer.functionCall("nt", "placeThing", "gm");
				neighbor.placeThing(this);
				Main.printer.returnFromFunctionCall();
			}
		}
	}
}
