package org.Task1;

public class ObjectA {
    public void stepFirst(ObjectB objectB){
        System.out.println(Thread.currentThread().getName() + " -> stepOne");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Interrupted!");
        }
        System.out.println("Пытаемся вызвать второй метод!");
        objectB.stepTwo();
    }
    public void stepTwo() {
        System.out.println(Thread.currentThread().getName() + " -> secondStep");
    }
}
