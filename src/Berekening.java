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
        double bijnaLaagsteUptime = laagsteUptimeWeb;
        if(laagsteUptime>laagsteUptimeWeb){
            laagsteUptime = laagsteUptimeWeb;
            bijnaLaagsteUptime = laagsteUptimeDB;
        }

        laagsteUptime = laagsteUptime/100;
        bijnaLaagsteUptime = bijnaLaagsteUptime/100;

        double teller = 0;
        double extraTeller = 0;
        double serverLaagste = 1-laagsteUptime;
        double laagsteExtraStap = 10;
        double serverBijnaLaagste = 1-bijnaLaagsteUptime;
        double bijnaLaagsteExtraStap;
        double lasti = -1;
        boolean nextStep = false;
        for (double i = 0; i < uptime/100;){
            if(lasti != i && !nextStep) {
                lasti = i;
                teller++;
                double totaal = (Math.pow(serverLaagste, teller));
                laagsteExtraStap = 1 - totaal;
                i = laagsteExtraStap * bijnaLaagsteUptime;
                System.out.println(i);
                System.out.println("--------------------------"+teller);
            } else {
                nextStep = true;
                teller++;
                extraTeller++;
                double totaalBijnaLaagste = (Math.pow(serverBijnaLaagste,extraTeller));
                bijnaLaagsteExtraStap = 1- totaalBijnaLaagste;
                i = bijnaLaagsteExtraStap * laagsteExtraStap *0.99999 * 0.99999;
                System.out.println(i);
                System.out.println("------------------------------------------------------"+extraTeller);
            }
        }
        System.out.println("Voor de uptime van " + uptime + " zijn " + (teller + 1) + " componenten nodig");
    }
}
