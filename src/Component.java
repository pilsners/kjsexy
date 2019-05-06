public class Component {

    private int prijs;
    private double uptime;
    private String naam;
    private String type;

    public Component(int prijs, double uptime, String naam, String type) {
        this.prijs = prijs;
        this.uptime = uptime;
        this.naam = naam;
        this.type = type;
    }

    public int getPrijs() {
        return prijs;
    }

    public String getNaam() {
        return naam;
    }

    public String getType() {
        return type;
    }

    public double getUptime() {
        return uptime;
    }
}
