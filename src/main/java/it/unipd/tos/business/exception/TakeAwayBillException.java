////////////////////////////////////////////////////////////////////
// Marco Ferrati 1168234
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception{

    public TakeAwayBillException(String error_string){
        super(error_string);
    }
}
