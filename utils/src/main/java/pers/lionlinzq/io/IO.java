package pers.lionlinzq.io;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;


/**
 * @author lin
 */
@Slf4j
public class IO {

    public static void main(String[] args) throws IOException {
        String currentPath = System.getProperty("user.dir");
        log.info("currentPath: {}", currentPath);
        String filePath = currentPath + "/utils/src/main/resources/测试文件.txt";
        log.info("filePath: {}", filePath);
        File file = new File(filePath);
        writeStringToFile(file, String.format("%s", System.nanoTime()));
        String read = read(file);
        log.info("read: {}", read);

        Properties properties = System.getProperties();
        String jsonString = JSON.toJSONString(properties);
        System.out.println(jsonString);
    }

    /**
     * 将字符串写入文件
     *
     * @param file   文件
     * @param string 字符串
     * @throws IOException IOEXCEPTION
     */
    public static void writeStringToFile(File file, String string) {
        OutputStream os = null;
        OutputStreamWriter osw = null;

        try {
            os = Files.newOutputStream(file.toPath());
            os = new BufferedOutputStream(os);
            osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            os.write(string.getBytes());
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String read(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);

        char[] chars = new char[1024];
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;
        // 循环取数据
        while ((length = isr.read(chars)) != -1) {
            // 将读取的内容转换成字符串
            log.debug("read读取: {}", new String(chars, 0, length));
            sb.append(chars, 0, length);
        }
        // 关闭流
        isr.close();

        return sb.toString();
    }

    public static void writeByFileWriter() throws IOException {
        InputStream is = new FileInputStream("filename.txt");
    }
}
