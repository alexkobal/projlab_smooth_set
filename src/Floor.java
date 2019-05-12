import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Floor singleton class
 *
 * <p>
 * 		Represents a floor witch is built from tiles.
 * 		Objects are placed on tiles.
 * 		Also a floor contains an entry and an exit point
 * </p>
 */
public class Floor implements Serializable {
	/**
	 * instance
	 * <p>
	 *     This is the singleton instance of the floor
	 * </p>
	 */
	private static Floor instance = null;
	/**
	 *idx
	 * <p>
	 *     Is used for tracking Tile index
	 * </p>
	 */
	private int idx;
	/**
	 * tiles
	 * <p>
	 *     Contains tiles of the floor
	 * </p>
	 */
	private HashMap<Integer, Tile> tiles;
	/**
	 * notifiers
	 * <p>
	 *     Contains objects placed on the floor
	 * </p>
	 */
	private ArrayList<IPandaEffective> notifiers;
	/**
	 * enrty
	 * <p>
	 *     Entry point of the floor
	 * </p>
	 */
	private Entry entry;
	/**
	 * exit
	 * <p>
	 *     Exit point of the floor
	 * </p>
	 */
	private Exit exit;

	/**
	 * Floor()
	 * <p>
	 *     Default constructor
	 *     Initialises parameters
	 * </p>
	 */
	private Floor(){
		tiles = new HashMap<>();
		notifiers = new ArrayList<>();
		idx = 0;
		entry = null;
		exit = null;
		instance = this;
	}
	/**
	 * addTile
	 * <p>
	 *     Adds a tile to the floor and increments idx
	 * </p>
	 * @param tile the tile that's been added
	 */
	public void addTile(Tile tile){
		if(!tiles.containsValue((Tile)tile)) {
			tiles.put(idx, tile);
		}
		idx++;
	}
	/**
	 * getTile
	 * <p>
	 *     Gets a tile reference from the list
	 * </p>
	 * @param key index of the tile
	 * @return tile with the given index
	 */
	public Tile getTile(int key){
		return tiles.get(key);
	}
	/**
	 * setTile
	 * <p>
	 *     Changes a tile in the list
	 *     Checks if the tile with given index exists
	 * </p>
	 * @param key index of the tile that is going to be set
	 * @param tile reference to the new tile value
	 */
	public void setTile(int key, Tile tile){
		if(tiles.containsKey(key)){
			tiles.replace(key, tile);
		}else{
			tiles.put(key, tile);
		}
	}
	/**
	 * addThing
	 * <p>
	 *     Adds a wardrobe to the floor
	 *     Checks if the tile is empty
	 * </p>
	 * @param wardrobe the wardrobe
	 * @param tileIdx index of the tile where the wardrobe is going to be placed
	 */
	public void addThing(Wardrobe wardrobe, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(wardrobe);
		}
	}
	/**
	 * addThing
	 * <p>
	 *     Adds an armchair to the floor
	 *     Checks if the tile is empty
	 * </p>
	 * @param armchair the armchair
	 * @param tileIdx index of the tile where the armchair is going to be placed
	 */
	public void addThing(Armchair armchair, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(armchair);
			notifiers.add(armchair);
		}
	}
	/**
	 * addThing
	 * <p>
	 *     Adds a VendingMachine to the floor
	 *     Checks if the tile is empty
	 * </p>
	 * @param vm the VendingMachine
	 * @param tileIdx index of the tile where the VendingMachine is going to be placed
	 */
	public void addThing(VendingMachine vm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(vm);
			notifiers.add(vm);
		}
	}
	/**
	 * addThing
	 * <p>
	 *     Adds a GameMachine to the floor
	 *     Checks if the tile is empty
	 * </p>
	 * @param gm the GameMachine
	 * @param tileIdx index of the tile where the GameMachine is going to be placed
	 */
	public void addThing(GameMachine gm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(gm);
			notifiers.add(gm);
		}
	}
	/**
	 * setEntry
	 * <p>
	 *     Sets the entry point of the floor
	 * </p>
	 * @param entry the entry object
	 * @param tileIdx tile index where the entry point is going to be placed
	 */
	public void setEntry(Entry entry, int tileIdx){

			tiles.get(tileIdx).setContains(entry);
			this.entry = entry;

	}
	/**
	 * setExit
	 * <p>
	 *     Sets the exit point of the floor
	 * </p>
	 * @param exit the exit object
	 * @param tileIdx tile index where the exit point is going to be placed
	 */
	public void setExit(Exit exit, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(exit);
			this.exit = exit;
		}
	}
	/**
	 * setNeighbors
	 * <p>
	 *     Sets the neighbors of the tile
	 * </p>
	 * @param matrix a two dimensional adjustment-like matrix where
	 *               1. dim - rows
	 *               2. dim - columns
	 *               the 0th row must be clean
	 *               other rows contain indexes of neighbors
	 */
	public void setNeighbors(int[][] matrix){
		for(int i = 1; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				tiles.get(i).addNeighbor(tiles.get(matrix[i][j]));
			}
		}
	}
	/**
	 * status
	 * <p>
	 *     Gets the actual floor status with objects
	 * </p>
	 * @return string with formed status
	 */
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
	/**
	 * toString()
	 * <p>
	 *     Converts the needed information to a string
	 * </p>
	 * @return string with floor information
	 */
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
	/**
	 * serialise
	 * <p>
	 *     Writes a full floor to a binary file
	 * </p>
	 * @param floor the floor that is going to be serialised
	 * @param filename name of the file
	 */
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
	/**
	 * deserialise
	 * <p>
	 *     Reads a Floor object from a file
	 * </p>
	 * @param filename name of the file that is going to be deserialised
	 */
	public static void deserialise(String filename){

		Floor floor = null;
		try{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			floor = (Floor)ois.readObject();
			ois.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		instance = floor;
	}
	/**
	 * getNotifiers
	 * <p>
	 *     Gets a reference to the notify-capable objects on the floor
	 * </p>
	 * @return a list of objects
	 */
	public ArrayList<IPandaEffective> getNotifiers(){
		return notifiers;
	}
	/**
	 * getEntry
	 * <p>
	 *     Gets the entry point of the floor
	 * </p>
	 * @return reference to the entry point
	 */
	public Entry getEntry() {
		return entry;
	}
	/**
	 * getExit
	 * <p>
	 *     Gets the exit point of the floor
	 * </p>
	 * @return reference to the exit point
	 */
	public Exit getExit() {
		return exit;
	}
	/**
	 * getInstance
	 * <p>
	 *     Singleton implementation
	 *     Creates an instance if that's the first reference
	 *     Gives a reference to instance if it already exists
	 * </p>
	 * @return reference to the Floor instance
	 */
	public static Floor getInstance() {
		if(instance != null) {
			return instance;
		}else{
			return instance = new Floor();
		}
	}
	/**
	 * clearInstance()
	 * <p>
	 *     Sets the instance to null
	 * </p>
	 */
	public static void clearInstance(){
		instance = null;
	}
	/**
	 * getTiles
	 * <p>
	 *     Provides a reference to the tile list of the floor
	 * </p>
	 * @return indexed list of tiles
	 */
	public HashMap<Integer, Tile> getTiles(){
		return tiles;
	}
}
