package com.martinvana.random_number_generator.random;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class JavaSecureRandom implements IRandom {

    private SecureRandom random;

    public JavaSecureRandom() {
        random = new SecureRandom();
    }

    public JavaSecureRandom(long seed) {
        byte[] byteSeed = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(seed).array();
        random = new SecureRandom(byteSeed);
    }

    @Override
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }

    @Override
    public float nextFloat() {
        return random.nextFloat();
    }

    @Override
    public int nextInt() {
        return random.nextInt();
    }
}
