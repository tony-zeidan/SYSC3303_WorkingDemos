package sharedmemorymodel;

import java.util.LinkedList;
import java.util.List;

/**
 * This class provides an implementation for the Producer Threads in the Shared Memory Model.
 * The Producer produces data that is passed to the BoundedBuffer to be read by Consumer Threads.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class Producer implements Runnable {

    /**
     * The buffer that this Producer Thread will use to communicate
     */
    private BoundedBuffer buffer;

    /**
     * A list of CommunicationItems to pass to the Consumer through the BoundedBuffer
     */
    private List<CommunicationItem> items;

    /**
     * Default constructor for instances of Producer.
     * Initializes a new producer, linked to the given buffer.
     *
     * @param buffer The buffer this Thread will use to communicate
     */
    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
        this.items = new LinkedList<>();

        items.add(new CommunicationItem("HELLO THERE"));
        items.add(new CommunicationItem("MY NAME"));
        items.add(new CommunicationItem("IS"));
        items.add(new CommunicationItem("TONY"));
        items.add(new CommunicationItem("AND"));
        items.add(new CommunicationItem("THIS IS PRODUCER CONSUMER"));
    }

    /**
     * The main Thread executable for Producer Threads.
     *
     * Pushes a single CommunicationItem at a time to the BoundedBuffer to be read by the Consumer,
     * and await a reply from the Consumer Threads.
     */
    @Override
    public void run() {

        for (CommunicationItem ci : items) {

            System.out.println("*-BEGIN----------------------------------------------------------------------------*");
            ThreadHelper.createPrint(Thread.currentThread().getName(), "Pushing a request to BoundedBuffer", ci);
            this.buffer.pushCommunicationItem(ci);


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CommunicationItem item = this.buffer.getCommunicationItem();
            ThreadHelper.createPrint(Thread.currentThread().getName(), "Received a request from BoundedBuffer", item);
            System.out.println("*-END------------------------------------------------------------------------------*");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
