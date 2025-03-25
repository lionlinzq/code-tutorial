package utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩、解压工具类
 * @since: 2021/5/27
 * @author: hongdahao
*/
public class DaFileZipUtil {

    /**
     * @param zipFile 待解压文件（绝对路径）
     * @param descDir 解压的目标路径
    */
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        //解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(zipFile, Charset.forName("UTF-8"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\\\", "/");
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            //输出文件路径信息
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
    }


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
            baseDir += file.getName() + File.separator;
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


