package sharedmemorymodel;

import java.sql.Timestamp;

public class ThreadHelper {


    public static void createPrint(String entity, String statement, Object item) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //System.out.println("*--------------------------EVENT--------------------------*");


        String printer;
        if (item != null)
            printer = String.format("%s | %s %s\n%s\n", timestamp, entity, statement, item);
        else
            printer = String.format("%s | %s | %s | %s\n", timestamp, entity, statement, false);

        System.out.println(printer);
    }
}
