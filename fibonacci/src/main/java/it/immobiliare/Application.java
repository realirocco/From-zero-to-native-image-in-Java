package it.immobiliare;

import org.apache.commons.cli.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.execute(args);
    }

    private void execute(String[] args) {
        //long start = System.currentTimeMillis();
        try {
            CommandLineParser parser = new DefaultParser();
            Options parameter=this.getOptions();
            CommandLine cmd = parser.parse(parameter, args);
            if(cmd.hasOption("h")){
                this.printHelp(parameter);
            }

            int n = 0;
            if (cmd.hasOption("n")) {
                n = Integer.parseInt(cmd.getOptionValue("n"));
            }
            List<BigInteger> fibSequence = this.fibonacci(n);

            if (cmd.hasOption("a")) {
                System.out.println(fibSequence);
            } else if (cmd.hasOption("l")) {
                System.out.println("Fibonacci: " + fibSequence.get(fibSequence.size() - 1));
            }
        } catch (ParseException pex) {
            System.out.println("Unknow params or arg");
        }
        //System.out.println(String.format("Fibonacci execution time: %sms", System.currentTimeMillis() - start));
    }

    private void printHelp(Options parameter){
        HelpFormatter fmt = new HelpFormatter();
        fmt.printHelp("Fibonacci help", parameter);
    }

    private Options getOptions() {
        Options options = new Options();
        options.addOption("n", "number", true, "number of fibonacci sequence");
        options.addOption("a", "all-sequence", false, "print all sequence of fibonacci");
        options.addOption("l", "last", false, "print last number of fibonacci sequence");
        options.addOption("h", "help", false,"Help fibonacci application");
        return options;
    }

    private List<BigInteger> fibonacci(int n) {
        List<BigInteger> sequence = new ArrayList<>();

        if (n <= 0) {
            return sequence;
        }

        if (n == 1) {
            sequence.add(BigInteger.ZERO);
            return sequence;
        }

        sequence.add(BigInteger.ZERO);
        sequence.add(BigInteger.ONE);

        if (n > 2) {
            for (int i = 2; i < n; i++) {
                BigInteger nextNumber = sequence.get(i - 1).add(sequence.get(i - 2));
                sequence.add(nextNumber);
            }
        }

        return sequence;
    }
}