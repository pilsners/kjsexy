
public class Main {

    public static void main(String[] args) {
        Backtracker b = new Backtracker(20, 60, 1, 1);
        if (b.getBest() != null) {
            System.out.println("The cheapest solution is: " + b.getBest());
        } else {
            System.out.println("No solution was found!");
        }
        Berekening a = new Berekening(99.999);
        a.maxComponent();
    }
}
