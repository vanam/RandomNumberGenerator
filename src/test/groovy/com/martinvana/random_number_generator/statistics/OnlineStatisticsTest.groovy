package com.martinvana.random_number_generator.statistics


class OnlineStatisticsTest extends GroovyTestCase {
    def onlineStatistics;

    void setUp() {
        super.setUp()
        onlineStatistics = new OnlineStatistics()
    }

    void testConstructor() {
        // No values pushed

        def expectedMean = 0
        def expectedVariance = 0
        def expectedStandardDeviation = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
        assertEquals(expectedVariance, onlineStatistics.getVariance())
        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testPush() {
        // Push one value
        onlineStatistics.push(5)

        def expectedMean = 5
        def expectedVariance = 0
        def expectedStandardDeviation = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
        assertEquals(expectedVariance, onlineStatistics.getVariance())
        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testPush1() {
        // Push two values
        onlineStatistics.push(-5)
        onlineStatistics.push(5)

        def expectedMean = 0
        def expectedVariance = 50
        def expectedStandardDeviation = 7.0710678118654755

        assertEquals(expectedMean, onlineStatistics.getMean())
        assertEquals(expectedVariance, onlineStatistics.getVariance())
        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testPush2() {
        // Push three values
        onlineStatistics.push(5)
        onlineStatistics.push(10)
        onlineStatistics.push(1000)

        def expectedMean = 338.3333333333333
        def expectedVariance = 328358.3333333334
        def expectedStandardDeviation = 573.0255957052298

        assertEquals(expectedMean, onlineStatistics.getMean())
        assertEquals(expectedVariance, onlineStatistics.getVariance())
        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testReset() {
        // Push non zero values
        onlineStatistics.push(0)
        onlineStatistics.push(5)
        onlineStatistics.push(55)
        onlineStatistics.push(-66.6)
        onlineStatistics.push(555)
        onlineStatistics.push(5555)

        // Reset
        onlineStatistics.reset();

        def expectedMean = 0
        def expectedVariance = 0
        def expectedStandardDeviation = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
        assertEquals(expectedVariance, onlineStatistics.getVariance())
        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testGetMean() {
        // Test for zero if no value is pushed
        onlineStatistics.push(0)

        def expectedMean = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
    }

    void testGetMean1() {
        // Test for zero if zero value is pushed two times
        onlineStatistics.push(0)
        onlineStatistics.push(0)

        def expectedMean = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
    }

    void testGetMean2() {
        // Test for zero if +1 and -1 value is pushed
        onlineStatistics.push(-1)
        onlineStatistics.push(1)

        def expectedMean = 0

        assertEquals(expectedMean, onlineStatistics.getMean())
    }

    void testGetMean3() {
        // Push three values (-3.5, 7, 199)
        onlineStatistics.push(-3.5)
        onlineStatistics.push(7)
        onlineStatistics.push(199)

        def expectedMean = 67.5

        assertEquals(expectedMean, onlineStatistics.getMean())
    }

    void testGetVariance() {
        // Test for zero if no value is pushed
        onlineStatistics.push(0)

        def expectedVariance = 0

        assertEquals(expectedVariance, onlineStatistics.getVariance())
    }

    void testGetVariance1() {
        // Test for zero if zero value is pushed two times
        onlineStatistics.push(0)
        onlineStatistics.push(0)

        def expectedVariance = 0

        assertEquals(expectedVariance, onlineStatistics.getVariance())
    }

    void testGetVariance2() {
        // Push +1 and -1 value
        onlineStatistics.push(-1)
        onlineStatistics.push(1)

        def expectedVariance = 2

        assertEquals(expectedVariance, onlineStatistics.getVariance())
    }

    void testGetVariance3() {
        // Push three values (-3.5, 7, 199)
        onlineStatistics.push(-3.5)
        onlineStatistics.push(7)
        onlineStatistics.push(199)

        def expectedVariance = 12996.75

        assertEquals(expectedVariance, onlineStatistics.getVariance())
    }

    void testGetStandardDeviation() {
        // Test for zero if no value is pushed
        onlineStatistics.push(0)

        def expectedStandardDeviation = 0

        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testGetStandardDeviation1() {
        // Test for zero if zero value is pushed two times
        onlineStatistics.push(0)
        onlineStatistics.push(0)

        def expectedStandardDeviation = 0

        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testGetStandardDeviation2() {
        // Push +1 and -1 value
        onlineStatistics.push(-1)
        onlineStatistics.push(1)

        def expectedStandardDeviation = Math.sqrt(2)

        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }

    void testGetStandardDeviation3() {
        // Push three values (-3.5, 7, 199)
        onlineStatistics.push(-3.5)
        onlineStatistics.push(7)
        onlineStatistics.push(199)

        def expectedStandardDeviation = Math.sqrt(12996.75)

        assertEquals(expectedStandardDeviation, onlineStatistics.getStandardDeviation())
    }
}

