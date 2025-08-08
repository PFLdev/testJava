package leetcode.math;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.util.Arrays;

public class TestString {

    /**
     * 520、125、14、34、58、344、541、557、151、387、389、383、242、49、451、3、、8..13、22、293、3、
     * 8、32、824、号2、83、86、 7、218. 43、 306423、657、551、696、482、6、68、28、686、459、214、5、647
     * 中等：34
     */

    public static void main(String[] args) {
        TestString test = new TestString();
        String str = "Mr Ding";
        System.out.println(test.reverseWords(str));
    }

    //557 输入：s = "Let's take LeetCode contest"
    //输出："s'teL ekat edoCteeL tsetnoc"
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] split = s.split(" ");
        for (String string : split) {
            String s1 = reverseString(string.toCharArray());
            res.append(s1).append(" ");
        }
        return res.toString().substring(0, res.toString().length() - 1);
    }

    //541 反转字符串2
    // 输入：s = "abcdefg", k = 2
    // 输出："bacdfeg"
    public String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i += k * 2) {
            reverse(chars, i, Math.min(i + k, n) - 1);
        }
        return new String(chars);
    }

    public static void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tem = s[left];
            s[left++] = s[right];
            s[right--] = tem;
        }
    }
    public String reverseStr(String s, int k) {
        int slen = s.length();
        String res = "";
        if (slen < k) {
            res = reverseString(s.toCharArray());
        } else {
            if (slen < 2 * k) {
                String substring = s.substring(0, k);
                String aftStr = s.substring(k);
                res = reverseString(substring.toCharArray()).concat(aftStr);
            } else {
                String befStr = s.substring(0, 2 * k);
                String aftStr = s.substring(2 * k);
                String revBefStr = reverseString(befStr.substring(0, k).toCharArray()).concat(befStr.substring(k, 2 * k));
                String revAftStr = reverseStr(aftStr, k);
                res = revBefStr.concat(revAftStr);
            }
        }
        return res;
    }

    // 344 反转字符串   = ["h","e","l","l","o"]
    public static String reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tem = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tem;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            res.append(s[i]);
        }
        return res.toString();
    }

    // 58 s = "   fly me   to   the moon  "
    public int lengthOfLastWord(String s) {
        int len = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (!String.valueOf(s.charAt(i)).equals(" ")) {
                if (flag) {
                    len = 0;
                }
                len++;
                flag = false;
            } else {
                flag = true;
            }
        }
        return len;
    }

    public int lengthOfLastWord2(String s) {
        // 反向遍历
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!String.valueOf(s.charAt(i)).equals(" ")) {
                len++;
            } else if (len > 0) {
                break;
            }
        }
        return len;
    }

    // 14 strs = ["flower","flow","flight"]
    public String longestCommonPrefix(String[] strs) {
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < len) {
                len = strs[i].length();
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char tem = strs[0].charAt(i);
            boolean isE = true;
            for (int j = 1; j < strs.length; j++) {
                if (tem != strs[j].charAt(i)) {
                    isE = false;
                }
            }
            if (isE) {
                res.append(tem);
            } else {
                break;
            }
        }
        return res.toString();
    }

    //125
    public boolean isPalindrome(String s) {
        String s1 = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        System.out.println(s1);
        System.out.println(s1.length());
        boolean res = true;
        for (int i = 0; i < s1.length() / 2; i++) {
            if (s1.charAt(i) != s1.charAt(s1.length() - 1 - i)) {
                res = false;
            }
        }
        return res;
    }

    // 520
    public boolean detectCapitalUse2(String word) {
        return word.equals(word.toUpperCase())||word.substring(1).equals(word.substring(1).toLowerCase());
    }

    public boolean detectCapitalUse(String word) {
        int length = word.length();
        char first = word.charAt(0);
        if (Character.isUpperCase(first)) {
            int daxie = 0;
            int xiaoxie = 0;
            for (int i = 1; i < length; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    daxie++;
                } else {
                    xiaoxie++;
                }
            }
            if (daxie == 0 || xiaoxie == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 1; i < length; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
