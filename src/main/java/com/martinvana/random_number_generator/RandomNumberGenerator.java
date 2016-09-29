package com.martinvana.random_number_generator;


import com.martinvana.random_number_generator.distribution.ErlangDistribution;
import com.martinvana.random_number_generator.distribution.IDistribution;
import com.martinvana.random_number_generator.random.IRandom;
import com.martinvana.random_number_generator.random.JavaRandom;
import com.martinvana.random_number_generator.random.JavaSecureRandom;
import com.martinvana.random_number_generator.statistics.IStatistics;
import com.martinvana.random_number_generator.statistics.OnlineStatistics;
import com.martinvana.random_number_generator.statistics.histogram.IHistogram;
import com.martinvana.random_number_generator.statistics.histogram.Histogram;

public class RandomNumberGenerator {

    public static void main(String[] args) {
        IRandom random = new JavaRandom();
//        IRandom random = new LinearCongruentialGenerator();
//        IRandom random = new JavaSecureRandom();
        IStatistics statistics = new OnlineStatistics();
        IHistogram histogram = new Histogram();
        IDistribution distribution = new ErlangDistribution(random, statistics, histogram, 2, 2.0);

        for (int i = 0; i < 100000; i++) {
            distribution.getValue();
        }

        System.out.println(distribution);
    }
}
