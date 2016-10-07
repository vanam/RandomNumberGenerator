# Random Number Generator

[![Build Status](https://travis-ci.org/vanam/RandomNumberGenerator.svg?branch=master)](https://travis-ci.org/vanam/RandomNumberGenerator)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/vanam/RandomNumberGenerator/blob/master/LICENSE)


This project utilize several rather simple pseudo-random number generators to generate numbers that are distributed in a given way. Program outputs theoretical and calculate statistical properties, and histogram.  

## Pseudo-random number generators

* [java.util.Random](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
* [java.security.SecureRandom](https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html)
* [Linear Congruential Generator](https://en.wikipedia.org/wiki/Linear_congruential_generator)

## Probability distributions

* [Uniform distribution (continuous)](https://en.wikipedia.org/wiki/Uniform_distribution_(continuous))
* [Erlang distribution](https://en.wikipedia.org/wiki/Erlang_distribution)

## Example

```
$ java -jar random-number-generator-1.0.jar -e 2 2.0
Statistical properties
----------------------
# Theory
# E  = 1.000000
# D  = 0.500000
# SD = 0.707107
#
# Calculated
# E  = 0.996433
# D  = 0.495580
# SD = 0.703974

Histogram
---------
# Number of values: 91062
# 8938 values outside of min/max
# Each ∎ represents a count of 79
    0.0000 -     0.0800 [  1193]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (1.31%)
    0.0800 -     0.1600 [  3054]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (3.35%)
    0.1600 -     0.2400 [  4209]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.62%)
    0.2400 -     0.3200 [  5098]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (5.60%)
    0.3200 -     0.4000 [  5640]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (6.19%)
    0.4000 -     0.4800 [  5722]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (6.28%)
    0.4800 -     0.5600 [  5918]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (6.50%)
    0.5600 -     0.6400 [  5885]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (6.46%)
    0.6400 -     0.7200 [  5601]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (6.15%)
    0.7200 -     0.8000 [  5292]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (5.81%)
    0.8000 -     0.8800 [  4937]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (5.42%)
    0.8800 -     0.9600 [  4706]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (5.17%)
    0.9600 -     1.0400 [  4326]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.75%)
    1.0400 -     1.1200 [  4074]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.47%)
    1.1200 -     1.2000 [  3749]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.12%)
    1.2000 -     1.2800 [  3279]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (3.60%)
    1.2800 -     1.3600 [  3086]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (3.39%)
    1.3600 -     1.4400 [  2717]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.98%)
    1.4400 -     1.5200 [  2409]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.65%)
    1.5200 -     1.6000 [  2207]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.42%)
    1.6000 -     1.6800 [  1948]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.14%)
    1.6800 -     1.7600 [  1827]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.01%)
    1.7600 -     1.8400 [  1618]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (1.78%)
    1.8400 -     1.9200 [  1370]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (1.50%)
    1.9200 -     2.0000 [  1197]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (1.31%)
```