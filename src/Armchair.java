import java.util.ArrayList;

public class Armchair extends AThing implements IPandaEffective{

	private LazyPanda panda;
	
	public void effect()
	{
		notifyNeighbors();
	}
	
	public boolean isOccupied()
	{
		if(panda != null)
			return true;
		return false;
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
