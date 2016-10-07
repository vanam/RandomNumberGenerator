package com.martinvana.random_number_generator;


import com.martinvana.random_number_generator.distribution.ErlangDistribution;
import com.martinvana.random_number_generator.distribution.IDistribution;
import com.martinvana.random_number_generator.distribution.UniformDistribution;
import com.martinvana.random_number_generator.random.IRandom;
import com.martinvana.random_number_generator.random.JavaRandom;
import com.martinvana.random_number_generator.random.JavaSecureRandom;
import com.martinvana.random_number_generator.random.LinearCongruentialGenerator;
import com.martinvana.random_number_generator.statistics.IStatistics;
import com.martinvana.random_number_generator.statistics.OnlineStatistics;
import com.martinvana.random_number_generator.statistics.histogram.IHistogram;
import com.martinvana.random_number_generator.statistics.histogram.Histogram;
import java.util.Arrays;
import org.apache.commons.cli.*;

public class RandomNumberGenerator {
    private static final String RANDOM_GENERATOR_JAVA_RANDOM = "java-random";
    private static final String RANDOM_GENERATOR_JAVA_SECURE_RANDOM = "java-secure-random";
    private static final String RANDOM_GENERATOR_LINEAR_CONGRUENTIAL_GENERATOR = "linear-congruential-generator";

    private static final String PROBABILITY_DISTRIBUTION_UNIFORM = "uniform";
    private static final String PROBABILITY_DISTRIBUTION_ERLANG = "erlang";

    private static final String [] randomGenerators = {
            RANDOM_GENERATOR_JAVA_RANDOM,
            RANDOM_GENERATOR_JAVA_SECURE_RANDOM,
            RANDOM_GENERATOR_LINEAR_CONGRUENTIAL_GENERATOR
    };

    private static final String [] probabilityDistributions = {
            PROBABILITY_DISTRIBUTION_UNIFORM,
            PROBABILITY_DISTRIBUTION_ERLANG
    };

    private static int numberOfValues = 1000000;
    private static String randomGenerator = RANDOM_GENERATOR_JAVA_RANDOM;
    private static String probabilityDistribution = PROBABILITY_DISTRIBUTION_ERLANG;
    private static String [] probabilityDistributionArgs = { "2", "1.0"};

    public static void main(String [] args) {
        Options options = setOptions();
        IDistribution distribution = null;

        try {
            processArgs(options, args);
            distribution = init();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options);
            System.exit(1);
        }

        run(distribution);
    }

    private static IDistribution init() throws ParseException {
        IRandom random;

        switch (randomGenerator) {
            case RANDOM_GENERATOR_JAVA_SECURE_RANDOM:
                random = new JavaSecureRandom();
                break;
            case RANDOM_GENERATOR_LINEAR_CONGRUENTIAL_GENERATOR:
                random = new LinearCongruentialGenerator();
                break;
            case RANDOM_GENERATOR_JAVA_RANDOM:
                random = new JavaRandom();
                break;
            default:
                random = new JavaRandom();
                break;
        }

        IStatistics statistics = new OnlineStatistics();
        IHistogram histogram = new Histogram();

        IDistribution distribution = null;

        switch (probabilityDistribution) {
            case PROBABILITY_DISTRIBUTION_UNIFORM:
                double a, b;

                a = parseDouble(PROBABILITY_DISTRIBUTION_UNIFORM, "a", probabilityDistributionArgs[0]);
                b = parseDouble(PROBABILITY_DISTRIBUTION_UNIFORM, "b", probabilityDistributionArgs[1]);

                distribution = new UniformDistribution(random, statistics, histogram, a, b);
                break;
            case PROBABILITY_DISTRIBUTION_ERLANG:
                int k;
                double lambda;

                k = parseInteger(PROBABILITY_DISTRIBUTION_ERLANG, "k", probabilityDistributionArgs[0]);
                lambda = parseDouble(PROBABILITY_DISTRIBUTION_ERLANG, "lambda", probabilityDistributionArgs[1]);

                distribution = new ErlangDistribution(random, statistics, histogram, k, lambda);
                break;
        }

        return distribution;
    }

    private static int parseInteger(String distributionName, String parameterName, String value) throws ParseException {
        int val;

        try {
            val = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Double value expected for parameter '" + parameterName + "' in " + distributionName + " distribution, '" + value + "' given\n");
        }

        return val;
    }

    private static double parseDouble(String distributionName, String parameterName, String value) throws ParseException {
        double val;

        try {
            val = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Double value expected for parameter '" + parameterName + "' in " + distributionName + " distribution, '" + value + "' given\n");
        }

        return val;
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(120, "java -jar random-number-generator.jar", "\nDESCRIPTION", options, null, true);
    }

    private static void processArgs(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        cmd = parser.parse(options, args);

        // Parse help
        if (cmd.hasOption("help")) {
            printHelp(options);
            System.exit(0);
        }

        // Parse number of values
        if (cmd.hasOption("n")) {
            String value = cmd.getOptionValue("n");

            try {
                numberOfValues = Integer.parseInt(value);

                if (numberOfValues <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                throw new ParseException("Number of values has to be positive integer, '" + value + "' given\n");
            }

        }

        // Parse random number generator
        if (cmd.hasOption("random")) {
            randomGenerator = cmd.getOptionValue("random");

            if (!Arrays.asList(randomGenerators).contains(randomGenerator)) {
                throw new ParseException("Unknown random number generator, '" + randomGenerator + "' given.");
            }
        }

        int probabilityDistributionCount = 0;

        // Parse probability distribution
        for(String distribution : probabilityDistributions) {
            if (cmd.hasOption(distribution)) {
                probabilityDistribution = distribution;
                probabilityDistributionArgs = cmd.getOptionValues(distribution);
                probabilityDistributionCount++;
            }
        }

        if (probabilityDistributionCount > 1) {
            throw new ParseException("Too many probability distributions given.");
        }
    }

    private static void run(IDistribution distribution) {
        for (int i = 0; i < numberOfValues; i++) {
            distribution.getValue();
        }

        System.out.println(distribution);
    }

    private static Options setOptions() {
        Options options = new Options();
        Option option;

        // Help
        option = new Option("h", "help", false, "display help");
        options.addOption(option);

        // Number of values
        option = new Option("n", true, "number of values");
        option.setArgName("number");
        options.addOption(option);

        // Random generator
        option = new Option("r", "random", true, "random number generator\n" + Arrays.toString(randomGenerators));
        option.setArgName("generator_name");
        options.addOption(option);

        // Uniform probability distribution
        option = new Option("u", PROBABILITY_DISTRIBUTION_UNIFORM, true, "Uniform probability distribution");
        option.setArgs(2);
        option.setArgName("a> <b");
        options.addOption(option);

        // Erlang probability distribution
        option = new Option("e", PROBABILITY_DISTRIBUTION_ERLANG, true, "Erlang probability distribution");
        option.setArgs(2);
        option.setArgName("k> <lambda");
        options.addOption(option);

        return options;
    }
}
