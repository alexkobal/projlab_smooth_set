import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;

public class Floor {
	private int idx;
	private HashMap<Integer, Tile> tiles;
	private HashSet<AThing> things;
	private Entry entry;
	private Exit exit;

	public Floor(){
		tiles = new HashMap<>();
		things = new HashSet<>();
		idx = 1;
		entry = null;
		exit = null;
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
			things.add(wardrobe);
		}
	}

	public void addThing(Armchair armchair, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(armchair);
			things.add(armchair);
		}
	}

	public void addThing(VendingMachine vm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(vm);
			things.add(vm);
		}
	}

	public void addThing(GameMachine gm, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(gm);
			things.add(gm);
		}
	}

	public void setEntry(Entry entry, int tileIdx){
		if(tiles.get(tileIdx).getContains() == null){
			tiles.get(tileIdx).setContains(entry);
			this.entry = entry;
		}
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
}
