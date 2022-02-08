package testsharedmemorymodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sharedmemorymodel.BoundedBuffer;
import sharedmemorymodel.CommunicationItem;


/**
 * This class provides test cases for the BoundedBuffer class which serves
 * as a link between Consumer and Producer classes.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class TestBoundedBuffer {

    /**
     * Testing BoundedBuffer object
     */
    private static BoundedBuffer testbuf;

    /**
     * Testing communication item.
     */
    private static CommunicationItem[] testci;

    /**
     * Initialize testing objects.
     */
    @BeforeAll
    public static void setup() {
        testbuf = new BoundedBuffer();
        testci = new CommunicationItem[5];
        for (int i = 0; i < testci.length; i ++) {
            testci[i] = new CommunicationItem("Test Request" + i);
        }
    }

    /**
     * Tests the pushing and retrieving ability of the BoundedBuffer.
     */
    @Test
    public void testGetPushCommunicationItem() {
        for (int i = 0; i < 5; i ++) {
            testbuf.pushCommunicationItem(testci[i]);
            CommunicationItem retrieved = testbuf.getCommunicationItem();
            assertEquals(testci[i], retrieved);
            assertEquals("Test Request" + i, testci[i].getRequest());
        }
    }

}
