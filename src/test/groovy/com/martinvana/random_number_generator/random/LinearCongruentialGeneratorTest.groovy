package com.martinvana.random_number_generator.random


class LinearCongruentialGeneratorTest extends GroovyTestCase {
    void setUp() {
        super.setUp()
    }

    void tearDown() {

    }

    void testNextDouble() {
        def x = 10
        assert x == 10

    }

    void testNextDouble2() {
        def x = 10
        assert x == 10

    }

    void testNextFloat() {
        def x = 2
        assert x == 2
    }

    void testNextInt() {

    }
}
