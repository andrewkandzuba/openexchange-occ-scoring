package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class TestApplication {

    @Test
    void applicationCreationSuccess() {
        Application app = new Application();
        Assertions.assertNotNull(app);
    }

    @Test
    void mainNoArgumentsFailure() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Application.main();
        bo.flush();
        String out = new String(bo.toByteArray(), Charset.defaultCharset());
        Assertions.assertEquals("usage: occ-scoring-util [-f <FILE>] [-h]\n" +
                "Calculate the score of the list of names in an input file\n" +
                "\n" +
                " -f,--file <FILE>   The file to be processed\n" +
                " -h,--help          print this message\n" +
                "\n" +
                "Please report issues at andrey.kandzuba@gmail.com\n", out);
    }

    @Test
    void mainPrintHelp() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Application.main("--help");
        bo.flush();
        String out = new String(bo.toByteArray(), Charset.defaultCharset());
        Assertions.assertEquals("usage: occ-scoring-util [-f <FILE>] [-h]\n" +
                "Calculate the score of the list of names in an input file\n" +
                "\n" +
                " -f,--file <FILE>   The file to be processed\n" +
                " -h,--help          print this message\n" +
                "\n" +
                "Please report issues at andrey.kandzuba@gmail.com\n", out);
    }

    @Test
    void mainNoFilePathFailure() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Application.main("-f");
        bo.flush();
        String out = new String(bo.toByteArray(), Charset.defaultCharset());
        Assertions.assertEquals("usage: occ-scoring-util [-f <FILE>] [-h]\n" +
                "Calculate the score of the list of names in an input file\n" +
                "\n" +
                " -f,--file <FILE>   The file to be processed\n" +
                " -h,--help          print this message\n" +
                "\n" +
                "Please report issues at andrey.kandzuba@gmail.com\n", out);
    }
    
    @Test
    void mainSuccess() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Application.main("-f", "/tmp/names.txt");
        bo.flush();
        String out = new String(bo.toByteArray(), Charset.defaultCharset());
        Assertions.assertEquals("Hello world!!!\n", out);
    }
}
