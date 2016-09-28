package com.martinvana.random_number_generator.statistics.histogram;


abstract class AHistogram implements IHistogram {
    protected int buckets;
    protected double maxV;
    protected double minV;

    public AHistogram(int buckets, double minV, double maxV) {
        setBuckets(buckets);
        setMinMax(minV, maxV);
        init();
    }

    protected abstract void init();

    protected void setBuckets(int buckets) {
        if (buckets <= 0) {
            throw new IllegalArgumentException(String.format("Histogram bucket count has to be positive integer, %d given.", buckets));
        }

        this.buckets = buckets;
    }

    protected void setMinMax(double minV, double maxV) {
        if (Double.compare(minV, maxV) >= 0) {
            throw new IllegalArgumentException(String.format("Histogram min value has to be less than max value, min=%f max=%f given.%n", minV, maxV));
        }

        this.minV = minV;
        this.maxV = maxV;
    }

    abstract protected String getStringHistogram();

    @Override
    public String toString() {
        return getStringHistogram();
    }
}
