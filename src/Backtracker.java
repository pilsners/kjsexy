import java.util.Arrays;
import java.util.Locale;

public class Backtracker {
    private final static int N = 6;
    private final static int[] percentages = new int[]{12, 15, 30, 20, 46, 75};
    private final static double[] prices = new double[]{20, 28, 40, 55, 70, 100};
    private final static int MIN_PERCENTAGE = 205;
    private final static int MAX_COMPONENTS = 20;

    public static void main(String[] args) {
        Solution best = recur(new Solution(new int[N]), null, MAX_COMPONENTS);
        System.out.println("The cheapest solution is:");
        System.out.println(best);
    }

    private static Solution recur(Solution current, Solution best, int left) {
        if (left <= 0 || current.isValid()) {
            if (current.compareTo(best) < 0) return new Solution(current);
            else return best;
        }

        for (int i = 0; i < N; i++) {
            current.update(i, 1, false);
            left--;
            best = recur(current, best, left);
            current.update(i, 1, true);
            left++;
        }

        return best;
    }

    static class Solution implements Comparable<Solution> {
        private int[] counters;
        private double price;
        private int percentage;

        Solution(int[] counters) {
            price = 0;
            percentage = 0;
            this.counters = new int[counters.length];
            for (int i = 0; i < counters.length; i++) {
                this.update(i, counters[i], false);
            }
        }

        Solution(Solution sol) {
            this(sol.counters);
        }

        private void update(int item, int num, boolean remove) {
            if (remove) num *= -1;
            counters[item] += num;
            price += num * prices[item];
            percentage += num * percentages[item];
        }

        boolean isValid() {
            return this.percentage > MIN_PERCENTAGE;
        }

        @Override
        public int compareTo(Solution o) {
            return Double.compare(this.price, o == null ? Double.MAX_VALUE : o.price);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(Arrays.toString(this.counters));
            sb.append(String.format(Locale.US, " (price: %.2f, percentage: %d)", this.price, this.percentage));
            return sb.toString();
        }
    }
}