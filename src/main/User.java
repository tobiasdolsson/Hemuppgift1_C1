package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class User {

	private int pubKey;
	//private int privKey;
	public int id;
	private int nValue;

	Random rand = new Random();

	public User(int id) {
		this.id = id;
		pubKey = 7;

	}

	public ArrayList<int[]> generateQuadruples(int k) {
		int a, r, c, d;

		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < 2 * k; i++) {
			int[] vector = new int[4];
			a = rand.nextInt(10) + 1;
			c = rand.nextInt(10) + 1;
			d = rand.nextInt(10) + 1;
			r = rand.nextInt(10) + 1;
			vector[0] = a;
			vector[1] = c;
			vector[2] = d;
			vector[3] = r;
			list.add(vector);
		}
		return list;
	}

	public BigInteger hFunction(int a, int b) {
		String toHash = Integer.toBinaryString((a + b));
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		md.update(toHash.getBytes());

		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		BigInteger value = new BigInteger(sb.toString(), 16);

		// return sb.toString();
		return value;
		// return a * b;
	}

	public BigInteger fFunction(BigInteger x, BigInteger y) {

		return x.xor(y);

	}

	public ArrayList<BigInteger> generateCutAndChoose(ArrayList<int[]> quadruples, int pubKey) {
		int a, r, c, d;
		BigInteger x, y, bigB;
		ArrayList<BigInteger> toBeSigned = new ArrayList<BigInteger>();

		for (int i = 0; i < quadruples.size(); i++) {

			a = quadruples.get(i)[0];
			c = quadruples.get(i)[1];
			d = quadruples.get(i)[2];
			r = quadruples.get(i)[3];
			x = hFunction(a, c);
			y = hFunction(a + id, d);

			// modulo n ska in här också
			BigInteger rsa = new BigInteger(String.valueOf((int) Math.pow(r, pubKey)));

			BigInteger n = new BigInteger(
					"143");
			//System.out.println((rsa.multiply(fFunction(x, y))));

			bigB = (rsa.multiply(fFunction(x, y))).mod(n);
			 System.out.println(bigB.toString());

			toBeSigned.add(bigB);

		}

		return toBeSigned;
	}

	public ArrayList<int[]> forBankToVerify(ArrayList<int[]> userQuadruples, ArrayList<Integer> chosenKs) {
		ArrayList<int[]> verifyThis = new ArrayList<int[]>();

		for (int i : chosenKs) {

			verifyThis.add(userQuadruples.get(i));
		}

		return verifyThis;

	}

}
