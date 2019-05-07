import java.util.ArrayList;

public class Berekening {

    public double uptime;
    public ArrayList<Component> componenten;

    public Berekening(double uptime) {
        this.uptime = uptime;
        componenten = new ArrayList<>();
        componenten.add(new Component(2000, 99.999, "pfSense", "Firewall"));
        componenten.add(new Component(2000, 99.999, "DBloadbalancer", "Loadbalancer"));

        //DBServers
        componenten.add(new Component(5100, 90, "HAL9001DB", "DBserver"));
        componenten.add(new Component(7700, 95, "HAL9002DB", "DBserver"));
        componenten.add(new Component(12200, 98, "HAL9003DB", "DBserver"));

        //Webservers
        componenten.add(new Component(2200, 80, "HAL9001W", "Webserver"));
        componenten.add(new Component(3200, 90, "HAL9002W", "Webserver"));
        componenten.add(new Component(5100, 95, "HAL9003W", "Webserver"));
    }

    public void algoritme() {
        //maximaal 20 componenten
        int aantalComponenten = 20;
        Component best;
        for(Component c : componenten) {
            for(int i = 0; i < aantalComponenten; i++) {
                //per component 20x
            }
        }
    }
}
