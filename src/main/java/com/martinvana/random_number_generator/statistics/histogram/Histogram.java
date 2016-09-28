package com.martinvana.random_number_generator.statistics.histogram;

import java.util.Arrays;

/**
 * Inspired by https://github.com/bitly/data_hacks
 */
public class Histogram extends AHistogram {

    private static final int maxHistogramLength = 75;
    private static final char star = '∎';

    private static final int defaultBuckets = 20;
    private static final double defaultMin = -10.0;
    private static final double defaultMax = 10.0;

    double [] boundaries;
    int [] bucketCounts;

    int skipped;
    double step;

    public Histogram() {
        this(defaultBuckets, defaultMin, defaultMax);
    }

    public Histogram(int buckets, double minV, double maxV) {
        super(buckets, minV, maxV);
    }

    private int getBucketIndex(double value) {
        // Is value out of bucket range?
        if (Double.compare(value, minV) < 0 || Double.compare(value, maxV) > 0){
            return -1;
        }

        return (int) Math.floor((value - minV) / step);
    }

    protected String getStringHistogram() {
        int maxBucket = 0;
        int valueCount = 0;

        for (int i = 0; i < buckets; i++) {
            int count = bucketCounts[i];

            maxBucket = Math.max(maxBucket, count);
            valueCount += count;
        }

        int bucketScale = Math.max((int) Math.ceil(maxBucket / (double)maxHistogramLength), 1);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Histogram%n"));
        stringBuilder.append(String.format("---------%n"));
        stringBuilder.append(String.format("# Number of values: %d%n", valueCount));

        if (skipped > 0){
            stringBuilder.append(String.format("# %d value%s outside of min/max%n", skipped, skipped > 1 ? "s" : ""));
        }

        stringBuilder.append(String.format("# Each %c represents a count of %d%n", star, bucketScale));

        String formatString = "%10.4f - %10.4f [%6d]: %s (%.2f%%)%n";

        double bucketMin = minV;
        double bucketMax = minV;

        for (int i = 0; i < buckets; i++) {
            bucketMin = bucketMax;
            bucketMax = boundaries[i+1];
            int bucketCount = bucketCounts[i];

            int starCount = bucketCount / bucketScale;
            stringBuilder.append(String.format(formatString, bucketMin, bucketMax, bucketCount, new String(new char[starCount]).replace('\0', star), valueCount != 0 ? 100.0 * bucketCount / valueCount : 0.0));
        }

        return stringBuilder.toString();
    }

    @Override
    public void push(double x) {
        int index = getBucketIndex(x);

        if (index != -1) {
            bucketCounts[index]++;
        } else {
            skipped++;
        }
    }

    @Override
    public void reset() {
        skipped = 0;
        Arrays.fill(bucketCounts, 0);
    }

    @Override
    public void reset(int buckets, double minV, double maxV) {
        setBuckets(buckets);
        setMinMax(minV, maxV);
        init();
    }

    @Override
    protected void init() {
        skipped = 0;
        step = (maxV - minV) / buckets;

        boundaries = new double[buckets + 1];

        // Generate boundaries
        for (int i = 0; i <= buckets; i++) {
            boundaries[i] = minV + (step * i);
        }

        bucketCounts = new int[buckets];
    }
}
