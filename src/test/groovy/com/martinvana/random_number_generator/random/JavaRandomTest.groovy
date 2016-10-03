package com.martinvana.random_number_generator.random


class JavaRandomTest extends GroovyTestCase {
    def javaRandom
    def random

    void setUp() {
        super.setUp()
        javaRandom = new JavaRandom(0)
        random = new Random(0)
    }

    void testNextDouble() {
        def expectedValue = random.nextDouble();
        assertEquals(expectedValue, javaRandom.nextDouble())
    }

    void testNextFloat() {
        def expectedValue = random.nextFloat();
        assertEquals(expectedValue, javaRandom.nextFloat())
    }

    void testNextInt() {
        def expectedValue = random.nextInt();
        assertEquals(expectedValue, javaRandom.nextInt())
    }
}
