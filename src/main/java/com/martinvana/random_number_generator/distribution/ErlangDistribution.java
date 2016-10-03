package com.martinvana.random_number_generator.distribution;


import com.martinvana.random_number_generator.random.IRandom;
import com.martinvana.random_number_generator.statistics.IStatistics;
import com.martinvana.random_number_generator.statistics.histogram.IHistogram;


public class ErlangDistribution extends ADistribution {
    private int k;
    private double lambda;

    public ErlangDistribution(IRandom random, IStatistics statistics, IHistogram histogram, int k, double lambda) {
        super(random, statistics, histogram);

        setK(k);
        setLambda(lambda);

        initHistogram();
    }

    protected void initHistogram() {
        double meanTheory = getMeanTheory();
        double varianceTheory = getVarianceTheory();

        double minV = Math.max(meanTheory - 2 * varianceTheory, 0);
        double maxV = meanTheory + 2 * varianceTheory;

        super.initHistogram(minV, maxV);
    }

    private void setLambda(double lambda) {
        if (Double.compare(lambda, 0.0) <= 0) {
            throw new IllegalArgumentException(String.format("Erlang parameter 'lambda' has to be positive double, %f given.", lambda));
        }

        this.lambda = lambda;
    }

    private void setK(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException(String.format("Erlang parameter 'k' has to be positive integer, %d given.", k));
        }

        this.k = k;
    }

    @Override
    public double getMeanTheory() {
        return k / lambda;
    }


    @Override
    public double getVarianceTheory() {
        return k / (lambda * lambda);
    }

    @Override
    protected double getValueFromDistribution() {
        double randProduct = 1.0;

        for (int i = 0; i < k; i++) {
            randProduct *= random.nextDouble();
        }

        return - Math.log(randProduct) / lambda;
    }

}
