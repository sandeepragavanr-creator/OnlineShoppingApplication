package onlineshoppingplatform.exceptions;

public class OutOfStockException  extends RuntimeException{
   // custom Exception
    public OutOfStockException(String message){
        super(message);
    }
}
