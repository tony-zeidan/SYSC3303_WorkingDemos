package sharedmemorymodel;

import java.sql.Timestamp;

/**
 * This class provides methods that all Threads use in common.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class ThreadHelper {

    /**
     * A method for the printing of Thread data.
     *
     * @param entity The name of the Thread printing
     * @param statement The statement context
     * @param item The item passed to or from the Thread
     */
    public static void createPrint(String entity, String statement, Object item) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String printer;
        if (item != null)
            printer = String.format("%s | %s %s\n%s\n", timestamp, entity, statement, item);
        else
            printer = String.format("%s | %s %s\n", timestamp, entity, statement);

        System.out.println(printer);
    }
}
