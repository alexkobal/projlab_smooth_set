import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FloorMakingProgram {

	public static void main(String[] args){
		System.out.println("Hello World!");
		test1();
	}

	private static void test1() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Floor Floor = null;
		Controller ctrl = new Controller();


//		for(int i = 0; i < 9; i++){					map készítéshez
//			floor.addTile(new RegularTile());
//		}
//		floor.setTile(8, new BrokenTile(20));
//		floor.setExit(new Exit(), 1);
//		floor.addThing(new GameMachine(3), 3);
//
//		Wardrobe w1 = null;
//		Wardrobe w2 = new Wardrobe(w1);
//		w1 = new Wardrobe(w2);
//
//		floor.addThing(w1, 4);
//		floor.addThing(new Armchair(), 5);
//		floor.addThing(w2, 7);
//		Entry entry = new Entry(floor.getTile(6));
//		entry.addOrangutan(2);
//		floor.setEntry(entry, 9);
//
//
//		int[][] matrix = {
//				{},			//0
//				{2},		//1
//				{1,3,8},	//2
//				{2,4,8},	//3
//				{3,5,8},	//4
//				{4,6,8},	//5
//				{5,7,9},	//6
//				{6,8},		//7
//				{2,3,4,5,7},//8
//				{6}			//9
//		};
//
//		floor.setNeighbors(matrix);
//
//		System.out.println(floor.toString());
//
//		try{
//			FileOutputStream fos = new FileOutputStream("test1.flr");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(floor);
//			oos.close();
//		}catch (Exception e){
//			e.printStackTrace();
//		}


		while (true) {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line.length() == 0) break;

			String[] part = line.split(" ");
			if(part.length > 1) {
				if(part[0].compareTo("cm")==0)
					try{
						FileInputStream fis = new FileInputStream(part[1]);
						ObjectInputStream ois = new ObjectInputStream(fis);
						Floor = (Floor)ois.readObject();
						ois.close();
						System.out.println(Floor);
					}catch (Exception e){
						e.printStackTrace();
					}

				else if(part[0].compareTo("put")==0) {		//move-val kéne nem?
					if (part[1].compareTo("orangutan") == 0) {
						Orangutan o = new Orangutan();
						o.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						o.setName(part[2]);
						ctrl.animals.add(o);
						Floor.getTile(Integer.parseInt(part[3])).setContains(o);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("jpanda") == 0){
						JumpingPanda jp = new JumpingPanda();
						jp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						jp.setName(part[2]);
						ctrl.animals.add(jp);
						Floor.getTile(Integer.parseInt(part[3])).setContains(jp);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("spanda") == 0){
						ScaredPanda sp = new ScaredPanda();
						sp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						sp.setName(part[2]);
						ctrl.animals.add(sp);
						Floor.getTile(Integer.parseInt(part[3])).setContains(sp);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("lpanda") == 0){
						int rnd = new Random().nextInt(5);
						LazyPanda lp = new LazyPanda(rnd);
						lp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						lp.setName(part[2]);
						ctrl.animals.add(lp);
						Floor.getTile(Integer.parseInt(part[3])).setContains(lp);
						System.out.println(Floor);
					}
				}

				else if(part[0].compareTo("chain")==0){
					Animal elso = null;
					Animal masodik = null;
					for (Animal a: ctrl.animals) {
						if(a.getName().compareTo(part[1]) == 0)
							elso = a;
						if(a.getName().compareTo(part[2]) == 0)
							masodik = a;
					}

					if(elso != null && masodik != null){		//Bemeneti nyelv szerint ezt csinálja, de van az animalnak connectChain függvénye!!!
						elso.prevAnimal = masodik;
						masodik.nextAnimal = elso;
					}
					else System.out.println("Valami nem jó!");
					//PREVTILE?!?
				}


				else if(part[0].compareTo("move")==0){
					Animal an = null;
					for (Animal a: ctrl.animals) {
						if (a.getName().compareTo(part[1]) == 0)
							an = a;
					}
					an.move(Floor.getTile(Integer.parseInt(part[2])));
				}

				else if(part[0].compareTo("run")==0)		//Minek??
					System.out.println("runolok");

				else if(part[0].compareTo("start")==0){
					/*Magic*/
				}

				else
					System.out.println("Unknown Command!");
			}
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		Floor Floor = null;
//		try{
//			FileInputStream fis = new FileInputStream("test1.flr");
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			Floor = (Floor)ois.readObject();
//			ois.close();
//			System.out.println(Floor);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}
}