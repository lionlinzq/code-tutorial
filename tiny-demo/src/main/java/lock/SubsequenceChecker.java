package lock;

import java.util.HashMap;
import java.util.Map;

public class SubsequenceChecker {

    public static boolean isSubsequence(String a, String b) {
        if (a.length() > b.length()) {
            return false; // 如果a比b长，直接返回false
        }

        // 统计a的字符频率
        Map<Character, Integer> aCount = new HashMap<>();
        for (char c : a.toCharArray()) {
            aCount.put(c, aCount.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口检查b
        int windowSize = a.length();
        Map<Character, Integer> bCount = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            // 增加当前字符到窗口
            char currentChar = b.charAt(i);
            bCount.put(currentChar, bCount.getOrDefault(currentChar, 0) + 1);

            // 如果窗口大小超过a的长度，移除最左边的字符
            if (i >= windowSize) {
                char leftChar = b.charAt(i - windowSize);
                bCount.put(leftChar, bCount.get(leftChar) - 1);
                if (bCount.get(leftChar) == 0) {
                    bCount.remove(leftChar);
                }
            }

            // 如果窗口大小等于a的长度，检查字符频率是否匹配
            if (i >= windowSize - 1 && bCount.equals(aCount)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "erabcertee" +
                "")); // 输出: true (包含"abc")
        System.out.println(isSubsequence("ab", "dfbae")); // 输出: true (包含"Abc")
        System.out.println(isSubsequence("aBc", "xyzaBczyx")); // 输出: true (包含"aBc")
        System.out.println(isSubsequence("abc", "xyzazyx")); // 输出: false (不包含任何排列)
        System.out.println(isSubsequence("ABC", "xyzABCzyx")); // 输出: true (包含"ABC")
    }
}