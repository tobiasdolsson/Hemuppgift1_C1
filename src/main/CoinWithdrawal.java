package main;

import java.util.ArrayList;
import java.util.Random;

public class CoinWithdrawal {
			
	public static void main(String args[]){
		User alice = new User(1991);
		Bank bank = new Bank();
		Random rand = new Random();
		ArrayList<int[]> userQuadruples = alice.generateQuadruples(10);
		ArrayList<Integer> idsToVerify = new ArrayList<Integer>();
	
		if(bank.verifyId(idsToVerify) == true){
			//allt är bra, signera coin
			bank.signCoin();
		}
		
	}
	
}
