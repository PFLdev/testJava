package stream;

import model.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test1 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("sdf");
        set.add("xvxcv");
        String str = "erewr";
        System.out.println(str + "," + set.toString());
    }
}
