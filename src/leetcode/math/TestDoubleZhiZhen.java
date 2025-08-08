package leetcode.math;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDoubleZhiZhen {

    /**
     * 双指针
     * 未做：167、15、16、18、11、42、27、26、80、83、82、611、187643、674、209、3、438、567、424、76、30、86、328、
     * 160、88、475、141、142、143234、457、287
     * 已做 345、680
     */

    public static void main(String[] args) {
        TestDoubleZhiZhen test = new TestDoubleZhiZhen();
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        System.out.println(test.removeDuplicates(nums));

        String[] arr = new String[]{"a", "a,b,c"};
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String[] split = arr[i].split(",");
            for (int j = 0; j < split.length; j++) {
                List<Integer> integers = map.get(split[j]);
                if (integers == null) {
                    integers = new ArrayList<>();
                    integers.add(j);
                } else {
                    integers.add(j);
                }
                map.put(split[j], integers);
            }
        }
        System.out.println(map.toString());
    }

    // 26
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    // 27
    public int removeElement2(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int removeElement(int[] nums, int val) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[high] == val) {
                high--;
                continue;
            }
            if (nums[low] == val) {
                int tem = nums[low];
                nums[low] = nums[high];
                nums[high] = 0;
                low++;
                high--;
            }
            low++;
        }
        return low + 1;
    }

    // 680 输入：s = "abca"
    //输出：true
    public boolean validPalindrome(String s) {
        boolean res = true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char c1 = s.charAt(left);
            char c2 = s.charAt(right);
            if (c1 == c2) {
                ++left;
                --right;
            } else {
                return validPalindrome(s, left, right - 1) || validPalindrome(s, left + 1, right);
            }
        }
        return res;
    }

    public static boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    // 345 输入：s = "IceCreAm"
    //输出："AceCreIm"
    public String reverseVowels(String s) {
        String yuan = "aeiou";
        char[] charArray = s.toCharArray();
        int right = s.length() - 1;
        int left = 0;
        while (left < right) {
            if (yuan.contains(String.valueOf(charArray[left]).toLowerCase())
                    && yuan.contains(String.valueOf(charArray[right]).toLowerCase())) {
                char tem = charArray[left];
                charArray[left++] = charArray[right];
                charArray[right--] = tem;
            }
            if (!yuan.contains(String.valueOf(charArray[left]).toLowerCase())) {
                left++;
            }
            if (!yuan.contains(String.valueOf(charArray[right]).toLowerCase())) {
                right--;
            }
        }
        StringBuilder str = new StringBuilder();
        for (char c : charArray) {
            str.append(c);
        }
        return str.toString();
    }
}
