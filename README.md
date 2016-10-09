# Random Number Generator - UWB version

[![Build Status](https://travis-ci.org/vanam/RandomNumberGenerator.svg?branch=zcu)](https://travis-ci.org/vanam/RandomNumberGenerator)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/vanam/RandomNumberGenerator/blob/master/LICENSE)


This project utilize several rather simple pseudo-random number generators to generate numbers that are distributed in a given way. Program outputs theoretical and calculate statistical properties, and histogram.  

This branch contains altered version of program to conform rules enforced by project description at [The University of West Bohemia](https://www.zcu.cz/en/).

## Pseudo-random number generators

* [java.util.Random](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
* ~~[java.security.SecureRandom](https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html)~~
* ~~[Linear Congruential Generator](https://en.wikipedia.org/wiki/Linear_congruential_generator)~~

## Probability distributions

* ~~[Uniform distribution (continuous)](https://en.wikipedia.org/wiki/Uniform_distribution_(continuous))~~
* [Erlang distribution](https://en.wikipedia.org/wiki/Erlang_distribution)

## Usage
Default random number generator is `JavaRandom`. Default distribution is Erlang distribution. Default parameter *k=2*. If no parameters are provided, program generates random *lambda* parameter from 0 to 3. 

```
usage: java -jar random-number-generator.jar [<number> <lambda>]

DESCRIPTION
 <lambda>                       Erlang probability distribution parameter
 <number>                       number of values
```

### Example

```
$ java -jar random-number-generator.jar
E_teorie=2.939729
D_teorie=4.321003
E_vypocet=2.935369
D_vypocet=4.322020

Histogram
---------
# Number of values: 996684
# 3316 values outside of min/max
# Each ∎ represents a count of 1534
    0.0000 -     0.4633 [ 40758]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.09%)
    0.4633 -     0.9265 [ 92437]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (9.27%)
    0.9265 -     1.3898 [112499]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (11.29%)
    1.3898 -     1.8531 [115044]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (11.54%)
    1.8531 -     2.3163 [107774]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (10.81%)
    2.3163 -     2.7796 [ 95901]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (9.62%)
    2.7796 -     3.2429 [ 83131]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (8.34%)
    3.2429 -     3.7062 [ 70053]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (7.03%)
    3.7062 -     4.1694 [ 58119]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (5.83%)
    4.1694 -     4.6327 [ 47146]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (4.73%)
    4.6327 -     5.0960 [ 38040]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (3.82%)
    5.0960 -     5.5592 [ 30413]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (3.05%)
    5.5592 -     6.0225 [ 24036]: ∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎ (2.41%)
    6.0225 -     6.4858 [ 19049]: ∎∎∎∎∎∎∎∎∎∎∎∎ (1.91%)
    6.4858 -     6.9490 [ 14852]: ∎∎∎∎∎∎∎∎∎ (1.49%)
    6.9490 -     7.4123 [ 11607]: ∎∎∎∎∎∎∎ (1.16%)
    7.4123 -     7.8756 [  9103]: ∎∎∎∎∎ (0.91%)
    7.8756 -     8.3388 [  7022]: ∎∎∎∎ (0.70%)
    8.3388 -     8.8021 [  5455]: ∎∎∎ (0.55%)
    8.8021 -     9.2654 [  4154]: ∎∎ (0.42%)
    9.2654 -     9.7287 [  3229]: ∎∎ (0.32%)
    9.7287 -    10.1919 [  2490]: ∎ (0.25%)
   10.1919 -    10.6552 [  1877]: ∎ (0.19%)
   10.6552 -    11.1185 [  1414]:  (0.14%)
   11.1185 -    11.5817 [  1081]:  (0.11%)
```