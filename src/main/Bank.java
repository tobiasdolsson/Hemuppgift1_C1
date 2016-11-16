package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {

	public int pubKey;
	private int privKey;

	public Bank() {
		pubKey = 3;
		privKey = 3;
	}

	public boolean verifyId(ArrayList<int[]> values, ArrayList<Integer> calculatedBs, ArrayList<Integer> chosenK,
			int id) {

		for (int i = 0; i < chosenK.size(); i++) {
			int B = calculatedBs.get(chosenK.get(i));
			int[] currentvalues = values.get(i);
			int a = currentvalues[0];
			int c = currentvalues[1];
			int d = currentvalues[2];
			int r = currentvalues[3];

			int x = hFunction(a, c);
			int y = hFunction(a + id, d);

			int Bvalue = (int) Math.pow(r, pubKey) * fFunction(x, y);
			System.out.println(B);
			System.out.println(Bvalue);
			System.out.println("----");

			if (Bvalue != B) {
				return false;
			}

		}

		return true;
	}

	public int hFunction(int a, int b) {
		String toHash = Integer.toBinaryString((a + b));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		md.update(toHash.getBytes());

		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println("Hex format : " + sb.toString());

		// return sb.toString();
		return a * b;
	}

	public int fFunction(int x, int y) {
		return x + y;

	}

	public void signCoin() {

	}

	public ArrayList<Integer> chooseK(int k) {

		ArrayList<Integer> indices = new ArrayList<Integer>();

		for (int i = 0; i < k; i++) {
			Random rand = new Random();
			int n = rand.nextInt(2 * k);
			indices.add(n);

		}
		return indices;

	}

}
