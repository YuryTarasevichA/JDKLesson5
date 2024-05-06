package org.Task2;

/*
Создайте два потока A и B.
Поток A меняет значение Boolean переменной switcher
с задержкой 1000 миллисекунд (true в состояние false и наоборот).
Поток B ожидает состояния true переменной switcher
и выводит на консоль обратный отсчет от 100 с задержкой 100 миллисекунд
и приостанавливает свое действие, как только поток A переключит switcher в состояние false.
Условием завершения работы потоков является достижение отсчета нулевой отметки.
 */
public class Switcher {
    static Boolean switcher;
    static boolean flag;

    public static void main(String[] args) {
        switcher = false;
        flag = true;
        Thread threadA = new Thread() {
            @Override
            public void run() {
                while (flag) {
                    switcher = !switcher;
//                    System.out.println("Switcher: " + switcher);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                int count = 100;
                while (count != 0) {
                    if (switcher){
                        count--;
                        System.out.println("count -> " + count);
                    }else {
                        switcher = !switcher;
                    }
//                    System.out.println("Switcher -> " + switcher);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                flag = false;
            }
        };
        threadA.setDaemon(true);
        threadA.start();
        threadB.start();

    }
}
