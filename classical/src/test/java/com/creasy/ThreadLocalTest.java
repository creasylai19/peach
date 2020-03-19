package com.creasy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadLocalTest {

    private static final Logger logger = LogManager.getLogger("ThreadLocalTest");
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void testMultiThreadAccess(){

        threadLocal.set("Main thread");

        Thread t1 = new Thread(()->{
            threadLocal.set("t1 thread");
            try {
                Thread.sleep(1000);//do something
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug(threadLocal.get());
        }, "t1");

        Thread t2 = new Thread(()->{
            threadLocal.set("t2 thread");
            try {
                Thread.sleep(1000);//do something
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug(threadLocal.get());
        }, "t2");


        t1.start();
        t2.start();

        logger.debug(threadLocal.get());

    }

    public static void main(String[] args) {
        testMultiThreadAccess();
    }

}
