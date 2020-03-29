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
    void mainSuccess() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Application.main();
        bo.flush();
        String out = new String(bo.toByteArray(), Charset.defaultCharset());
        Assertions.assertEquals("Hello world!!!\n", out);
    }
}
