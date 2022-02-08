package sharedmemorymodel;

/**
 * This class provides an implementation for the Consumer Threads in the Shared Memory Model.
 * The Consumer acts as the Thread that reads data produced by the Producer.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class Consumer implements Runnable {

    /**
     * The buffer that this Consumer will use to communicate.
     */
    private BoundedBuffer buffer;

    /**
     * Default constructor for instances of Consumer.
     * Initializes a new consumer, linked to the given buffer.
     *
     * @param buffer The buffer this Thread will use to communicate
     */
    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * The main Thread executable for Consumer Threads.
     *
     * Reads a CommunicationItem put in the BoundedBuffer by Producer,
     * then sends the same CommunicationItem (reply mode on) to the BoundedBuffer to
     * be read by the Producer.
     */
    @Override
    public void run() {
        while (true) {
            CommunicationItem item = this.buffer.getCommunicationItem();
            ThreadHelper.createPrint(Thread.currentThread().getName(), "Received a request from BoundedBuffer", item);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            item.toggleReply();
            ThreadHelper.createPrint(Thread.currentThread().getName(), "Pushing a reply request to BoundedBuffer", item);
            this.buffer.pushCommunicationItem(item);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
