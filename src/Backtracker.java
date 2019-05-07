import java.util.Arrays;
import java.util.Locale;

public class Backtracker {
    //TODO: Uitreken formule gebruiken
    //TODO: Minimum aantal web en db servers als input
    //TODO: Altijd 1 firewall en balancer toevoegen

    private final int N = 6;
    private final double[] percentages = new double[]{12, 15, 30, 20, 46, 75};
    private final double[] prices = new double[]{20, 28, 40, 55, 70, 100};
    private final double MIN_PERCENTAGE;
    private final int MAX_COMPONENTS;
    private final int MIN_WEB;
    private final int MIN_DB;
    private Solution best;

    public Backtracker(int MAX_COMPONENTS, double MIN_PERCENTAGE, int MIN_WEB, int MIN_DB) {
        this.MAX_COMPONENTS = MAX_COMPONENTS;
        this.MIN_PERCENTAGE = MIN_PERCENTAGE;
        this.MIN_WEB = MIN_WEB;
        this.MIN_DB = MIN_DB;
        Solution best = recur(new Solution(new int[N]), null, MAX_COMPONENTS);
        this.best = best;
    }

    public Solution getBest() {
        return best;
    }

    private Solution recur(Solution current, Solution best, int left) {
        if (left <= 0 || current.isValid()) {
            if (current.compareTo(best) < 0 && current.isValid()) return new Backtracker.Solution(current);
            else return best;
        }

        for (int i = 0; i < N; i++) {
            current.update(i, 1);
            left--;
            best = recur(current, best, left - 1);
            current.update(i, -1);
            left++;
        }
        return best;
    }

    class Solution implements Comparable<Solution> {
        private int[] counters;
        private double price;
        private double percentage;

        Solution(int[] counters) {
            price = 0;
            percentage = 0;
            this.counters = new int[counters.length];
            for (int i = 0; i < counters.length; i++) {
                this.update(i, counters[i]);
            }
        }

        Solution(Solution sol) {
            this(sol.counters);
        }

        private void update(int item, int num) {
            counters[item] += num;
            price += num * prices[item];
            percentage += num * percentages[item];
        }

        boolean isValid() {
            return this.percentage > MIN_PERCENTAGE;
        }

        public int compareTo(Solution o) {
            return Double.compare(this.price, o == null ? Double.MAX_VALUE : o.price);
        }

        @Override
        public String toString() {
            return Arrays.toString(this.counters) + String.format(Locale.US, " (price: %.2f, percentage: %.2f)", this.price, this.percentage);
        }
    }
}