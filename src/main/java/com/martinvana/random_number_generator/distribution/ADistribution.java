package com.martinvana.random_number_generator.distribution;

import com.martinvana.random_number_generator.random.IRandom;
import com.martinvana.random_number_generator.statistics.IStatistics;
import com.martinvana.random_number_generator.statistics.histogram.IHistogram;


abstract class ADistribution implements IDistribution {
    private static final int defaultHistogramBuckets = 20;

    protected IRandom random;
    protected IStatistics statistics;
    protected IHistogram histogram;

    public ADistribution(IRandom random, IStatistics statistics, IHistogram histogram) {
        this.random = random;
        this.statistics = statistics;
        this.histogram = histogram;

        statistics.reset();
        histogram.reset();
    }

    @Override
    public double getMeanCalculated() {
        return statistics.getMean();
    }

    @Override
    public double getValue() {
        double value = getValueFromDistribution();

        statistics.push(value);
        histogram.push(value);

        return value;
    }

    abstract protected double getValueFromDistribution();

    @Override
    public double getVarianceCalculated() {
        return statistics.getVariance();
    }

    @Override
    public double getStandardDeviationCalculated() {
        return statistics.getStandardDeviation();
    }

    @Override
    public double getStandardDeviationTheory() {
        return Math.sqrt(getVarianceTheory());
    }

    protected String getStringInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("E_teorie=%f%n", getMeanTheory()));
        stringBuilder.append(String.format("D_teorie=%f%n", getVarianceTheory()));
        stringBuilder.append(String.format("E_vypocet=%f%n", getMeanCalculated()));
        stringBuilder.append(String.format("D_vypocet=%f%n", getVarianceCalculated()));

        return stringBuilder.toString();
    }

    protected void initHistogram() {
        double meanTheory = getMeanTheory();
        double varianceTheory = getVarianceTheory();

        double minV = meanTheory - 2 * varianceTheory;
        double maxV = meanTheory + 2 * varianceTheory;

        initHistogram(defaultHistogramBuckets, minV, maxV);
    }

    protected void initHistogram(int histogramBuckets, double minV, double maxV) {
        histogram.reset(histogramBuckets, minV, maxV);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // Add basic information about distribution
        stringBuilder.append(getStringInfo());

        // Empty line
        stringBuilder.append(String.format("%n"));

        // Add histogram
        stringBuilder.append(histogram);

        return stringBuilder.toString();
    }

}
