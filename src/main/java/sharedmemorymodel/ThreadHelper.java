package sharedmemorymodel;

import java.sql.Timestamp;

public class ThreadHelper {

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
