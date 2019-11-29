package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    App app;
    List<MenuItem> items;
    private final MenuItem panino_primavera = new MenuItem(ItemType.PANINO, "Panino primavere", 5.5);
    private final MenuItem panino_vegetariano = new MenuItem(ItemType.PANINO, "Panino vegetariano", 6.0);
    private final MenuItem olive_ascolane = new MenuItem(ItemType.FRITTO, "Olive ascolane", 3.5);
    private final MenuItem arancino = new MenuItem(ItemType.FRITTO, "Arancino", 3.0);
    private final MenuItem coca_cola = new MenuItem(ItemType.BEVANDA, "Coca Cola", 2.0);
    private final MenuItem acqua_naturale = new MenuItem(ItemType.BEVANDA, "Acqua naturale 0,5l", 1.0);

    @Test
    public void testCalcoloDelTotale() {
        app = new App();
        items = new ArrayList<>();
        double result = 0.0;

        items.add(panino_primavera);
        items.add(panino_vegetariano);
        items.add(olive_ascolane);
        items.add(arancino);
        items.add(coca_cola);
        items.add(acqua_naturale);

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }
        assertEquals(21.0, result);
    }
}
