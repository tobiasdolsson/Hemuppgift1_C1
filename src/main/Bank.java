package main;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	public int pubKey;
	private int privKey;

	public Bank() {
		pubKey = 3;
		privKey = 3;
	}

	public boolean verifyId(ArrayList<Integer> array) {
		return true;
	}

	public void signCoin() {

	}

	public ArrayList chooseK(int k) {
		
		ArrayList<Integer> indices = new ArrayList<Integer>();

		for (int i = 0; i > k; i++) {
			Random rand = new Random();
			int n = rand.nextInt(2 * k) + 1;
			indices.add(n);

		}
		return indices;

	}

}
