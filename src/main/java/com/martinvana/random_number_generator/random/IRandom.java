package com.martinvana.random_number_generator.random;


public interface IRandom {
    void setSeed(long seed);
    double nextDouble();
    float nextFloat();
    int nextInt();
}
