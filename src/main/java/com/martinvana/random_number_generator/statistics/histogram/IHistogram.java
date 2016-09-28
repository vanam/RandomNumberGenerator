package com.martinvana.random_number_generator.statistics.histogram;

public interface IHistogram {
    void push(double x);

    void reset();

    void reset(int buckets, double minV, double maxV);
}
