package com.martinvana.random_number_generator.random


class JavaRandomTest extends GroovyTestCase {
    def random

    void setUp() {
        super.setUp()
        random = new JavaRandom(0)
    }

    void testNextDouble() {
        assertEquals(random.nextDouble(), 0.730967787376657)
    }

    void testNextFloat() {
        assertEquals(random.nextFloat(), 0.73096776f)
    }

    void testNextInt() {
        assertEquals(random.nextInt(), -1155484576)
    }
}
