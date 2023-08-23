package com.demo.test.util;

import java.util.Random;

/**
 * @author :xiezhi
 * @date : 2023/8/10
 */
public class Test {
    public static void main(String[] args) {
//        byte[] bytes = "LA9CKJ3R8N5LM6534".getBytes();
//        System.out.println();
        Random R = new Random();
        int random = R.nextInt(2);
        System.out.println(random);
    }
}
