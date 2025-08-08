package leetcode.math;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class TestMath {

    /**
     * https://blog.csdn.net/2201_75299492/article/details/136405782?spm=1001.2014.3001.5506
     *
     * 2469、2235、2413、2160、2520、1688、1281、2427、728、2119、509、70、
     * 372、7、479、564、231、342、326、504、263、190、191、476、461、477、50、
     * 693、393、172、458、258、319、405、171、168、670、233、357、400
     * @param args
     */

    public static void main(String[] args) {
        char ch = '1';
        String a = "A";
        System.out.println((char)(25 + 'A'));
        System.out.println(convertToTitle(25));
    }

    //400
    public int findNthDigit(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        long s = (long) Math.pow(10, len - 1);
        long x = n / len - 1 + s;
        n -= (x - s + 1) * len;
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }

    //357,动态规划
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + (dp[i-1] - dp[i-2])*(10-(i-1));
        }
        return dp[n];
    }

    // 233 TODO  数位DP（动态规划）
    public static int countDigitOne(int n) {
        return 0;
    }

    //670   2736
    public static int maximumSwap(int num) {
        List<Integer> list = new ArrayList<>();//6372
        while (num != 0) {
            list.add(num % 10); num /= 10;
        }
        int n = list.size(), ans = 0;
        int[] idx = new int[n];//0022
        for (int i = 0, j = 0; i < n; i++) {
            if (list.get(i) > list.get(j)) j = i;
            idx[i] = j;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (list.get(idx[i]) != list.get(i)) {//6327
                int c = list.get(idx[i]);
                list.set(idx[i], list.get(i));
                list.set(i, c);
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) ans = ans * 10 + list.get(i);//7236
        return ans;
    }

    //168
    public static String convertToTitle(int cn) {
        StringBuilder sb = new StringBuilder();
        while (cn > 0) {
            cn--;
            sb.append((char)(cn % 26 + 'A'));
            cn /= 26;
        }
        sb.reverse();
        return sb.toString();
    }

    //171
    public static int titleToNumber(String columnTitle) {
        char[] cs = columnTitle.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans * 26 + (cs[i] - 'A' + 1);
        }
        return ans;
    }

    //405
    public static String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i --) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                System.out.println(digit);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    //319，return (int)Math.sqrt(n);
    public static int bulbSwitch(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 4) {
            return 1;
        }
        // 0-打开，1-关闭
        int[] arr = new int[n];
        while (--n > 0) {
            int flag = arr.length - n;
            if (flag == 1) {
                for (int i = 1; i < arr.length; i+=2) {
                    arr[i] = 1;
                }
            } else if (flag > 1) {
                for (int i = flag; i < arr.length; i+= flag + 1) {
                    arr[i] = arr[i] ^ 1;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                sum++;
            }
        }
        return sum;
    }

    // 258,数根，return (num - 1) % 9 + 1;
    public static int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int count = 0;
        while (num > 0) {
            count += num % 10;
            num /= 10;
        }
        return addDigits(count);
    }

    // TODO 458
    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return 0;
    }

    // 172,阶乘后的0，分解质因数2和5
    public static int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    public static boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int t = data[i], j = 7;
            int cnt = 0;
            while (j >= 0 && (((t >> j) & 1) == 1)) {
                j--;
                cnt++;
            }
            if (cnt == 1 || cnt > 4) return false;
            if (i + cnt - 1 >= n) return false;
            for (int k = i + 1; k < i + cnt; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) continue;
                return false;
            }
            if (cnt == 0) i++;
            else i += cnt;
        }
        return true;
    }
}
