import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Program {

	public static void main(String[] args){
		System.out.println("Hello World!");
		reader();
	}

	private static void reader() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Floor Floor = null;
		Controller ctrl = new Controller();


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
						o.setPrevTile(o.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(o);
						Floor.getTile(Integer.parseInt(part[3])).setContains(o);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("jpanda") == 0){
						JumpingPanda jp = new JumpingPanda();
						jp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						jp.setName(part[2]);
						jp.setPrevTile(jp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(jp);
						Floor.getTile(Integer.parseInt(part[3])).setContains(jp);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("spanda") == 0){
						ScaredPanda sp = new ScaredPanda();
						sp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						sp.setName(part[2]);
						sp.setPrevTile(sp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(sp);
						Floor.getTile(Integer.parseInt(part[3])).setContains(sp);
						System.out.println(Floor);
					}
					else if(part[1].compareTo("lpanda") == 0){
						int rnd = new Random().nextInt(5);
						LazyPanda lp = new LazyPanda(rnd);
						lp.setIsOn(Floor.getTile(Integer.parseInt(part[3])));
						lp.setName(part[2]);
						lp.setPrevTile(lp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
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

					System.out.println(Floor);
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