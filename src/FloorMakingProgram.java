import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FloorMakingProgram
{

	public static void main(String[] args)
	{

		mainFloor();

	}

	private static void mainFloor()
	{
		//Generating floor
		Floor floor = Floor.getInstance();
		for(int i = 0; i < 43; i++){
			floor.addTile(new RegularTile());
		}


		floor.setTile(32, new BrokenTile(2));
		floor.setTile(27, new BrokenTile(2));
		//Adding things to the floor
		floor.setExit(new Exit(), 23);
		floor.addThing(new GameMachine(3), 13);
		Wardrobe w1 = new Wardrobe();
		Wardrobe w2 = new Wardrobe();
		w1.setOutPoint(w2);
		w2.setOutPoint(w1);
		floor.addThing(w1, 37);
		floor.addThing(new Armchair(), 41);
		floor.addThing(w2, 24);
		Entry entry = new Entry(floor.getTile(17));
		floor.setEntry(entry, 16);


		//Setting neighbors
		int[][] matrix = {
				{},							//0
				{2,16},						//1
				{1,16,15},					//2
				{15,14,4},					//3
				{3,14,5},					//4
				{4,14,13,6},				//5
				{5,13,7},					//6
				{6,13,12,8},				//7
				{7,11,9},					//8
				{8,10},						//9
				{9,11,23},					//10
				{10,8,12,23},				//11
				{11,7,13,22,23},			//12
				{12,7,6,5,14,20,29,21,22},	//13
				{13,5,4,3,15,17,18,19,20},	//14
				{14,3,2,16,17},				//15
				{15,2,1,35,34,33,17},		//16
				{16,33,18,14,15},			//17
				{17,32,19,14},				//18
				{18,31,30,20,14},			//19
				{19,30,29,13,14},			//20
				{13,29,27,22},				//21
				{21,26,23,12,13},			//22
				{22,26,25,24,10,11,12},		//23
				{23,25},					//24
				{24,23,26,42},				//25
				{25,23,22,27},				//26
				{26,21,28,40,42},			//27
				{27,29,30,39},				//28
				{28,21,13,20,30},			//29
				{29,20,19,31,38,39,28},		//30
				{30,19,32,38},				//31
				{31,18,33,36,37},			//32
				{32,17,16,34},				//33
				{33,16,35,36},				//34
				{34,16},					//35
				{34,32},					//36
				{32,38},					//37
				{37,30,31},					//38
				{30,40,28},					//39
				{39,41,27},					//40
				{40,42},					//41
				{41,25,27},					//42
		};
		floor.setNeighbors(matrix);
		//Serialization
		String filename = "mainFloor.flr";
		Floor.serialise(floor, filename);

	}

	//Program starts and makes a map
	private static void test1() {
		//Generating floor
		Floor floor = Floor.getInstance();
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
		ArrayList<Orangutan> ol = new ArrayList<>();
		ol.add(new Orangutan());
		ol.add(new Orangutan());
		entry.addOrangutan(ol);
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
		Floor.clearInstance();
	}


	//Entry generates two orangutans and player steps with them
	private static void test2(){
		//Generating floor
		Floor floor = Floor.getInstance();
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
		ArrayList<Orangutan> ol = new ArrayList<>();
		ol.add(new Orangutan());
		ol.add(new Orangutan());
		entry.addOrangutan(ol);
		floor.setEntry(entry, 1);
		//Serialization
		String filename = "test2.flr";
		Floor.serialise(floor, filename);
		Floor.clearInstance();
	}

	/*
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

        floor.addThing(new GameMachine(1), 7);

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

		floor.setTile(1, new BrokenTile(1));
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
		String filename = "test11.flr";
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

		floor.addThing(new GameMachine(1), 8);
		Wardrobe w1 = new Wardrobe();
		Wardrobe w2 = new Wardrobe();
		w1.setOutPoint(w2);
		w2.setOutPoint(w1);
		floor.addThing(w1, 1);
		floor.addThing(w2, 7);

		floor.setNeighbors(mtx);
		String filename = "test13.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Panda breaks a tile with its move - Előd
	private static void test14(){
		Floor floor = new Floor();
		for(int i = 0; i < 3; i++)
		{
			floor.addTile(new RegularTile());
		}
		floor.setTile(2, new BrokenTile(1)); //Csak tesztelés 1

		int[][] matrix =
				{
						{},
						{2},
						{1},
						{}
				};

		floor.setNeighbors(matrix);

		String filename = "test14.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	//Teszteset15 Orangutan releases its PandaChain K.Peti
	private static void test15(){
		Floor floor = new Floor();
		for(int i = 0; i < 4; i++)
		{
			floor.addTile(new RegularTile());
		}

		int[][] matrix =
		{
				{},
				{2},
				{1,3},
				{2,4},
				{3},
		};

		floor.setNeighbors(matrix);

		System.out.println(floor.toString());

		String filename = "test15.flr";
		Floor.serialise(floor, filename);
		System.out.println(Floor.deserialise(filename));
	}

	 */

}