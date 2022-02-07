package sharedmemorymodel;

public class Consumer implements Runnable {

    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

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
