package main;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private BigInteger privKey;
	private BigInteger p, q;
	private BigInteger pubKey;
	private BigInteger PhiN;
	private BigInteger n;

	public RSA() {
		
		int SIZE = 8;

		p = new BigInteger(SIZE, 15, new Random());
		q = new BigInteger(SIZE, 15, new Random());

		n = p.multiply(q);

		PhiN = p.subtract(BigInteger.valueOf(1));
		PhiN = PhiN.multiply(q.subtract(BigInteger.valueOf(1)));

		do {
			pubKey = new BigInteger(2 * SIZE, new Random());

		} while ((pubKey.compareTo(PhiN) != 1) || (pubKey.gcd(PhiN).compareTo(BigInteger.valueOf(1)) != 0));

		privKey = pubKey.modInverse(PhiN);
	}

	public BigInteger getPrivKey() {
		return privKey;
	}

	public BigInteger getPubKey() {
		return pubKey;
	}

	public BigInteger getN() {
		return n;
	}
}
