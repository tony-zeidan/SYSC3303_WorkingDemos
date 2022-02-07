package sharedmemorymodel;

import java.util.LinkedList;
import java.util.List;

public class BoundedBuffer {

    private boolean writeable;
    private boolean readable;

    private static final int MAX_SIZE = 5;

    private CommunicationItem[] buffer;
    private int inIndex = 0;
    private int outIndex = 0;
    private int size = 0;

    public BoundedBuffer() {
        buffer = new CommunicationItem[MAX_SIZE];
        writeable = true;
        readable = false;
    }

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
