import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Program {

	public static void main(String[] args)
	{
		Floor floor = new Floor();

		/*BrokenTile rt = new BrokenTile();
		Armchair a = new Armchair();
		a.setPanda(new LazyPanda(55));
		rt.setContains(a);

		floor.addTile(rt);*/

		/*RegularTile rt = new RegularTile();
		Exit ex = new Exit();
		rt.setContains(ex);
		floor.addTile(rt);*/

		/*RegularTile rt = new RegularTile();
		Wardrobe w = new Wardrobe();
		rt.setContains(w);
		floor.addTile(rt);*/

		/*RegularTile rt = new RegularTile();
		Armchair gm = new Armchair();
		rt.setContains(gm);
		floor.addTile(rt);*/

		RegularTile entryTile = new RegularTile();
		Entry en = new Entry(entryTile);
		floor.addTile(entryTile);



		View view = View.getInstance();
		view.construate("minimap", floor, 1);
		en.invokeDraw();
	}


	// Korábbi feladat.. torolheto??
	//FloorMakingProgram.main(new String[3]);
	//reader();
	/*private static void reader() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Floor floor = null;
		Controller ctrl = null;


		while (true) {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (line.length() <= 0) {
				break;
			}

			String[] part = line.split(" ");

			if(part.length > 1) {
				if(part[0].compareTo("cm")==0)
					try{
						floor = Floor.deserialise(part[1]);
						ctrl = new Controller(floor);
						System.out.println(floor);
					}catch (Exception e){
						e.printStackTrace();
					}

				else if(part[0].compareTo("put")==0) {		//move-val kéne nem?
					if (part[1].compareTo("orangutan") == 0) {
						Orangutan o = new Orangutan();
						o.setIsOn(floor.getTile(Integer.parseInt(part[3])));
						o.setName(part[2]);
						o.setPrevTile(o.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(o);
						ctrl.orangutans.add(o);
						floor.getTile(Integer.parseInt(part[3])).setContains(o);
						System.out.println(floor.status());
					}
					else if(part[1].compareTo("jpanda") == 0){
						JumpingPanda jp = new JumpingPanda();
						jp.setIsOn(floor.getTile(Integer.parseInt(part[3])));
						jp.setName(part[2]);
						jp.setPrevTile(jp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(jp);
						ctrl.pandas.add(jp);
						floor.getTile(Integer.parseInt(part[3])).setContains(jp);
						System.out.println(floor.status());
					}
					else if(part[1].compareTo("spanda") == 0){
						ScaredPanda sp = new ScaredPanda();
						sp.setIsOn(floor.getTile(Integer.parseInt(part[3])));
						sp.setName(part[2]);
						sp.setPrevTile(sp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(sp);
						ctrl.pandas.add(sp);
						floor.getTile(Integer.parseInt(part[3])).setContains(sp);
						System.out.println(floor.status());
					}
					else if(part[1].compareTo("lpanda") == 0){
						int rnd = Math.abs(new Random().nextInt(4) + 1);
						LazyPanda lp = new LazyPanda(rnd);
						lp.setIsOn(floor.getTile(Integer.parseInt(part[3])));
						lp.setName(part[2]);
						lp.setPrevTile(lp.getIsOn());										//FONTOS HOGY A PREVTILE BE LEGYEN SETTELVE!!
						ctrl.animals.add(lp);
						ctrl.pandas.add(lp);
						floor.getTile(Integer.parseInt(part[3])).setContains(lp);
						System.out.println(floor.status());
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



			}else if(part.length == 1){
				if(part[0].compareTo("run")==0)		//Minek??
					System.out.println("runolok");

				else if(part[0].compareTo("start")==0){
					ctrl.start();
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
	}*/
}