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
			isOn.notifyNeighbors();
			beepTime = baseBeepTime;
		}else{
			beepTime--;
		}
	}
}
