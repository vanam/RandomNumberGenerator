package com.martinvana.random_number_generator.distribution;


import java.util.List;

public interface IDistribution {
    double getMeanTheory();

    double getMeanCalculated();

    double getValue();

    double getVarianceTheory();

    double getVarianceCalculated();

    double getStandardDeviationTheory();

    double getStandardDeviationCalculated();

}
