package main;

import java.math.BigInteger;
import java.util.ArrayList;


import main.Bank;
import main.User;

public class CoinWithdrawal {

	public static void main(String args[]) {
		int k = 10;

		RSA rsa = new RSA();
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
			
			System.out.println("Time to extract!");

			//bank.signCoin();
			BigInteger signedCoin = bank.handOutCoin();
			ArrayList<Integer> rIndeces = bank.getrIndex();
			BigInteger extractedCoin = alice.extractCoin(rIndeces, signedCoin);
			System.out.println("Extracted coin: "+ extractedCoin);

			
			/*ArrayList<Integer> correctR = alice.getCorrectR(rIndex, userQuadruples);
			
			for(int i : correctR){
				System.out.println(i);
			}
			*/
			// bank.signCoin();

		}

	}

}
