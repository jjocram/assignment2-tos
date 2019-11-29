////////////////////////////////////////////////////////////////////
// Marco Ferrati 1168234
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.MenuItem;

import java.util.List;

/**
 * Hello world!
 */
public class App implements TakeAwayBill {
    @Override
    public double getOrderPrice(List<MenuItem> items_ordered) throws TakeAwayBillException {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
