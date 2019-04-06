public class ProgramFloorGenerator {

	public static void main(String[] args){
		try {
			FloorGenerator fg = new FloorGenerator();
			fg.generateFloor(10, 0.6, System.out);
			System.in.read();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
