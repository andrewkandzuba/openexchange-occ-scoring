package io.openexchange.occ.scoring;

import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class Application {

    private static final Options options = buildOptions();

    public static void main(String... args) {
        var parser = new DefaultParser();
        try {
            var line = parser.parse(options, args);
            if (line.hasOption("h") || !line.hasOption("f")){
                help();
                return;
            }
            process(line.getOptionValue( "f" ));
        } catch (ParseException exp) {
            help();
        } catch (IOException e) {
            System.err.println("Failed to process the file: " + e.getMessage());
        }
    }

    private static Options buildOptions() {
        var options = new Options();
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
        var header = "Calculate the score of the list of names in an input file\n\n";
        var footer = "\nPlease report issues at andrey.kandzuba@gmail.com";
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("occ-scoring-util", header, options, footer, true);
    }

    private static void process(String path) throws IOException {
        try(FileInputStream fis =new FileInputStream(new File(path))){
            var content = IOUtils.toString(fis, Charset.defaultCharset());
            System.out.println(content);
        }
    }
}
