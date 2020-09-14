import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * FloorMakingProgram
 * <p>
 *     A program where pre-written floor objects can be (re)serialised
 * </p>
 */
public class FloorMakingProgram
{
	/**
	 * main
	 * <p>
	 *     Main method of the FloorMakingProgram, generates and serialises the game floor
	 * </p>
	 * @param args program arguments
	 */
	public static void main(String[] args)
	{
		mainFloor();
	}

	/**
	 * mainFloor
	 * <p>
	 *     Generates and serialises the floor object that is used in the game
	 * </p>
	 */
	private static void mainFloor()
	{
		//Generating floor
		Floor floor = Floor.getInstance();
		for(int i = 0; i < 43; i++){
			floor.addTile(new RegularTile());
		}


		floor.setTile(32, new BrokenTile(20));
		floor.setTile(27, new BrokenTile(20));
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
}