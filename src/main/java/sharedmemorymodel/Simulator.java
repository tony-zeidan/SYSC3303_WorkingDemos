package sharedmemorymodel;

/**
 * This class provides an a way for the SharedMemoryModel to be run.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class Simulator {

    /**
     * Executable for the model.
     *
     * @param args Not used
     */
    public static void main(String[] args) {

        BoundedBuffer buffer = new BoundedBuffer();

        Thread producer1 = new Thread(new Producer(buffer), "Producer Thread 1");
        //Thread producer2 = new Thread(new Producer(buffer), "Producer Thread 2");

        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer Thread 1");
        //Thread consumer2 = new Thread(new Consumer(buffer), "Consumer Thread 2");
        //Thread consumer3 = new Thread(new Consumer(buffer), "Consumer Thread 3");

        producer1.start();
        //producer2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer1.start();
        //consumer2.start();
        //consumer3.start();
    }

}
