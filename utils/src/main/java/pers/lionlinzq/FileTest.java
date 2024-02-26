package pers.lionlinzq;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        String currentPath = System.getProperty("user.dir");


        String filePath = "/Users/lin/IdeaProjects/code-tutorial/utils/src/main/resources/测试文件.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        listAllFiles(new File("/Users/lin/IdeaProjects/code-tutorial/utils/src/main"));

    }

    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        for (File file : dir.listFiles()) {
            listAllFiles(file);
        }
    }
}
