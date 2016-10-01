package com.martinvana.random_number_generator.random


class LinearCongruentialGeneratorTest extends GroovyTestCase {
    def random

    void setUp() {
        super.setUp()
        random = new LinearCongruentialGenerator(0)
    }

    void testNextDouble() {
        assertEquals(random.nextDouble(), 1.2312141195458537e-8)
    }

    void testNextFloat() {
        assertEquals(random.nextFloat(), 0.0f)
    }

    void testNextInt() {
        assertEquals(random.nextInt(), 11)
    }
}
