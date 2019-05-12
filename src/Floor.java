import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;

public class Floor implements Serializable {
	private int idx;
	private HashMap<Integer, Tile> tiles;
	private ArrayList<IPandaEffective> notifiers;
	private Entry entry;
	private Exit exit;

	private Floor(){
		tiles = new HashMap<>();
		notifiers = new ArrayList<>();
		idx = 0;
		entry = null;
		exit = null;
		instance = this;
	}

	public void addTile(Tile tile){
		if(!tiles.containsValue((Tile)tile)) {
			tiles.put(idx, tile);
		}
		idx++;
	}
	public Tile getTile(int key){
		return tiles.get(key);
	}
	public void setTile(int key, Tile tile){
		if(tiles.containsKey(key)){
			tiles.replace(key, tile);
		}else{
			tiles.put(key, tile);
		}
	}

	public void addThing(Wardrobe wardrobe, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(wardrobe);
		}
	}

	public void addThing(Armchair armchair, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(armchair);
			notifiers.add(armchair);
		}
	}

	public void addThing(VendingMachine vm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(vm);
			notifiers.add(vm);
		}
	}

	public void addThing(GameMachine gm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(gm);
			notifiers.add(gm);
		}
	}

	public void setEntry(Entry entry, int tileIdx){

			tiles.get(tileIdx).setContains(entry);
			this.entry = entry;

	}

	public void setExit(Exit exit, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(exit);
			this.exit = exit;
		}
	}

	public void setNeighbors(int[][] matrix){
		for(int i = 1; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				tiles.get(i).addNeighbor(tiles.get(matrix[i][j]));
			}
		}
	}

	public String status(){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Integer, Tile> entry : tiles.entrySet()){
			int key = entry.getKey();
			Tile value = entry.getValue();
			if (value.getContains() != null){
				sb.append(key + value.getContains().toString());
			}else{
				sb.append(key + value.toString());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String toString(){
		//Creating StringBuilder to write down the matrix
		StringBuilder sb = new StringBuilder();
		//Head row
		for(int i = 0; i < idx; i++){
			sb.append(String.format("%3d", i));
		}
		sb.append("\n");
		//Value rows
		for(Map.Entry<Integer, Tile> entry : tiles.entrySet()){
			int key = entry.getKey();
			Tile value = entry.getValue();
			sb.append(String.format("%3d", key));

			//Writing down the neighbors
			ArrayList<Tile> neighbors = value.getNeighbors();
			for(int i = 1; i < idx; i++){
				if(neighbors.contains(tiles.get((Integer)i))){
					sb.append(String.format("%3s", "X"));
				}else{
					sb.append(String.format("%3s", "_"));
				}
			}
			sb.append("\n");
		}
		sb.append(status());
		return sb.toString();
	}

	public static void serialise(Floor floor, String filename){
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(floor);
			oos.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Floor deserialise(String filename){

		Floor floor = null;
		try{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			floor = (Floor)ois.readObject();
			ois.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return floor;
	}

	public ArrayList<IPandaEffective> getNotifiers(){
		return notifiers;
	}

	public Entry getEntry() {
		return entry;
	}

	public Exit getExit() {
		return exit;
	}

	private static Floor instance = null;
	public static Floor getInstance() {
		if(instance != null) {
			return instance;
		}else{
			return instance = new Floor();
		}
	}
	public static void clearInstance(){
		instance = null;
	}

	public HashMap<Integer, Tile> getTiles(){
		return tiles;
	}
}
