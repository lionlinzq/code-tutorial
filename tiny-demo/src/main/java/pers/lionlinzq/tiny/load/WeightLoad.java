package pers.lionlinzq.tiny.load;

import java.util.ArrayList;
import java.util.Random;

public class WeightLoad {

    public static String getServer() {
        ArrayList<String> ips = new ArrayList<>();
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            for (int i = 0; i < weight; i++) {
                ips.add(ip);
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(ips.size());
        return ips.get(randomIndex);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }

}
