package com.creasy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Concurrency {

    public static void main(String[] args) {
//        testSimpleDateFormat();
        int a = 0x61c88647;
        int b = 15;
        int c = a & b;
        System.out.println(c);
    }

    private static void testSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + simpleDateFormat.parse("2020-03-03"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
