import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class BloomFilter {

    public static final int FILTER_SIZE = 32;
    private BitSet bitset = new BitSet(32);

    static class HashFunction {
        int i;

        public HashFunction(int i) {
            this.i = i;
        }

        public double hash(int value) {
            double add = (Math.pow(value, 2) + Math.pow(value, 3));
            double val = add * i;
            return val;
        }

    }

    public void add(int value) {
        for (int i = 0; i <= 2; i++) {
            double hash = new HashFunction(i).hash(value);
            bitset.set((int) Math.abs(hash % FILTER_SIZE), true);
        }
    }

    public boolean contains(int value) {
        for (int i = 0; i <= 2; i++) {
            double hash = new HashFunction(i).hash(value);
            if (!bitset.get((int) Math.abs(hash % FILTER_SIZE))) {
                return false;
            }
        }
        return true;
    }

    public Map<Integer, Boolean> getAllBitsSet() {
        Map<Integer, Boolean> set = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 32; i++) {
            set.put(i, bitset.get(i));
        }
        return set;
    }
}
