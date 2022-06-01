package ge.nlatsabidze.newsapplication;

public class Sum {

    public boolean isInt = true;

    public int sum(int a, int b) {
        int c = Integer.MAX_VALUE - a;
        if (c < b) {
            throw new IllegalArgumentException("Sum is more than INT_MAX");
        } else {
            return a + b;
        }
    }

    public Number sumInts(int a, int b) {
        try {
            isInt = true;
            return sum(a, b);
        } catch (IllegalArgumentException e) {
            isInt = false;
            return sumLong(a, b);
        }
    }

    public long sumLong(int a, int b) {
        return (long) a + b;
    }

}
