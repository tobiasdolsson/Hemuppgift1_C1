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
		int nbrOfBs = userQuadruples.size()/2;
		for(int i=0; i<nbrOfBs; i++){
			int a = rand.nextInt(userQuadruples.size());
			idsToVerify.add(alice.generateCutAndChoose(userQuadruples, a, bank.pubKey));
		}
		if(bank.verifyId(idsToVerify) == true){
			//allt är bra, signera coin
			bank.signCoin();
		}
		
	}
	
}
