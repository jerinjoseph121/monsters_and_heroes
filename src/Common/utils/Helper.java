package Common.utils;

public class Helper {
    private static int nextId = 1;

    public static synchronized int generateId() {
        return nextId++;
    }
}
