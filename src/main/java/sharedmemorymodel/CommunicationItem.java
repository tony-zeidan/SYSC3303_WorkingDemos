package sharedmemorymodel;

/**
 * This class provides an implementation for an object that Producer and Consumer Threads will use for data storage.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class CommunicationItem {

    /**
     * A string containing a word for a request (could be anything)
     */
    private String request;

    /**
     * Whether this item is acting as a reply or not
     */
    private boolean reply;

    /**
     * Default constructor for instances of CommunicationItem.
     * Initializes a new CommunicationItem with the given request word.
     *
     * @param request A string containing a request word
     */
    public CommunicationItem(String request) {
        this.request = request;
        this.reply = false;
    }

    /**
     * Getter for the request.
     *
     * @return The contained request
     */
    public String getRequest() {
        return request;
    }

    /**
     * Switch this to a reply item or a non-reply item.
     */
    public void toggleReply() {
        this.reply = !this.reply;
    }

    /**
     * Check if this CommunicationItem represents a reply item or not
     *
     * @return Whether this is a reply item or not
     */
    public boolean isReply() {
        return this.reply;
    }

    /**
     * String representation of the CommunicationItem.
     *
     * @return A string representation of the item
     */
    @Override
    public String toString() {
        return String.format("*Communication Item*\nrequest: %s\nreply: %s", this.request, this.reply);
    }
}
