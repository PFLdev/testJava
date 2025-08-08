package leetcode.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TestDuiLie {

    /**
     * 933、1700、232、225、2073、387
     *
     */

    public static void main(String[] args) {
        TestDuiLie test = new TestDuiLie();
        int[] students = new int[]{1,1,1,0,0,1};
        int[] sandwiches = new int[]{1,0,0,0,1,1};
        System.out.println(test.countStudents(students, sandwiches));
    }

    // 1700  输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
    //输出：3
    public int countStudents(int[] students, int[] sandwiches) {
        int s1 = Arrays.stream(students).sum();
        int s0 = students.length - s1;
        for (int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == 0 && s0 > 0) {
                s0--;
            } else if (sandwiches[i] == 1 && s1 > 0) {
                s1--;
            } else {
                break;
            }
        }
        return s0 + s1;
    }

}
