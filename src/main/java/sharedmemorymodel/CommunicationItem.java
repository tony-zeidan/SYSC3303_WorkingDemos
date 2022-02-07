package sharedmemorymodel;

public class CommunicationItem {


    private String request;
    private boolean reply;

    public CommunicationItem(String request) {
        this.request = request;
        this.reply = false;
    }

    public String getRequest() {
        return request;
    }

    public void toggleReply() {
        this.reply = !this.reply;
    }

    public boolean isReply() {
        return this.reply;
    }

    @Override
    public String toString() {
        return String.format("*Communication Item*\nrequest: %s\nreply: %s", this.request, this.reply);
    }
}
