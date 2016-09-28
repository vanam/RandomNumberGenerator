package com.martinvana.random_number_generator.statistics;


public interface IStatistics {
    void push(double x);

    void reset();

    double getMean();

    double getVariance();

    double getStandardDeviation();
}
