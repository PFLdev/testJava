package leetcode.math;

import org.omg.CORBA.ARG_OUT;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestArray {
    long INF = (long)-1e18;

    /**
     * 数组相关
     * 2011、1929、1720、2574、lcp01、lcp06-->1365、1732、1464、2496、1979、485、495、414、628、645、697、448、
     * 442、41、274、453、665、283、118、119、661、598、419、189、396、54、59、498、566、48、73、289、303、304、238
     */

    /**
     * 中等：442，274，453，665
     * 困难：41
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,0,0,12};
        List<List<Integer>> generate = generate(1);
        System.out.println(generate.toString());
    }

    //661
    public int[][] imageSmoother(int[][] img) {
        return null;
    }

    //119
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        list.add(list0);
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        list.add(list1);
        if (rowIndex == 0) {
            return list0;
        }
        if (rowIndex == 1) {
            return list1;
        }
        for (int i = 2; i <= rowIndex; i++) {
            List<Integer> listTem = new ArrayList<>();
            List<Integer> integers = list.get(i - 1);
            listTem.add(integers.get(0));
            for (int j = 1; j < i; j++) {
                listTem.add(j, integers.get(j - 1) + integers.get(j));
            }
            listTem.add(integers.get(integers.size() - 1));
            if (i == rowIndex) {
                return listTem;
            }
            list.add(listTem);
        }
        return new ArrayList<>();
    }

    //118
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        Integer[] one = new Integer[]{1};
        list.add(Arrays.asList(one));
        int len = 1;
        while (len < numRows) {
            List<Integer> integers = list.get(len - 1);
            int len2 = integers.size();
            List<Integer> temList = new ArrayList<>();
            temList.add(integers.get(0));
            for (int i = 1; i < len2; i++) {
                int num = integers.get(i - 1) + integers.get(i);
                temList.add(num);
            }
            temList.add(integers.get(integers.size() - 1));
            list.add(temList);
            len ++;
        }
        return list;
    }

    //283
    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int tem = nums[left];
        nums[left] = nums[right];
        nums[right] = tem;
    }

    //448
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[nums[i]]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (res[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }

    //697
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }

    //645
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] cnts = new int[n + 1];
        for (int x : nums) cnts[x]++;
        int[] ans = new int[2];
        for (int i = 1; i <= n; i++) {
            if (cnts[i] == 0) ans[1] = i;
            if (cnts[i] == 2) ans[0] = i;
        }
        return ans;
    }

    //628
    /**
     * 解题思路
     * 排序法：数组可以分为三种情况，第一是都为正数，第二是都为负数，第三是有正有负（分为（1）只有一个负数（2）有两个及以上的负数）
     * 都为正数：乘积最大值为排序数组最后三个数相乘
     * 都为负数：乘积最大值为排序数组最后三个数相乘
     * 有正有负：（1）乘积最大值为排序数组最后三个数相乘
     * （2）乘积最大值为排序数组前两个负数与数组最后一个正数相乘
     * **整理一下上面的四种情况：**可以归纳成取max（排序数组最后三个数相乘，排序数组前两个负数与数组最后一个正数相乘）
     * 不排序方法：通过上面对排序法的分析，我们可以看出，实际上我们只要找到数组的第一大的值，第二大的值，第三大的值，第一小的值和第 二小的值即可。所以我们只需要遍历一边数组，即可找到这些值（具体实现看代码注释）！
     */
    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    //414
    public int thirdMax2(int[] nums) {
        long a = INF, b = INF, c = INF;
        for (int x : nums) {
            if (x > a) {
                c = b; b = a; a = x;
            } else if (x < a && x > b) {
                c = b; b = x;
            } else if (x < b && x > c) {
                c = x;
            }
        }
        return c != INF ? (int)c : (int)a;
    }
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 3) {
            return nums[n-1];
        }
        int res = nums[n-1];
        int count = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (res != nums[i] && count < 3) {
                res = nums[i];
                count++;
            }
        }
        return count < 3 ? nums[n - 1] : res;
    }

    //495
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i] + duration - 1 < timeSeries[i + 1]) {
                sum += duration;
            } else {
                sum += timeSeries[i + 1] - timeSeries[i];
            }
        }
        return sum + duration;
    }

    //485,nums = [1,1,0,1,1,1]
    public static int findMaxConsecutiveOnes(int[] nums) {
        int sum = 0;
        int pre = 0;
        for (int num : nums) {
            if (num == 1) {
                sum++;
            } else {
                if (sum > pre) {
                    pre = sum;
                }
                sum = 0;
            }
        }
        return Math.max(sum, pre);
    }

    //1979
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1];
        int b = nums[0];
        while (b != 0) {
            int tem = b;
            b = a % b;
            a = tem;
        }
        return a;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int tem = b;
            b = a % b;
            a = tem;
        }
        return a;
    }

    //2496
    public int maximumValue(String[] strs) {
        int len = 0;
        String pattern = "^[0-9]+$";
        for (String str : strs) {
            int tem = 0;
            if (Pattern.matches(pattern, str)) {
                tem = Integer.parseInt(str);
            } else {
                tem = str.length();
            }
            if (len < tem) {
                len = tem;
            }
        }
        return len;
    }

    //1464
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }

    public int maxProduct2(int[] nums) {
        int a = -1, b = -1;
        for (int x : nums) {
            if (x > a) {
                b = a; a = x;
            } else if (x > b) {
                b = x;
            }
        }
        return (a - 1) * (b - 1);
    }

    //1732
    public int largestAltitude(int[] gain) {
        int ret = 0;
        int high = 0;
        for (int i = 0; i < gain.length; i++) {
            ret += gain[i];
            if (ret > high) {
                high = ret;
            }
        }
        return high;
    }

    //1365
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            int num = nums[i];
            for (int j = 0; j < n; j++) {
                if (num > nums[j]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, new Comparator<int[]>() {
            public int compare(int[] data1, int[] data2) {
                return data1[0] - data2[0];
            }
        });
        int[] ret = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            ret[data[i][1]] = prev;
        }
        return ret;
    }

    //lcp06
    public int minCount(int[] coins) {
        int n = 0;
        for (int i : coins) {
            n += (i + 1) / 2;
        }
        return n;
    }

    //lcp01
    public int game(int[] guess, int[] answer) {
        int n = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                n++;
            }
        }
        return n;
    }

    //2574
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        leftSum[0] = 0;
        for (int i = 1; i < n; i++) {
            leftSum[i] = nums[i - 1] + leftSum[i - 1];
            System.out.println(leftSum[i]);
        }
        int[] rightSum = new int[n];
        rightSum[n-1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = nums[i + 1] + rightSum[i + 1];
            System.out.println(rightSum[i]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        return res;
    }

    //1720
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        for (int i = 0; i < n; i++) {
            res[i + 1] = res[i] ^ encoded[i];
        }
        return res;
    }

    //1929
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[2 * n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i];
            res[i + n] = nums[i];
        }
        return res;
    }

    //2011
    public int finalValueAfterOperations(String[] operations) {
        int n = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].contains("+")) {
                n++;
            } else {
                n--;
            }
        }
        return n;
    }
}
