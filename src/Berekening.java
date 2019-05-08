import java.util.ArrayList;
import java.util.Collections;


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

    public void maxComponent() {
        double laagsteUptimeWeb = 100;
        for (Component component : componenten){
            if (component.getUptime()< laagsteUptimeWeb && component.getType().equals("Webserver")) {
                laagsteUptimeWeb = component.getUptime();
            }
        }
        System.out.println("De webserver met de laagste uptime heeft een uptime van "+laagsteUptimeWeb);

        double laagsteUptimeDB = 100;
        for (Component component : componenten){
            if (component.getUptime()< laagsteUptimeDB && component.getType().equals("DBserver")) {
                laagsteUptimeDB = component.getUptime();
            }
        }
        System.out.println("De DBserver met de laagste uptime heeft een uptime van "+laagsteUptimeDB);

        double laagsteUptime = laagsteUptimeDB;
        String typeLaagste = "DB server";
        double bijnaLaagsteUptime = laagsteUptimeWeb;
        String typeBijnaLaagste = "Web server";
        if(laagsteUptime>laagsteUptimeWeb){
            laagsteUptime = laagsteUptimeWeb;
            typeLaagste = "Web server";
            bijnaLaagsteUptime = laagsteUptimeDB;
            typeBijnaLaagste = "DB server";
        }

        laagsteUptime = laagsteUptime/100;
        bijnaLaagsteUptime = bijnaLaagsteUptime/100;
        double rekenUptime = uptime/100;

        double teller = 0;
        double extraTeller = 0;
        double serverLaagste = 1-laagsteUptime;
        double serverBijnaLaagste = 1-bijnaLaagsteUptime;
        double uptimeServers = Math.sqrt(rekenUptime/0.99999*0.99999);
        double uptimeLaagste = 0;
        double uptimeBijnaLaagste = 0;

        for (double i = 0; i < uptimeServers;){
                teller++;
                double totaal = (Math.pow(serverLaagste, teller));
                i = 1-totaal;
                uptimeLaagste = i;
                System.out.println(i);
                System.out.println("--------------------------"+teller);
            }
        System.out.println("----------------------------------------------------------Dit is van " + typeLaagste);
        for (double i = 0; i < uptimeServers;){
                teller++;
                extraTeller++;
                double totaal = (Math.pow(serverBijnaLaagste, extraTeller));
                i = 1-totaal;
                uptimeBijnaLaagste = i;
                System.out.println(i);
                System.out.println("--------------------------"+teller);
        }
        System.out.println("----------------------------------------------------------Dit is van " + typeBijnaLaagste);

        System.out.println("Berekening: "+uptimeLaagste+" * "+uptimeBijnaLaagste+" * 0.99999 * 0.99999 = "+uptimeLaagste*uptimeBijnaLaagste*0.99999*0.99999);
        System.out.println("\nVoor de uptime van " + uptime + " zijn " + teller + " componenten nodig");
        }
    }

