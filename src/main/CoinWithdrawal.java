package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import main.Bank;
import main.User;

public class CoinWithdrawal {
			
	public static void main(String args[]){
		int k = 10;
		
		RSA rsa = new RSA();
		BigInteger pubKey = rsa.getPubKey();
		BigInteger privKey = rsa.getPrivKey();
		BigInteger n = rsa.getN();
	
		User alice = new User(1991, pubKey, n);
		Bank bank = new Bank(pubKey,privKey, n);
		Random rand = new Random();
		ArrayList<int[]> userQuadruples = alice.generateQuadruples(k);
	
		ArrayList<BigInteger> calculatedBs = alice.generateCutAndChoose(userQuadruples);
		ArrayList<Integer> chosenKs = bank.chooseK(k);
		ArrayList<int[]> idsToVerify = alice.forBankToVerify(userQuadruples, chosenKs);
	
		if(bank.verifyId(idsToVerify, calculatedBs, chosenKs, alice.id) == true){
			//allt är bra, signera coin
			System.out.println("Time to extract!");
			//bank.signCoin();
			BigInteger signedCoin = bank.handOutCoin();
			ArrayList<Integer> rIndeces = new ArrayList<Integer>();
			BigInteger extractedCoin = alice.extractCoin(rIndeces, signedCoin);
			System.out.println(extractedCoin);
		}
		
	}
	
}
