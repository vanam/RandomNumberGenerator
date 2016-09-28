package com.martinvana.random_number_generator.statistics;


abstract class AStatistics implements IStatistics {

    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }
}
