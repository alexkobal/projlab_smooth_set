import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FloorMakingProgram
{

	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
		test10();
		test11();
		test12();
		test13();
		test14();
		test15();
		test16();
		test17();
		test18();
	}


	//Program starts and makes a map
	private static void test1() {
		//Generating floor
		Floor floor = new Floor();
		for(int i = 0; i < 9; i++){
			floor.addTile(new RegularTile());
		}
		floor.setTile(8, new BrokenTile(20));
		//Adding things to the floor
		floor.setExit(new Exit(), 1);
		floor.addThing(new GameMachine(3), 3);
		Wardrobe w1 = new Wardrobe();
		Wardrobe w2 = new Wardrobe();
		w1.setOutPoint(w2);
		w2.setOutPoint(w1);
		floor.addThing(w1, 4);
		floor.addThing(new Armchair(), 5);
		floor.addThing(w2, 7);
		Entry entry = new Entry(floor.getTile(6));
		entry.addOrangutan(2);
		floor.setEntry(entry, 9);
		//Setting neighbors
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
		//Serialization
		String filename = "test1.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Entry generates two orangutans and player steps with them
	private static void test2(){
		//Generating floor
		Floor floor = new Floor();
		for(int i = 0; i < 5; i ++){
			floor.addTile(new RegularTile());
		}
		//Setting neighbors
		int[][] matrix = {
				{},		//0
				{2},	//1
				{1,3},	//2
				{2,4},	//3
				{3},	//4
				{}		//5
		};
		floor.setNeighbors(matrix);
		//Creating entry point;
		Entry entry = new Entry(floor.getTile(2));
		entry.addOrangutan(2);
		floor.setEntry(entry, 1);
		//Serialization
		String filename = "test2.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Two orangutans wandering on the floor and
	//suddenly one orangutan sees a panda and hits it.
	private static void test3(){
		//Generating floor
		Floor floor = new Floor();
		for(int i = 0; i < 6; i++){
			floor.addTile(new RegularTile());
		}
		//Setting neighbors
		int[][] matrix = {
				{},		//0
				{2},	//1
				{1,3},	//2
				{2,4},	//3
				{3,5},	//4
				{4,6},	//5
				{5}		//6
		};
		floor.setNeighbors(matrix);
		//Serialization
		String filename = "test3.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Orangutan with Panda chain goes through a Wardrobe
	private static void test4(){
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
		
		String filename = "test4.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Armchair notifies some randomly stepping pandas K.Peti
	private static void test5() {
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
		String filename = "test5.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Lonely Orangutan walking around a broken tile and breaks it, then the game ends M.M
    private static void test6(){
        Floor floor = new Floor();
        for(int i = 0; i < 3; i++)
        {
            floor.addTile(new RegularTile());
        }
        floor.setTile(2, new BrokenTile(4)); //Csak tesztelés 4

        int[][] matrix =
                {
                        {},
                        {2},
                        {1},
						{}
                };

        floor.setNeighbors(matrix);

        String filename = "test6.flr";
        Floor.serialise(floor, filename);
        System.out.println(Floor.deserialise(filename));
    }

	//GameMachine scares a panda M.M
    private static void test7(){
        Floor floor = new Floor();
        for(int i = 0; i < 7; i++){
            floor.addTile(new RegularTile());
        }

        floor.addThing(new GameMachine(3), 7);

        int[][] matrix =
                {
                        {},
                        {2},
                        {1, 3},
                        {2, 4},
                        {3, 5, 7},
                        {4, 6},
                        {5},
                        {4},
                };

        floor.setNeighbors(matrix);

        String filename = "test7.flr";
        Floor.serialise(floor, filename);
        System.out.println(Floor.deserialise(filename));
    }

    //Orangutan steals Pandas from an Orangutan without Panda- chain M.M
    private static void test8(){
        Floor floor = new Floor();
        for(int i = 0; i < 8; i++)
        {
            floor.addTile(new RegularTile());
        }

        int[][] matrix =
                {
                        {},
                        {2},
                        {1, 5},
                        {4},
                        {3, 5},
                        {2, 6, 7},
                        {5},
                        {5, 8},
                        {7}
                };

        floor.setNeighbors(matrix);

        String filename = "test8.flr";
        Floor.serialise(floor, filename);
        System.out.println(Floor.deserialise(filename));
    }

    //Orangutan hits a chained Panda from another chain M.M
    private static void test9(){
        Floor floor = new Floor();
        for(int i = 0; i < 9; i++)
        {
            floor.addTile(new RegularTile());
        }

        int[][] matrix =
                {
                        {},
                        {2},
                        {1, 6},
                        {4},
                        {3, 5},
                        {4, 6},
                        {2, 5, 7, 8},
                        {6},
                        {6, 9},
                        {8}
                };

        floor.setNeighbors(matrix);

        String filename = "test9.flr";
        Floor.serialise(floor, filename);
        System.out.println(Floor.deserialise(filename));
    }

	// VendingMachine makes JumpingPanda jump
	private static void test10(){
		Floor floor = new Floor();

		for(int i = 0; i < 4; i++)
		{
			floor.addTile(new RegularTile());
		}

		floor.setTile(1, new BrokenTile(20));
		floor.setTile(3, new BrokenTile(20));
		floor.setTile(4, new BrokenTile(20));
		floor.addThing(new VendingMachine(1), 2);

		int[][] mtx = {
				{},
				{2},
				{1, 3, 4},
				{2},
				{2}
		};
		
		String filename = "test10.flr";
		floor.setNeighbors(mtx);
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	// Orangutan hits its own Panda chain
	private static void test11(){
		Floor floor = new Floor();
		for(int i = 0; i < 5; i++)
		{
			floor.addTile(new RegularTile());
		}

		int[][] mtx = {
				{},
				{2},
				{1, 3, 4},
				{2, 5},
				{2, 5},
				{3, 4}
		};

		floor.setNeighbors(mtx);
		String filename = "test12.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	// Orangutan exits with two Pandas
	private static void test12(){
		Floor floor = new Floor();

		RegularTile entryTile = new RegularTile();
		for(int i = 0; i < 5 - 1; i++)
		{
			floor.addTile(new RegularTile());
		}
		floor.addTile(entryTile);

		int[][] mtx = {
				{},
				{2},
				{1, 3},
				{2, 4},
				{3, 5},
				{4}
		};

		floor.setEntry(new Entry(entryTile), 5);
		floor.setExit(new Exit(), 1);

		floor.setNeighbors(mtx);
		String filename = "test12.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Orangutan is exiting the floor with a Panda chain meanwhile a
	//GameMachine scares one ScaredPanda in it who leaves the chain
	//with all the Pandas behind.
	private static void test13(){
		Floor floor = new Floor();

		RegularTile entryTile = new RegularTile();
		floor.addTile(entryTile);
		for(int i = 1; i < 8; i++)
		{
			floor.addTile(new RegularTile());
		}

		int[][] mtx = {
				{},
				{2},
				{1, 3},
				{2, 4},
				{3, 5, 8},
				{4, 6},
				{5, 7},
				{6},
				{4}
		};

		floor.addThing(new VendingMachine(1), 8);
		floor.setEntry(new Entry(entryTile), 1);
		floor.setExit(new Exit(), 7);

		floor.setNeighbors(mtx);
		String filename = "test13.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Panda breaks a tile with its move - Előd
	private static void test14(){

		Floor floor = new Floor();
		for(int i=0; i<4;++i){

			floor.addTile(new RegularTile());
		}
		int[][] matrix =
				{
						{},		//0
						{2},	//1
						{1},	//2
						{4},	//3
						{3},	//4
				};
		floor.setNeighbors(matrix);
		floor.setTile(2, new BrokenTile());

		String filename = "test14.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Teszteset17 Last Orangutan dies. - Előd
	private static void test15(){

		Floor floor = new Floor();
		for(int i=0; i<4;++i){

			floor.addTile(new RegularTile());
		}
		int[][] matrix =
				{
						{},		//0
						{2},	//1
						{1},	//2
						{4},	//3
						{3},	//4
				};
		floor.setNeighbors(matrix);
		floor.setTile(2, new BrokenTile());

		String filename = "test15.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Teszteset18 Last  Panda dies - Előd
	private static void test16(){

		Floor floor = new Floor();
		for(int i=0; i<4;++i){

			floor.addTile(new RegularTile());
		}
		int[][] matrix =
				{
						{},		//0
						{2},	//1
						{1},	//2
						{4},	//3
						{3},	//4
				};
		floor.setNeighbors(matrix);
		floor.setTile(2, new BrokenTile());

		String filename = "test16.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Teszteset19 Orangutan releases its PandaChain K.Peti
	private static void test17(){
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

		String filename = "test17.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Teszteset20 Orangutan brings PandaChain over broken tile K.Peti
	private static void test18(){
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

		String filename = "test18.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}


}