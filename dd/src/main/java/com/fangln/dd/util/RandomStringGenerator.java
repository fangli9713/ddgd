package com.fangln.dd.util;

import java.security.SecureRandom;
import java.util.Random;
/**
 * 随机字符产生器
 * @author HuangFu
 *
 */
public class RandomStringGenerator {
	private static final char[] PRINTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679"
			.toCharArray();
	
	private static final char[] NUMBER_CHARACTERS = "012345679"
			.toCharArray();

	private static final int DEFAULT_MAX_RANDOM_LENGTH = 35;

	private final SecureRandom randomizer;
	private final char[] seed;
	private final int randomLength;

	private static RandomStringGenerator defaultInstance = new RandomStringGenerator();

	public static RandomStringGenerator getDefaultInstance() {
		return defaultInstance;
	}

	public RandomStringGenerator() {
		this(DEFAULT_MAX_RANDOM_LENGTH);
	}

	public RandomStringGenerator(int randomLength) {
		this(randomLength, PRINTABLE_CHARACTERS);
	}

	public RandomStringGenerator(int randomLength, char[] seedOptions) {
		randomizer = new SecureRandom();
		if (randomLength < 1)
			randomLength = 1;
		this.randomLength = randomLength;
		if (seedOptions == null || seedOptions.length == 0)
			seedOptions = PRINTABLE_CHARACTERS;
		this.seed = seedOptions;
	}

	public int getDefaultRandomLength() {
		return randomLength;
	}

	public String getNewString() {
		return newRandomString(randomizer, randomLength, seed);
	}

	public String getNewString(int length, char[] seeds) {
		return newRandomString(randomizer, length, seeds);
	}

	public String getNewNumberString(int length) {
		return newRandomString(randomizer, length, NUMBER_CHARACTERS);
	}
	
	public String getNewString(int length) {
		return newRandomString(randomizer, length, seed);
	}

	public byte[] getRandomBytes(int length) {
		byte[] rb = new byte[length];
		randomizer.nextBytes(rb);
		return rb;
	}

	private static String convertBytesToString(byte[] rb, char[] seedchars) {
		final char[] output = new char[rb.length];
		for (int i = 0; i < rb.length; i++) {
			int index = (rb[i] & 0xff) % seedchars.length; // Math.abs(rb[i] %
															// seedchars.length);
			output[i] = seedchars[index];
		}
		return new String(output);
	}

	public static String newRandomString(Random random, int length, char[] seed) {
		if (length < 0)
			length = 0;
		byte[] rb = new byte[length];
		random.nextBytes(rb);
		if (seed == null || seed.length == 0)
			seed = PRINTABLE_CHARACTERS;
		return convertBytesToString(rb, seed);
	}
}
