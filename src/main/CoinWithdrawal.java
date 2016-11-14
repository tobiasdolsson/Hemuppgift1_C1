package main;

import java.util.ArrayList;
import java.util.Random;

public class CoinWithdrawal {
	
	
	private int random;
	private int x;
	private int pubKey, privKey;
	
	Random rand = new Random();
	
	public CoinWithdrawal(){
		x = rand.nextInt(10);
		random = rand.nextInt(10);		
	}
	
	private ArrayList<int[]> generateQuadruples(int k){
		int a, b, c, d;
		int[] vector = new int[4];
		ArrayList<int[]> array = new ArrayList<int[]>();
		for(int i=0; i<2*k; i++){
			a = rand.nextInt(10);
			b = rand.nextInt(10);
			c = rand.nextInt(10);
			d = rand.nextInt(10);
			vector[0] = a;
			vector[1] = b;
			vector[2] = c;
			vector[3] = d;
			array.add(vector);
		}
		return array;
	}

}
