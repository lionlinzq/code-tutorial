package utils;

import java.net.*;

/**
 * ip工具
 * @since: 2022/9/16
 * @author: hongdahao
*/
public class IpUtil {

    public static String getLocalIp() throws SocketException {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            if (socket.getLocalAddress() instanceof Inet4Address) {
                return socket.getLocalAddress().getHostAddress();
            }
            return null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

}
