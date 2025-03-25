package utils;

import com.xuanwu.apaas.core.server.filter.SessionTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileLogUtil {
    private static Logger logger = LoggerFactory.getLogger(FileLogUtil.class);

    private final static String suffix = ".actionlog";

    private final static String charset = "UTF-8";

    //private final static LogWorker[] workers = new LogWorker[10];
    //
    //static {
    //    for (int i = 0; i < workers.length; i++) {
    //        workers[i] = new LogWorker();
    //    }
    //}

    public static void write(String log, String dir, String actionName) throws Exception {
        logger.debug("enter ide log write ......");

        String finalLog = log + "\n";
       // Runnable runnable = () -> {
            FileOutputStream fos = null;
            try {
                logger.debug("ide log write start ......");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String childDirStr = dir + "/" + sdf.format(new Date()) + "/" + SessionTokenFilter.getSession().getTenantCode();
                File childDir = new File(childDirStr);
                if (!childDir.exists()) {
                    childDir.mkdirs();
                }
                File file = new File(childDirStr + "/" + actionName + suffix);
                boolean append = false;
                if (file.exists()) {
                    append = true;
                }
                fos = new FileOutputStream(file, append);
                FileChannel channel = fos.getChannel();
                byte[] buf = finalLog.getBytes(charset);
                int length = buf.length;
                ByteBuffer bf = ByteBuffer.allocate(length);
                bf.put(buf);
                bf.flip();
                channel.write(bf);
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        //};
        //LogWorker worker = workers[Math.abs(actionName.hashCode()) % workers.length];
        //worker.addTask(runnable);
    }

    public static Map<String, Object> read(String dir, String actionName, int position, int length) throws Exception {
        //utf8编码中，中文汉字占3个字节，所以取的长度一定要是3的倍数，以免拿到半个汉字造成乱码
        length = length * 3;
        FileChannel channel = null;
        RandomAccessFile raf = null;
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String childDirStr = dir + "/" + sdf.format(new Date()) + "/" + SessionTokenFilter.getSession().getTenantCode();
            File childDir = new File(childDirStr);
            if (!childDir.exists()) {
                throw new FileNotFoundException("找不到指定文件夹:" + childDirStr);
            }
            File file = new File(childDirStr + "/" + actionName + suffix);
            if (!file.exists()) {
                throw new FileNotFoundException("找不到指定文件:" + childDirStr + "/" + actionName + suffix);
            }
            raf = new RandomAccessFile(file, "r");
            if (position == 0) {
                long size = raf.length();
                if (size >= length) {
                    position = (int) (size - length);
                }
            }
            raf.seek(position);
            channel = raf.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(length);
            length = channel.read(buf);
            length = length < 0 ? 0 : length;
            channel.close();
            buf.flip();
            String data = new String(buf.array(), 0, buf.limit(), charset);
            result.put("data", data);
            result.put("position", position + length);
            return result;
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }

    static class LogWorker {

        private final Executor executor = newLogThreadPool();

        /**
         * 得到一个初始线程数为5，最大线程数为10，空闲保留30s，等待队列容量为1000的自定义线程池
         *
         * @return
         */
        private Executor newLogThreadPool() {
            return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        }

        public void addTask(Runnable task) {
            executor.execute(task);
        }

    }


    //public static void main(String[] args) throws Exception {
    //
    //    Runnable run1 = () -> {
    //        try {
    //            for (int i = 0; i < 10000; i++) {
    //                FileLogUtil.write(String.valueOf(i) + ",", "E:/log", "测试4");
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    };
    //
    //
    //    Runnable run2 = () -> {
    //        int pos = 0;
    //        while (true) {
    //            try {
    //                Thread.sleep(100);
    //                Map<String, Object> m2 = FileLogUtil.read("E:/log", "测试4", pos, 30);
    //                pos = MapUtils.getIntValue(m2, "position");
    //                String data = MapUtils.getString(m2, "data");
    //                if (!StringUtils.isEmpty(data)) {
    //                    System.out.print(data);
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //                continue;
    //            }
    //        }
    //    };
    //
    //    new Thread(run1).start();
    //    new Thread(run2).start();
    //}

}
