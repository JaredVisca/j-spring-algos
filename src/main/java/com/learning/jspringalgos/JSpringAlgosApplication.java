package com.learning.jspringalgos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class JSpringAlgosApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSpringAlgosApplication.class, args);
        int[] foo = {0, 2, 1, 3, 2};
        int result = findDuplicate(foo);
        System.out.println(result);

//        countToNumber(3);
//        countToNumber(4);

    }

    public static int findDuplicate(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        for(int i : array) {
            if(!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                return i;
            }
        }
        return 0;
    }

    public static void countToNumber(int n) {
        int numberToPrintTo = Math.powExact(10, n);
        for(int i = 0; i < numberToPrintTo; i++) {
            System.out.println(i);
        }
    }
}
