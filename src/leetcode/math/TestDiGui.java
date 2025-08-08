package leetcode.math;

import javax.swing.plaf.SeparatorUI;
import java.util.*;

public class TestDiGui {

    // 未做：71、388、150、227、224、20、636、591、32   B01511-67449/W5UB01520
    // 已做：682

    public static void main(String[] args) {
        TestDiGui test = new TestDiGui();
        String[] arr = new String[]{"5","-2","4","C","D","9","+","+"};
        System.out.println(test.isValid("()"));
    }

    // 20  s = "()[]{}"
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("]", "[");
        map.put("}", "{");
        char[] charArray = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (char c : charArray) {
            if (map.containsKey(String.valueOf(c))) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(String.valueOf(c)))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(String.valueOf(c));
            }
        }
        return stack.isEmpty();
    }

    // 682
    public int calPoints(String[] operations) {
        List<Integer> list = new ArrayList<>();
        for (String operation : operations) {
            if (operation.equals("+")) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
            } else if (operation.equals("D")) {
                list.add(2 * list.get(list.size() - 1));
            } else if (operation.equals("C")) {
                list.remove(list.size() - 1);
            } else {
                list.add(Integer.valueOf(operation));
            }
        }
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }
}
