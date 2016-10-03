package com.martinvana.random_number_generator.statistics.histogram


class HistogramTest extends GroovyTestCase {

    void testConstructor() {
        // Default constructor
        def histogram = new Histogram();

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 0\n" +
                "# Each ∎ represents a count of 1\n" +
                "  -10.0000 -    -9.0000 [     0]:  (0.00%)\n" +
                "   -9.0000 -    -8.0000 [     0]:  (0.00%)\n" +
                "   -8.0000 -    -7.0000 [     0]:  (0.00%)\n" +
                "   -7.0000 -    -6.0000 [     0]:  (0.00%)\n" +
                "   -6.0000 -    -5.0000 [     0]:  (0.00%)\n" +
                "   -5.0000 -    -4.0000 [     0]:  (0.00%)\n" +
                "   -4.0000 -    -3.0000 [     0]:  (0.00%)\n" +
                "   -3.0000 -    -2.0000 [     0]:  (0.00%)\n" +
                "   -2.0000 -    -1.0000 [     0]:  (0.00%)\n" +
                "   -1.0000 -     0.0000 [     0]:  (0.00%)\n" +
                "    0.0000 -     1.0000 [     0]:  (0.00%)\n" +
                "    1.0000 -     2.0000 [     0]:  (0.00%)\n" +
                "    2.0000 -     3.0000 [     0]:  (0.00%)\n" +
                "    3.0000 -     4.0000 [     0]:  (0.00%)\n" +
                "    4.0000 -     5.0000 [     0]:  (0.00%)\n" +
                "    5.0000 -     6.0000 [     0]:  (0.00%)\n" +
                "    6.0000 -     7.0000 [     0]:  (0.00%)\n" +
                "    7.0000 -     8.0000 [     0]:  (0.00%)\n" +
                "    8.0000 -     9.0000 [     0]:  (0.00%)\n" +
                "    9.0000 -    10.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testConstructor1() {
        // Zero buckets are not allowed
        def msg = shouldFail IllegalArgumentException, {
            new Histogram(0, 1, 2);
        }
        assert msg.contains('Histogram bucket count has to be positive integer, 0 given.')
    }

    void testConstructor2() {
        // Negative buckets are not allowed
        def msg = shouldFail IllegalArgumentException, {
            new Histogram(-10, 1, 2);
        }
        assert msg.contains('Histogram bucket count has to be positive integer, -10 given.')
    }

    void testConstructor3() {
        // Positive number of buckets should not thrown an exception
        new Histogram(1, 1, 2);
    }

    void testConstructor4() {
        // Min cannot be equal to max
        def msg = shouldFail IllegalArgumentException, {
            new Histogram(1, 1, 1);
        }
        assert msg.contains('Histogram min value has to be less than max value, min=1.000000 max=1.000000 given.')
    }

    void testConstructor5() {
        // Min which is less than max should not throw an exception
        new Histogram(1, 1, 1.1);
    }

    void testGetStringHistogram() {
        // Odd number of buckets
        def histogram = new Histogram(5, -1, 1);

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 0\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.6000 [     0]:  (0.00%)\n" +
                "   -0.6000 -    -0.2000 [     0]:  (0.00%)\n" +
                "   -0.2000 -     0.2000 [     0]:  (0.00%)\n" +
                "    0.2000 -     0.6000 [     0]:  (0.00%)\n" +
                "    0.6000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testGetStringHistogram1() {
        // Even number of buckets
        def histogram = new Histogram(4, -1, 1);

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 0\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     0]:  (0.00%)\n" +
                "   -0.5000 -     0.0000 [     0]:  (0.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testPush() {
        def histogram = new Histogram(4, -1, 1);
        // Push one value
        histogram.push(-0.5)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 1\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     0]:  (0.00%)\n" +
                "   -0.5000 -     0.0000 [     1]: ∎ (100.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testPush1() {
        def histogram = new Histogram(4, -1, 1);
        // Push two values
        histogram.push(-0.5)
        histogram.push(-0.51)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 2\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     1]: ∎ (50.00%)\n" +
                "   -0.5000 -     0.0000 [     1]: ∎ (50.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testPush2() {
        def histogram = new Histogram(4, -1, 1);
        // Max value is not in histogram bucket
        histogram.push(1)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 0\n" +
                "# 1 value outside of min/max\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     0]:  (0.00%)\n" +
                "   -0.5000 -     0.0000 [     0]:  (0.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testPush3() {
        def histogram = new Histogram(4, -1, 1);
        // Min value is in histogram bucket
        histogram.push(-1)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 1\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     1]: ∎ (100.00%)\n" +
                "   -0.5000 -     0.0000 [     0]:  (0.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testPush4() {
        def histogram = new Histogram(4, -1, 1);
        // Push two values in same bucket
        histogram.push(-0.51)
        histogram.push(-1)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 2\n" +
                "# Each ∎ represents a count of 1\n" +
                "   -1.0000 -    -0.5000 [     2]: ∎∎ (100.00%)\n" +
                "   -0.5000 -     0.0000 [     0]:  (0.00%)\n" +
                "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testReset() {
        def histogram = new Histogram(4, -1, 1);

        // Push two values
        histogram.push(-0.51)
        histogram.push(-1)

        // Reset
        histogram.reset()

        def expectedOutput =
                "Histogram\n" +
                        "---------\n" +
                        "# Number of values: 0\n" +
                        "# Each ∎ represents a count of 1\n" +
                        "   -1.0000 -    -0.5000 [     0]:  (0.00%)\n" +
                        "   -0.5000 -     0.0000 [     0]:  (0.00%)\n" +
                        "    0.0000 -     0.5000 [     0]:  (0.00%)\n" +
                        "    0.5000 -     1.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testReset1() {
        def histogram = new Histogram(4, -1, 1);

        // Push two values
        histogram.push(-0.51)
        histogram.push(-1)
        histogram.push(100)

        // Reset to different bucket count and min/max
        histogram.reset(3, 6, 9)

        def expectedOutput =
                "Histogram\n" +
                "---------\n" +
                "# Number of values: 0\n" +
                "# Each ∎ represents a count of 1\n" +
                "    6.0000 -     7.0000 [     0]:  (0.00%)\n" +
                "    7.0000 -     8.0000 [     0]:  (0.00%)\n" +
                "    8.0000 -     9.0000 [     0]:  (0.00%)\n"
        assertEquals(expectedOutput, histogram.getStringHistogram())
    }

    void testToString() {
        def histogram = new Histogram(4, -1, 1);

        // An output of toString() is the same as an output of getStringHistogram()
        assertEquals(histogram.getStringHistogram(), histogram.toString())
    }
}
