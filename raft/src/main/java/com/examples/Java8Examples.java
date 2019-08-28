package com.examples;

import java.util.function.Function;

public class Java8Examples {
    static Function<Integer, Integer> func = (Integer i) -> 2;

    public static void main(String args[]) {
        System.out.println("args = " + func.apply(1));
    }
}
