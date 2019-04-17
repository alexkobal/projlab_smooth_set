public class ProgramFloorGenerator {

	public static void main(String[] args){
		try {
			FloorGenerator fg = new FloorGenerator();
			fg.generateFloor(6, 0.5, System.out);
			System.in.read();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
