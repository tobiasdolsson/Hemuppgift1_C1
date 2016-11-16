package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class User {

	private int pubKey;
	private int privKey;
	public int id;

	Random rand = new Random();

	public User(int id) {
		this.id = id;
		pubKey = 3;
		privKey = 3;
	}

	public ArrayList<int[]> generateQuadruples(int k) {
		int a, r, c, d;

		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < 2 * k; i++) {
			int[] vector = new int[4];
			a = rand.nextInt(10);
			c = rand.nextInt(10);
			d = rand.nextInt(10);
			r = rand.nextInt(10);
			vector[0] = a;
			vector[1] = c;
			vector[2] = d;
			vector[3] = r;
			list.add(vector);
		}
		return list;
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

	public ArrayList<Integer> generateCutAndChoose(ArrayList<int[]> quadruples, int pubKey) {
		int x, y, a, r, c, d, bigB;
		ArrayList<Integer> toBeSigned = new ArrayList<Integer>();

		for (int i = 0; i < quadruples.size(); i++) {

			a = quadruples.get(i)[0];
			c = quadruples.get(i)[1];
			d = quadruples.get(i)[2];
			r = quadruples.get(i)[3];
			x = hFunction(a, c);
			y = hFunction(a + id, d);

			// modulo n ska in här också
			bigB = (int) Math.pow(r, pubKey) * fFunction(x, y);

			toBeSigned.add(bigB);

		}

		return toBeSigned;
	}

	public ArrayList<int[]> forBankToVerify(ArrayList<int[]> userQuadruples, ArrayList<Integer> chosenKs) {
		ArrayList<int[]> verifyThis = new ArrayList<int[]>();

		for (int i : chosenKs) {	
			System.out.println(i);
			verifyThis.add(userQuadruples.get(i));
		}

		return verifyThis;

	}

}
