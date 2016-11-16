package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {

	public int pubKey;
	private int privKey;

	public Bank() {
		pubKey = 7;
		privKey = 103;
	}

	public boolean verifyId(ArrayList<int[]> values, ArrayList<BigInteger> calculatedBs, ArrayList<Integer> chosenK,
			int id) {

		for (int i = 0; i < chosenK.size(); i++) {
			BigInteger B = calculatedBs.get(chosenK.get(i));
			calculatedBs.set(chosenK.get(i), null);
			int[] currentvalues = values.get(i);
			int a = currentvalues[0];
			int c = currentvalues[1];
			int d = currentvalues[2];
			int r = currentvalues[3];

			BigInteger x = hFunction(a, c);
			BigInteger y = hFunction(a + id, d);
			
			BigInteger rsa = new BigInteger(String.valueOf((int) Math.pow(r, pubKey)));
			
			BigInteger n = new BigInteger(
					"143");
		
			BigInteger Bvalue = (rsa.multiply(fFunction(x, y))).mod(n);

			if (!Bvalue.equals(B)) {
				
				return false;
			}

		}
		/*
		 * System.out.println("nu ska vi printa en lista"); for(int j=0;
		 * j<calculatedBs.size(); j++){
		 * System.out.println(j+":  "+calculatedBs.get(j)); }
		 */
		signCoin(calculatedBs);
		return true;
	}

	private BigInteger signCoin(ArrayList<BigInteger> Bs) {
		BigInteger signature = new BigInteger("1");
		BigInteger n = new BigInteger(
				"143");
		
		for (int i = 0; i < Bs.size(); i++) {
			
			if (Bs.get(i) != null) {
				
				signature = ((signature.multiply(Bs.get(i))).pow(privKey)).mod(n);
				
			}
		}
		System.out.println("coin: " + signature);
		return signature;
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
		// return a * b;
		return value;
	}

	public BigInteger fFunction(BigInteger x, BigInteger y) {
		return x.xor(y);

	}

	public ArrayList<Integer> chooseK(int k) {

		ArrayList<Integer> indices = new ArrayList<Integer>();

		for (int i = 0; i < k; i++) {
			Random rand = new Random();
			Boolean check = true;
			int n = rand.nextInt(2 * k);

			while (check) {

				if (indices.contains(n)) {
					n = rand.nextInt(2 * k);
				} else {
					check = false;
				}

			}

			indices.add(n);

		}

		return indices;

	}

}
