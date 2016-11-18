package main;

import java.math.BigInteger;
import java.util.ArrayList;


import main.Bank;
import main.User;

public class CoinWithdrawal {

	public static void main(String args[]) {
		int k = 10;

		RSA rsa = new RSA(8);
		BigInteger pubKey = rsa.getPubKey();
		BigInteger privKey = rsa.getPrivKey();
		BigInteger n = rsa.getN();

		System.out.println("PublicKey (e): " + pubKey);
		System.out.println("PrivateKey (d): " + privKey);
		System.out.println("N-value: " + n);

		User alice = new User(1991, pubKey, n);
		Bank bank = new Bank(pubKey, privKey, n);
		ArrayList<int[]> userQuadruples = alice.generateQuadruples(k);
		ArrayList<BigInteger> calculatedBs = alice.caluclateBs(userQuadruples);
		ArrayList<Integer> chosenKs = bank.chooseK(k);
		ArrayList<int[]> idsToVerify = alice.forBankToVerify(userQuadruples, chosenKs);

		if (bank.verifyAndSign(idsToVerify, calculatedBs, chosenKs, alice.id) == true) {
			
			BigInteger signedCoin = bank.handOutCoin();
			ArrayList<Integer> rIndeces = bank.getrIndex();
			BigInteger extractedCoin = alice.extractCoin(rIndeces, signedCoin);
			System.out.println("Extracted coin: "+ extractedCoin);

		}

	}

}
