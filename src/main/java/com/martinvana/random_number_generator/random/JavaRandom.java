package com.martinvana.random_number_generator.random;

import java.util.Random;


public class JavaRandom implements IRandom {

    private Random random;

    public JavaRandom() {
        random = new Random();
    }

    public JavaRandom(long seed) {
        random = new Random(seed);
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
