package com.martinvana.random_number_generator.distribution;


import com.martinvana.random_number_generator.random.IRandom;
import com.martinvana.random_number_generator.statistics.IStatistics;
import com.martinvana.random_number_generator.statistics.histogram.IHistogram;

public class UniformDistribution extends ADistribution {
    private static final int histogramBuckets = 10;

    private double a;
    private double b;

    public UniformDistribution(IRandom random, IStatistics statistics, IHistogram histogram, double a, double b) {
        super(random, statistics, histogram);
        setParameters(a, b);
        initHistogram();
    }

    protected void initHistogram() {
        super.initHistogram(histogramBuckets, a, b);
    }

    public void setParameters(double a, double b) {
        if (Double.compare(a, b) >= 0) {
            throw new IllegalArgumentException(String.format("Uniform distribution parameter 'b' has to be greater than parameter 'a', a=%f b=%f given.", a, b));
        }

        this.a = a;
        this.b = b;
    }

    @Override
    public double getMeanTheory() {
        return (a + b) / 2;
    }

    @Override
    public double getVarianceTheory() {
        return Math.pow(b - a, 2) / 12;
    }

    @Override
    protected double getValueFromDistribution() {
        return (b - a) * random.nextDouble() + a;
    }
}
