package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    App app = new App();;
    List<MenuItem> items;
    double result;
    private final MenuItem panino_primavera = new MenuItem(ItemType.PANINO, "Panino primavere", 5.5);
    private final MenuItem panino_vegetariano = new MenuItem(ItemType.PANINO, "Panino vegetariano", 6.0);
    private final MenuItem olive_ascolane = new MenuItem(ItemType.FRITTO, "Olive ascolane", 3.5);
    private final MenuItem arancino = new MenuItem(ItemType.FRITTO, "Arancino", 3.0);
    private final MenuItem coca_cola = new MenuItem(ItemType.BEVANDA, "Coca Cola", 2.0);
    private final MenuItem acqua_naturale = new MenuItem(ItemType.BEVANDA, "Acqua naturale 0,5l", 1.0);

    @Test
    public void testCalcoloDelTotale() {
        items = new ArrayList<>();
        result = 0.0;

        items.add(panino_primavera);
        items.add(panino_vegetariano);
        items.add(olive_ascolane);

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(15.0, result, 0.0D);
    }

    @Test
    public void testCalcoloDelTotaleDiUnaListaVuota() {
        items = new ArrayList<>();
        result = 0.0;

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(0.0D, result, 0.0D);
    }

    @Test(expected = NullPointerException.class)
    public void testCalcoloDelTotateDiUnaListaNonInizializzata() throws TakeAwayBillException{
        items = null;
        result = app.getOrderPrice(items);
    }

    @Test
    public void testApplicazioneScontoSulPaninoMenoCaroSeNeVengonoCompartiPiuDiCinque(){
        items = new ArrayList<>();
        result = 0.0;

        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_primavera);
        items.add(panino_primavera);

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(32.25, result, 0.0D);
    }

    @Test
    public void testMenuSenzaPanini(){
        items = new ArrayList<>();
        result = 0.0;

        items.add(arancino);
        items.add(olive_ascolane);
        items.add(coca_cola);
        items.add(acqua_naturale);
        items.add(acqua_naturale);
        items.add(acqua_naturale);

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(11.5, result, 0.0D);
    }

    @Test
    public void testContoTotaleConSpesaPerPaniniEFrittiCheSuperaICinquantaEuro(){
        items = new ArrayList<>();
        result = 0.0;

        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(arancino);
        items.add(arancino);
        items.add(arancino);
        items.add(arancino);
        items.add(arancino);
        items.add(olive_ascolane);
        items.add(olive_ascolane);
        items.add(olive_ascolane);
        items.add(olive_ascolane);
        items.add(olive_ascolane);

        try {
            result = app.getOrderPrice(items);
        } catch (TakeAwayBillException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(56.25, result, 0.0D);
    }
}
