package org.Task1;

/*
Создать два класс ObjectA, ObjectB
Реализовать класс в котором два потока вызовут DeadLock
 */
public class DeadLock implements Runnable {
    ObjectA first = new ObjectA();
    ObjectB second = new ObjectB();
    Thread thread;

    public DeadLock() {
        Thread.currentThread().setName("Main thread");
        this.thread = new Thread("Block thread");
    }

    @Override
    public void run() {
        second.stepFirst(first);
        System.out.println(Thread.currentThread().getName() + " is finished!");
    }

    public void startDeadLock() {
        thread.start();
        first.stepFirst(second);
        System.out.println(Thread.currentThread().getName() + " is finished!");
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.startDeadLock();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (deadLock.first) {
                    System.out.println("Running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (deadLock.second) {
                        System.out.println("Some work");
                    }
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run(){
                synchronized (deadLock.second) {
                    System.out.println("Running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (deadLock.first) {
                        System.out.println("Some work");
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}

