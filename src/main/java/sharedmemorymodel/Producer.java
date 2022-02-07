package sharedmemorymodel;

import java.util.LinkedList;
import java.util.List;

public class Producer implements Runnable {

    private BoundedBuffer buffer;
    private List<CommunicationItem> items;

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
