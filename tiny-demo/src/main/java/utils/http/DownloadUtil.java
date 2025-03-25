package utils.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 响应输出二进制流工具
 * @since: 2021/5/20
 * @author: hongdahao
*/
public class DownloadUtil {

    public static void download(byte[] bytes, String fileName, HttpServletResponse response) throws IOException {
        int len = bytes.length;
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode(fileName,"UTF-8"));
        ServletOutputStream sos = null;
        try{
            sos = response.getOutputStream();
            int index = 0;
            int step = 1024;
            while(index < len) {
                if (index + step >= len) {
                    step = len - index;
                } else {
                    step = 1024;
                }
                sos.write(bytes, index, step);
                index += step;
            }
        }catch(Exception e){
            throw e;
        }finally {
            if(sos != null){
                sos.close();
            }
        }
    }

}
