////////////////////////////////////////////////////////////////////
// Marco Ferrati 1168234
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.model.MenuItem;

public interface TakeAwayBill {

    double getOrderPrice(List<MenuItem> items_ordered) throws TakeAwayBillException;
}
