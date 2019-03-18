public class Armchair extends AThing implements IPandaEffective{

	private LazyPanda panda;
	
	public void effect()
	{
		isOn.notifyNeighbors();
	}
	
	public boolean isOccupied()
	{
		if(panda != null)
			return true;
		return false;
	}
}
