package io.openexchange.occ.scoring;

import org.apache.commons.cli.*;

public class Application {

    private static final Options options = buildOptions();

    public static void main(String... args) {

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h") || !line.hasOption("f")){
                help();
                return;
            }
            System.out.println("Hello world!!!");
        } catch (ParseException exp) {
            help();
        }
    }

    private static Options buildOptions() {
        Options options = new Options();
        options.addOption(Option.builder("f")
                .longOpt("file")
                .desc("The file to be processed")
                .hasArg()
                .argName("FILE")
                .build());
        options.addOption("h", "help", false, "print this message");
        return options;
    }

    private static void help(){
        String header = "Calculate the score of the list of names in an input file\n\n";
        String footer = "\nPlease report issues at andrey.kandzuba@gmail.com";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("occ-scoring-util", header, options, footer, true);
    }
}
