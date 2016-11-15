package main;

import java.util.ArrayList;
import java.util.Random;

public class CoinWithdrawal {
			
	public static void main(String args[]){
		int k = 10;
		User alice = new User(1991);
		Bank bank = new Bank();
		Random rand = new Random();
		ArrayList<int[]> userQuadruples = alice.generateQuadruples(k);
		ArrayList<Integer> calculatedBs = alice.generateCutAndChoose(userQuadruples, bank.pubKey);
		ArrayList<Integer> choosenKs = bank.chooseK(k);
		ArrayList<int[]> idsToVerify = alice.forBankToVerify(userQuadruples, choosenKs);
	
		if(bank.verifyId(idsToVerify, calculatedBs) == true){
			//allt är bra, signera coin
			bank.signCoin();
		}
		
	}
	
}
