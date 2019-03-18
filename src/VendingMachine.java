public class VendingMachine extends AThing implements IPandaEffective{

	private int beepTime;
	
	public VendingMachine(int t) { beepTime = t; }
	
	public void effect()
	{
		isOn.notifyNeighbours();
	}
}
