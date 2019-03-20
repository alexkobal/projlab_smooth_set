import java.util.ArrayList;

public class VendingMachine extends AThing implements IPandaEffective{

	private int beepTime;
	private int baseBeepTime;
	
	public VendingMachine(int t) {
		beepTime = t;
		baseBeepTime = t;
	}
	
	public void effect()
	{
		if(beepTime <= 0) {
			notifyNeighbors();
			beepTime = baseBeepTime;
		}else{
			beepTime--;
		}
	}

	@Override
	public void notifyNeighbors() {
		ArrayList<Tile> neighbors = isOn.getNeighbors();
		for(Tile neighbor : neighbors)
		{
			if(neighbor.getContains() != null)
			{
				Main.printer.functionCall("nt", "placeThing");
				neighbor.placeThing(this);
				Main.printer.returnFromFunctionCall();
			}
		}
	}
}
