package com.martinvana.random_number_generator.random


class LinearCongruentialGeneratorTest extends GroovyTestCase {
    def random

    void setUp() {
        super.setUp()
        random = new LinearCongruentialGenerator(0)
    }

    void testNextDouble() {
        def expectedValue = 1.2312141195458537e-8;
        assertEquals(expectedValue, random.nextDouble())
    }

    void testNextFloat() {
        def expectedValue = 0.0f;
        assertEquals(expectedValue, random.nextFloat())
    }

    void testNextInt() {
        def expectedValue = 11;
        assertEquals(expectedValue, random.nextInt())
    }
}
