import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FloorGenerator {

	public void generateFloor(int n, double entropy, OutputStream os) throws Exception{

		PrintWriter writer = new PrintWriter(os);

		for(int i = 0; i <= n; i++){
			writer.printf("%3d", i);
		}
		writer.printf("\n");

		for(int i = 1; i <= n; i++){
			writer.printf("%3d", i);
			for(int j = 0; j < n; j++){
				writer.printf("%3s", randomNeighbor(entropy));
			}
			writer.printf("\n");
		}
		writer.close();
	}

	private char randomNeighbor(double entropy){
		Random random = new Random();
		boolean isNeighbor = (random.nextDouble() * entropy) > 0.5;
		if(isNeighbor){
			return 'X';
		}
		return '_';
	}

	private void addObjects(HashMap<Integer, AThing> objects, PrintWriter writer){
		for(int i = 0; i < objects.size(); i++){

		}
	}
}
