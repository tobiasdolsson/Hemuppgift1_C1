package main;

import java.util.ArrayList;
import java.util.Random;

public class User {
	
	private int pubKey;
	private int privKey;
	private int id;
	
	Random rand = new Random();
	
	public User(int id){
		this.id = id;
		pubKey = 3;
		privKey = 3;
	}
	
	public ArrayList<int[]> generateQuadruples(int k){
		int a, r, c, d;
		int[] vector = new int[4];
		ArrayList<int[]> array = new ArrayList<int[]>();
		for(int i=0; i<2*k; i++){
			a = rand.nextInt(10);
			c = rand.nextInt(10);
			d = rand.nextInt(10);
			r = rand.nextInt(10);
			vector[0] = a;
			vector[1] = c;
			vector[2] = d;
			vector[3] = r;
			array.add(vector);
		}
		return array;
	}
	
	public int hashFunction(int a, int b){
		return a*b;
	}
	
	public int someOtherFunction(int x, int y){
		return x+y;
	}
	
	public int generateCutAndChoose(ArrayList<int[]> quadruples, int i, int pubKey){
		int x, y, a, r, c, d, bigB;
		a = quadruples.get(i)[0];
		c = quadruples.get(i)[1];
		d = quadruples.get(i)[2];
		r = quadruples.get(i)[3];
		x = hashFunction(a,c);
		y = hashFunction(a+id,d);
		quadruples.remove(i);
		//modulo n ska in här också
		bigB = (int)Math.pow(r, pubKey)*someOtherFunction(x,y);
		return bigB;
	}

}
