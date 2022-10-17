package today.creame.web.common.support;


public class ObjectKeySequence {
    private static ObjectKeySequence instance;
    private ObjectKeySequence() {}

    static {
        instance = new ObjectKeySequence();
    }

    public static ObjectKeySequence getInstance() {
        return instance;
    }


    private int nextValue = 10000;

    public synchronized int next() {
        if (++nextValue < 99999) {
            return nextValue;
        }

        nextValue = 10000;
        return nextValue;
    }
}