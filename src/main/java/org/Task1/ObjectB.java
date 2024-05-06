package org.Task1;

public class ObjectB {
    public void stepFirst(ObjectA objectA) {
        System.out.println(Thread.currentThread().getName() + " -> stepOne");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Interrupted!");
        }
        System.out.println("Пытаемся вызвать второй метод!");
        objectA.stepTwo();
    }

    public void stepTwo() {
        System.out.println(Thread.currentThread().getName() + " -> secondStep");
    }
}
