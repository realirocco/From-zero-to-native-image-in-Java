package it.immobiliare;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.execute(args);
    }

    private void execute(String[] args) {
        long start = System.currentTimeMillis();
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(this.getOptions(), args);
            //
            int size = 0;
            if (cmd.hasOption("s")) {
                size = Integer.parseInt(cmd.getOptionValue("s"));
            }
            //
            List<Integer> unordered = this.getUnordered(size);
            this.print(unordered, cmd);
            //
            this.bubbleSort(unordered);
            this.print(unordered, cmd);
        } catch (ParseException pex) {
            System.out.println("Unknow params or arg");
        }
        System.out.println(String.format("Bubblesort execution time: %sms", System.currentTimeMillis() - start));
    }

    private void print(List<Integer> listToPrint, CommandLine cmd) {
        if (cmd.hasOption("a")) {
            System.out.println(listToPrint);
        } else if (cmd.hasOption("fl")) {
            System.out.println(String.format("First and last: [%s...%s]", listToPrint.get(0), listToPrint.get(listToPrint.size() - 1)));
        }
    }

    private ArrayList<Integer> getUnordered(int size) {
        ArrayList<Integer> unordered = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            unordered.add(i);
        }
        Collections.shuffle(unordered);
        return unordered;
    }

    //https://stackabuse.com/bubble-sort-in-java/
    public void bubbleSort(List<Integer> list) {
        Integer temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

    private Options getOptions() {
        Options options = new Options();
        options.addOption("s", "size", true, "size of list to order");
        options.addOption("a", "all", false, "print all list");
        options.addOption("fl", "first-last", false, "print first and last number");
        return options;
    }
}