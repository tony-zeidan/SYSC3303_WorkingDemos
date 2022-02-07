package sharedmemorymodel;

public class ThreadHelper {

    public static void createPrint(String entity, String statement, Object item) {
        String printer;
        if (item != null)
            printer = String.format("\n%s : %s : contains item :\n%s\n", entity, statement, item);
        else
            printer = String.format("\n%s : %s\n", entity, statement);

        System.out.println(printer);
    }
}
