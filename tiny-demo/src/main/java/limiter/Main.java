package limiter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] sales = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Main main = new Main();
        System.out.println(main.maxSales(sales));
    }


    // sales = [-2,1,-3,4,-1,2,1,-5,4] f(n)= max(f(n-1)+x,x)
    public int maxSales(int[] sales) {
        int ans = Integer.MIN_VALUE;
        int tempSum = 0;
        for (int i = 0; i < sales.length; i++) {
            tempSum = Math.max(tempSum + sales[i], sales[i]);
            ans = Math.max(ans, tempSum);
        }
        return ans;
    }

    // password = [0, 3, 30, 34, 5, 9]  "03033459"
    public String crackPassword(int[] password) {
        return "";
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        Arrays.sort(nums);
        int half = sum / 2;

        return false;
    }

}
