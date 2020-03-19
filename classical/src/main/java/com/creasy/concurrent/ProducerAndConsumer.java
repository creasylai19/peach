package com.creasy.concurrent;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 经典的生产者消费者场景
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        Basket basket = new Basket(3);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                int count = 0;
                while (++count <= 2) {
                    Bread bread = basket.get();
                    try {
                        Thread.sleep(new Random().nextInt(1000));//休息一会
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println(Thread.currentThread().getName() + "我饱了，总共吃了" + (count-1) + "个面包了");
            }, "Consumer:" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while ( true ){//生产者源源不断供应
                    Bread bread = new Bread();
                    basket.put(bread);
                    try {
                        Thread.sleep(new Random().nextInt(1000));//休息一会
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Producer:" + i).start();
        }
    }

}

class Basket {

    private final Vector<Bread> list = new Vector<>();
    private int capacity;

    public Basket(int capacity) {
        this.capacity = capacity;
    }

    public Bread get() {
        synchronized (list) {
            while (list.size() <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + "当前没有面包了，消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Bread bread = list.remove(0);
            System.out.println(Thread.currentThread().getName() + "我吃了一个面包了:" + bread);
            list.notifyAll();
            return bread;
        }
    }

    public void put( Bread bread ){
        synchronized (list) {
            while (list.size() >= capacity) {
                try {
                    System.out.println(Thread.currentThread().getName() + "当前篮子满了，生产者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(bread);
            System.out.println(Thread.currentThread().getName() + "我生产了一个面包:" + bread);
            list.notifyAll();
        }
    }

}

class Bread {

    private static final AtomicInteger INDEX = new AtomicInteger(0);

    private int index;

    public Bread() {
        index = INDEX.addAndGet(1);
    }

    @Override
    public String toString() {
        return "Bread{" +
                "index=" + index +
                '}';
    }
}
