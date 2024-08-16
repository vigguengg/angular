package ProducerConsumer;


class Buffer {

    private final int[] buffer;
    private final int size;
    private int count;

    Buffer(int size) {
        this.buffer = new int[size];
        this.size = size;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (count == size) {
            wait();
        }
        buffer[count] = item;
        count++;
        System.out.println("produed" + item);
        notify();

    }

    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int item = buffer[count - 1];
        count--;
        System.out.println("consumed" + item);
        notify();
        return item;
    }
}


class Producer implements Runnable {

    private final Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {

    private final Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int item = buffer.consume();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(5);
        Thread t1 = new Thread(new Producer(buffer));
        Thread t2 = new Thread(new Consumer(buffer));
                t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}