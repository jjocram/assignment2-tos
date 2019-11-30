package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.ItemType;
import it.unipd.tos.business.model.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    App app = new App();
    ;
    List<MenuItem> items;
    double bill_result;

    private final MenuItem panino_primavera = new MenuItem(ItemType.PANINO, "Panino primavere", 5.5);
    private final MenuItem panino_vegetariano = new MenuItem(ItemType.PANINO, "Panino vegetariano", 6.0);
    private final MenuItem olive_ascolane = new MenuItem(ItemType.FRITTO, "Olive ascolane", 3.5);
    private final MenuItem arancino = new MenuItem(ItemType.FRITTO, "Arancino", 3.0);
    private final MenuItem coca_cola = new MenuItem(ItemType.BEVANDA, "Coca Cola", 2.0);
    private final MenuItem acqua_naturale = new MenuItem(ItemType.BEVANDA, "Acqua naturale 0,5l", 1.0);

    @Before
    public void cleanEnvironment() {
        items = new ArrayList<>();
        bill_result = 0.0;
    }

    @Test
    public void testCalcoloDelTotale() throws TakeAwayBillException {
        items.add(panino_primavera);
        items.add(panino_vegetariano);
        items.add(olive_ascolane);

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(15.0, bill_result, 0.0D);
    }

    @Test
    public void testCalcoloDelTotaleDiUnaListaVuota() throws TakeAwayBillException {
        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(0.0D, bill_result, 0.0D);
    }

    @Test(expected = NullPointerException.class)
    public void testCalcoloDelTotateDiUnaListaNonInizializzata() throws TakeAwayBillException {
        items = null;
        bill_result = app.getOrderPrice(items);
    }

    @Test
    public void testApplicazioneScontoSulPaninoMenoCaroSeNeVengonoCompartiPiuDiCinque() throws TakeAwayBillException {
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_primavera);
        items.add(panino_primavera);

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(32.25, bill_result, 0.0D);
    }

    @Test
    public void testMenuSenzaPanini() throws TakeAwayBillException {
        items.add(arancino);
        items.add(olive_ascolane);
        items.add(coca_cola);
        items.add(acqua_naturale);
        items.add(acqua_naturale);
        items.add(acqua_naturale);

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(11.5, bill_result, 0.0D);
    }

    @Test
    public void testContoTotaleConSpesaPerPaniniEFrittiCheSuperaICinquantaEuro() throws TakeAwayBillException {
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

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(56.25, bill_result, 0.0D);
    }

    @Test
    public void testContoTotaleApplicandoSiaLoScontoPeICinquePaniniSiaPerICinquantaEuroDiSpesa() throws TakeAwayBillException {
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_vegetariano);
        items.add(panino_primavera);
        items.add(panino_primavera);
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

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(63.675, bill_result, 0.0D);
    }

    @Test(expected = TakeAwayBillException.class)
    public void testConPiuDiTrentaElementi() throws TakeAwayBillException {
        for (int i = 0; i < 32; ++i) {
            items.add(panino_primavera);
        }

        app.getOrderPrice(items);
    }

    @Test
    public void testContoInferioreADieciEuro() throws TakeAwayBillException {
        items.add(panino_primavera);

        bill_result = app.getOrderPrice(items);

        Assert.assertEquals(6.0, bill_result, 0.0D);
    }

    @Test
    public void testMainFunction() {
        App.main(null);
    }
}
