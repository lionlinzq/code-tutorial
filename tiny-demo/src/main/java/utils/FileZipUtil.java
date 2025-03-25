package utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipUtil {

    public static final String targetPath = "{0}/temp";
    public static final String imgPath = "{0}/img/{1}";
    public static final String jsonPath = "{0}/json/{1}";

//    public static final String targetPath = "{0}\\temp";
//    public static final String imgPath = "{0}\\img\\{1}";
//    public static final String jsonPath = "{0}\\json\\{1}";

    /**
     * @param srcPaths   源文件路径(多)
     * @param targetPath 压缩文件保存路径
     * @description:将文件/文件夹生成指定格式的压缩文件
     */
    public static void compressedFile(String[] srcPaths, String targetPath, String zipFileName) throws Exception {
        File srcFile = null;
        File targetFile = new File(targetPath);
        //压缩目录不存在，则创建
        if (!targetFile.exists()) {
            if (!targetFile.mkdirs()) {
                throw new RuntimeException("新建文件夹失败");
            }
        }
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(targetPath + File.separator + zipFileName);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            for (String srcPath : srcPaths) {
                srcFile = new File(srcPath);
                compress(zos, srcFile, "");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (null != zos) {
                    zos.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    private static void compress(ZipOutputStream zos, File file, String baseDir) throws Exception {
        if (file.isDirectory()) {
            baseDir = file.getName() + File.separator;
            compressDir(zos, file, baseDir);
        } else if (file.isFile()) {
            compressFile(zos, file, baseDir);
        }
    }

    private static void compressDir(ZipOutputStream zos, File file, String baseDir) throws Exception {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            /**递归调用*/
            compress(zos, files[i], baseDir);
        }
    }

    private static void compressFile(ZipOutputStream zos, File file, String baseDir) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        zos.putNextEntry(new ZipEntry(baseDir + file.getName()));
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = bis.read(buffer)) != -1) {
            zos.write(buffer, 0, len);
        }
        bis.close();
    }
}


