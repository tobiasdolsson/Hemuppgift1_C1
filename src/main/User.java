package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class User {

	public int id;
	private BigInteger pubKey;
	private BigInteger n;
	private ArrayList<Integer> rValues;

	Random rand = new Random();

	public User(int id, BigInteger pubKey, BigInteger n) {
		this.id = id;
		this.pubKey = pubKey;
		this.n = n;
		rValues = new ArrayList<Integer>();
	}

	public ArrayList<int[]> generateQuadruples(int k) {
		int a, r, c, d;
		rValues = new ArrayList<Integer>();
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < 2 * k; i++) {
			int[] vector = new int[4];
			a = rand.nextInt(100);
			c = rand.nextInt(100);
			d = rand.nextInt(100);
			r = rand.nextInt(100);
			vector[0] = a;
			vector[1] = c;
			vector[2] = d;
			vector[3] = r + 1;

			rValues.add(r+1);

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

	public ArrayList<BigInteger> caluclateBs(ArrayList<int[]> quadruples) {
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

			BigInteger random = BigInteger.valueOf(r);

			BigInteger temp = random.pow(pubKey.intValue());

			// System.out.println((rsa.multiply(fFunction(x, y))));
			BigInteger fvalue = temp.multiply(fFunction(x, y));
			bigB = fvalue.mod(n);
			// System.out.println(bigB.toString());
			// System.out.println("----");
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
	
	public BigInteger extractCoin(ArrayList<Integer> rIndeces, BigInteger coin){
		BigInteger totalValue = new BigInteger("1");
		for(int i=0; i<rIndeces.size(); i++){
			BigInteger mulValue = BigInteger.valueOf(rValues.get(rIndeces.get(i)));
			totalValue = totalValue.multiply(mulValue);
		}
		
		return coin.divide(totalValue);
		
	}

	public ArrayList<Integer> getCorrectR(ArrayList<Integer> index, ArrayList<int[]> userQuadruples) {
		ArrayList<Integer> correctR = new ArrayList<Integer>();
		for (int i : index) {
			int[] values = userQuadruples.get(index.get(i));
			
			correctR.add(values[3]);
			System.out.println(values[3]);
			
			

		}

		return null;

	}

}
