import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FloorMakingProgram {

	public static void main(String[] args){
		test1();
	}

	private static void test1() {
		Floor floor = new Floor();
		for(int i = 0; i < 9; i++){
			floor.addTile(new RegularTile());
		}
		floor.setTile(8, new BrokenTile(20));
		floor.setExit(new Exit(), 1);
		floor.addThing(new GameMachine(3), 3);

		Wardrobe w1 = null;//
		Wardrobe w2 = new Wardrobe(w1);
		w1 = new Wardrobe(w2);//

		floor.addThing(w1, 4);
		floor.addThing(new Armchair(), 5);
		floor.addThing(w2, 7);
		Entry entry = new Entry(floor.getTile(6));
		entry.addOrangutan(2);
		floor.setEntry(entry, 9);


		int[][] matrix = {
				{},			//0
				{2},		//1
				{1,3,8},	//2
				{2,4,8},	//3
				{3,5,8},	//4
				{4,6,8},	//5
				{5,7,9},	//6
				{6,8},		//7
				{2,3,4,5,7},//8
				{6}			//9
		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		try{
			FileOutputStream fos = new FileOutputStream("test1.flr");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}

		Floor Floor = null;
		try{
			FileInputStream fis = new FileInputStream("test1.flr");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Floor = (Floor)ois.readObject();
			ois.close();
			System.out.println(Floor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}