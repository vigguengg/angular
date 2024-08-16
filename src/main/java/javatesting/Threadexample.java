package javatesting;

import io.micrometer.observation.ObservationHandler;

public class Threadexample implements Runnable{

    static int i =0;

    Object obj;

    public Threadexample(Object obj) {
        this.obj = obj;
    }

    public Threadexample() {

    }

    @Override
    public void run() {
        while(i <= 10) {
            if(i%2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (obj) {
                    System.out.println("Thread name" + Thread.currentThread().getName() + " value "+ i);
                    i++;
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if(i%2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (obj) {
                    System.out.println("Thread name" + Thread.currentThread().getName() + " value "+ i);
                    i++;
                    obj.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(new Threadexample(lock),"even");
        Thread t2 = new Thread(new Threadexample(lock),"odd");

        t1.start();
        t2.start();

    }
}
