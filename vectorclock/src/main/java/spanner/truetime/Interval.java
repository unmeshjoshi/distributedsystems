package spanner.truetime;

public class Interval {
    long earliest;
    long latest;

    public Interval(int earliest, int latest) {
        this.earliest = earliest;
        this.latest = latest;
    }
}
