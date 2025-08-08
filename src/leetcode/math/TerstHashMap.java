package leetcode.math;

import jdk.nashorn.internal.runtime.ListAdapter;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class TerstHashMap {

    /**
     * 未做：128、532、166、466、138、1、167、599、219.217、220350、554、609、454、18、560、523、525、594
     */

    public static void main(String[] args) {
        TerstHashMap test = new TerstHashMap();
        System.out.println(test.isIsomorphic("paper", "title"));
    }

    // 205
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        Map<Object, Integer> map1 = new HashMap<>();
        for (Integer i = 0; i < s.length(); i++) {
            if (map.put(s.charAt(i), i) != map1.put(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }

    // 290
    public boolean wordPattern2(String pattern, String str) {
        String[] split = str.split(" ");
        if (pattern.length() != split.length) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map.put(pattern.charAt(i), i) != map.put(split[i], i)) {
                System.out.println(map.put(pattern.charAt(i), i));
                System.out.println(map.put(split[i], i));
                return false;
            }
        }
        return true;
    }

    // 290
    public boolean wordPattern(String pattern, String s) {
        Map<String, Set<Character>> map = new HashMap<>();
        String[] split = s.split(" ");
        for (int i = 0; i < pattern.length(); i++) {
            Set<Character> strings = map.get(split[i]);
            if (strings == null) {
                Set<Character> set = new HashSet<>();
                set.add(pattern.charAt(i));
                map.put(split[i], set);
            } else {
                strings.add(pattern.charAt(i));
                map.put(split[i], strings);
            }
        }
        for (String string : map.keySet()) {
            Set<Character> characters = map.get(string);
            if (characters.size() != 1) {
                return false;
            }
        }
        return true;
    }

    // 500
    public String[] findWords(String[] words) {
        String one = "qwertyuiop";
        String two = "asdfghjkl";
        String three = "zxcvbnm";
        List<String> list = new ArrayList<>();
        String row = "";
        for (String word : words) {
            if (one.contains(String.valueOf(word.charAt(0)).toLowerCase())) {
                row = one;
            } else if (two.contains(String.valueOf(word.charAt(0)).toLowerCase())) {
                row = two;
            } else {
                row = three;
            }
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (!row.contains(String.valueOf(c))) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(word);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    // 202 输入：n = 19
    //输出：true
    //解释：
    //12 + 92 = 82
    //82 + 22 = 68
    //62 + 82 = 100
    //12 + 02 + 02 = 1
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = bitSquare(slow);
            fast = bitSquare(fast);
            fast = bitSquare(fast);
        } while (slow != fast);
        return slow == 1;
    }

    public static int bitSquare(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n /= 10;
        }
        return sum;
    }

    //349
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        for (int i : nums2) {
            if (list1.contains(i)) {
                set.add(i);
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // 633
    public boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        for (int i = 0; i <= max; i++) {
            int b = (int)Math.sqrt(c - i * i);
            if (i * i + b * b == c) return true;
        }
        return false;
    }
}
