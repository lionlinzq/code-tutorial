package pers.lionlinzq.tiny.load;

public class WeightLoadV2 {

    public static String getServer() {
        int totalWeight = 0;
        for (Integer value : ServerIps.WEIGHT_LIST.values()) {
            totalWeight += value;
        }
        return ServerIps.LIST.get(1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }

    }

}
