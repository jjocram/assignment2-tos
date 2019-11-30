////////////////////////////////////////////////////////////////////
// Marco Ferrati 1168234
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;

import java.util.List;

/**
 * Hello world!
 */
public class App implements TakeAwayBill {
    @Override
    public double getOrderPrice(List<MenuItem> items_ordered) throws TakeAwayBillException {
        double conto_totale = items_ordered.stream().mapToDouble(MenuItem::getPrice).reduce(0, Double::sum);

        double conto_solo_panini_e_fritti = items_ordered.stream()
                .filter(e -> e.getItemType() == ItemType.PANINO || e.getItemType() == ItemType.FRITTO)
                .mapToDouble(MenuItem::getPrice)
                .reduce(0, Double::sum);

        long numero_di_panini = items_ordered.stream().filter(e -> e.getItemType() == ItemType.PANINO).count();

        if (numero_di_panini > 5){
            double costo_panino_meno_costoso = items_ordered.stream().filter(e -> e.getItemType() == ItemType.PANINO).mapToDouble(MenuItem::getPrice).min().getAsDouble();
            conto_totale = conto_totale - (0.5 * costo_panino_meno_costoso);
        }

        if (conto_solo_panini_e_fritti > 50.0) {
            conto_totale = conto_totale - (0.1 * conto_totale);
        }

        return conto_totale;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
