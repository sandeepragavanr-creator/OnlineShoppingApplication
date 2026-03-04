package onlineshoppingplatform.strategy;

public class UPIPaymentStrategy implements PaymentStrategy{

    private final String upiId;

    public UPIPaymentStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of" + amount + "using card number" + upiId);
        return true;
    }
}
