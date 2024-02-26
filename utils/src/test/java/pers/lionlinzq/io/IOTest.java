package pers.lionlinzq.io;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Slf4j
public class IOTest {

    @Test
    public void getUserDir() {
        Properties properties = System.getProperties();
        String userDir = properties.getProperty("user.dir");
        log.info("userDir is {}", userDir);
        String jsonString = JSON.toJSONString(properties);
        System.out.println(jsonString);
    }

    @Test
    public void testMain() throws Exception {
        // Setup
        // Run the test
        IO.main(new String[]{"args"});

        // Verify the results
    }

    @Test
    public void testMain_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> IO.main(new String[]{"args"}));
    }

    @Test
    public void testWriteStringToFile() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        IO.writeStringToFile(file, "string");

        // Verify the results
    }

    @Test
    public void testRead() throws Exception {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final String result = IO.read(file);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testRead_ThrowsIOException() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        assertThrows(IOException.class, () -> IO.read(file));
    }

    @Test
    public void writeByFileWriter() {
        // FileWriter fileWriter = new FileWriter();
    }
}
