package spanner.truetime;

public class TrueTime {

    public Interval now() {
        return new Interval(10, 11);
    }
}
