package pers.lionlinzq.tiny.load;

import java.util.Random;

/**
 * 随机载荷
 *
 * @author lzq
 */
public class RandomLoad {

    public static String getServer() {
        Random random = new Random();
        int rand = random.nextInt(ServerIps.WEIGHT_LIST.size());
        return ServerIps.LIST.get(rand);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
