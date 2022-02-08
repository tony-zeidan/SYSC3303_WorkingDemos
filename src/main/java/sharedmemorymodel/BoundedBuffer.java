package sharedmemorymodel;

/**
 * This class provides an implementation for the BoundedBuffer in the Shared Memory Model.
 * The BoundedBuffer acts as a communication link/shared memory for the Consumer and Producer Threads.
 *
 * The BoundedBuffer can be implemented in many ways.
 * In this implementation, the BoundedBuffer uses a circular array of CommunicationItems.
 * These CommunicationItems are objects that Producer and Consumer Threads write data to and read data from.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class BoundedBuffer {

    /**
     * Whether the buffer can be written to or not and read from or not
     */
    private boolean writeable, readable;

    /**
     * The max size of the circular buffer array
     */
    private static final int MAX_SIZE = 5;

    /**
     * The circular buffer array
     */
    private CommunicationItem[] buffer;

    /**
     * The index to write to, read from, and the number of elements in the circular array
     */
    private int inIndex, outIndex, size = 0;

    /**
     * Default constructor for instances of BoundedBuffer.
     * Initializes a new BoundedBuffer that can be written to and not read from.
     */
    public BoundedBuffer() {
        buffer = new CommunicationItem[MAX_SIZE];
        writeable = true;
        readable = false;
    }

    /**
     * Provides Producer Threads with a way of pushing items into the buffer.
     *
     * @param ci The item to push to the buffer
     */
    public synchronized void pushCommunicationItem(CommunicationItem ci) {

        while (!writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ThreadHelper.createPrint("BoundedBuffer", "had a request pushed to it", ci);

        this.buffer[inIndex] = ci;
        readable = true;
        inIndex = (inIndex+1) % MAX_SIZE;
        size++;

        if (this.size == MAX_SIZE)
            writeable = false;

        notifyAll();
    }

    /**
     * Provides Consumer Threads with a way of reading items from the buffer.
     *
     * @return The read item
     */
    public synchronized CommunicationItem getCommunicationItem() {

        while (!readable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        CommunicationItem item = buffer[outIndex];

        ThreadHelper.createPrint("BoundedBuffer", "had a request read from it", item);

        writeable = true;

        outIndex = (outIndex + 1) % MAX_SIZE;
        size--;
        if (size == 0)
            readable = false;

        notifyAll();
        return item;
    }
}
