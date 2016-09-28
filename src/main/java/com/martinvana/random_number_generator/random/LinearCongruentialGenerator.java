package com.martinvana.random_number_generator.random;


public class LinearCongruentialGenerator implements IRandom{

    private static final long a = 1103515245;
    private static final long c = 11;
    private static final long m = 4294967296L;

    private static final long seedUniquifier = 8682522807148012L;
    private static final double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53)

    protected long seed;

    public LinearCongruentialGenerator() {
        this(seedUniquifier ^ System.nanoTime());
    }

    public LinearCongruentialGenerator(long seed) {
        setSeed(seed);
    }

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public double nextDouble() {
        return (((long)(next(26)) << 27) + next(27)) * DOUBLE_UNIT;
    }

    @Override
    public float nextFloat() {
        return next(24) / ((float)(1 << 24));
    }

    private int next(int bits) {
        long nextseed = (a * seed + c) % m;
        setSeed(nextseed);

        return (int)(nextseed >>> (32 - bits));
    }

    @Override
    public int nextInt() {
        return next(32);
    }

}
