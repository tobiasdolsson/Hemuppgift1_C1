package main;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private BigInteger privKey;
	private BigInteger p, q;
	private BigInteger pubKey;
	private BigInteger phi;
	private BigInteger n;
	private int keySize;

	public RSA(int keySize) {

		this.keySize = keySize;

		p = new BigInteger(keySize, 15, new Random());
		q = new BigInteger(keySize, 15, new Random());
		n = p.multiply(q);

		phi = p.subtract(BigInteger.ONE);
		phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

		do {
			pubKey = new BigInteger(2 * keySize, new Random());

		} while ((pubKey.compareTo(phi) != 1) || (pubKey.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));{
			privKey = pubKey.modInverse(phi);
		}

		
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
