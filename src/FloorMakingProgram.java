import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FloorMakingProgram
{

	public static void main(String[] args)
	{
		test4();
	}

	private static void test1()
	{
		Floor floor = new Floor();
		for(int i = 0; i < 9; i++){
			floor.addTile(new RegularTile());
		}
		floor.setTile(8, new BrokenTile(20));
		floor.setExit(new Exit(), 1);
		floor.addThing(new GameMachine(3), 3);

		Wardrobe w1 = null;
		Wardrobe w2 = new Wardrobe(w1);
		w1 = new Wardrobe(w2);

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

	private static void test4()
	{
		Floor floor = new Floor();
		for(int i = 0; i < 6; i++){
			floor.addTile(new RegularTile());
		}

		Wardrobe w1 = new Wardrobe(null);
		Wardrobe w2 = new Wardrobe(null);
		w1.setOutPoint(w2);
		w2.setOutPoint(w1);

		floor.addThing(w1, 1);
		floor.addThing(w2, 6);

		int[][] matrix =
		{
				{},
				{2},
				{1,3},
				{2,4},
				{3,5},
				{4,6},
				{5},
		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		try{
			FileOutputStream fos = new FileOutputStream("test4.flr");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}

		Floor Floor = null;
		try{
			FileInputStream fis = new FileInputStream("test4.flr");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Floor = (Floor)ois.readObject();
			ois.close();
			System.out.println(Floor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void test5()
	{
		Floor floor = new Floor();
		for(int i = 0; i < 5; i++)
		{
			floor.addTile(new RegularTile());
		}
		floor.addThing(new Armchair(), 3);

		int[][] matrix =
		{
				{},
				{2},
				{1,3},
				{2,4,5},
				{3},
				{3},
		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		try
		{
			FileOutputStream fos = new FileOutputStream("test5.flr");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Floor Floor = null;
		try
		{
			FileInputStream fis = new FileInputStream("test5.flr");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Floor = (Floor)ois.readObject();
			ois.close();
			System.out.println(Floor);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void test17()
	{
		Floor floor = new Floor();
		for(int i = 0; i < 4; i++)
		{
			floor.addTile(new RegularTile());
		}

		int[][] matrix =
		{
				{},
				{2,4},
				{1,3},
				{2},
				{1},
		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		try{
			FileOutputStream fos = new FileOutputStream("test17.flr");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}

		Floor Floor = null;
		try{
			FileInputStream fis = new FileInputStream("test17.flr");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Floor = (Floor)ois.readObject();
			ois.close();
			System.out.println(Floor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void test18()
	{
		Floor floor = new Floor();
		for(int i = 0; i < 5; i++)
		{
			floor.addTile(new RegularTile());
		}
		floor.setTile(2, new BrokenTile(20));

		int[][] matrix = {
				{},
				{2},
				{1,3},
				{2,4},
				{3,5},
				{4},

		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		try{
			FileOutputStream fos = new FileOutputStream("test18.flr");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}

		Floor Floor = null;
		try{
			FileInputStream fis = new FileInputStream("test18.flr");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Floor = (Floor)ois.readObject();
			ois.close();
			System.out.println(Floor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}