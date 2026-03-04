package onlineshoppingplatform.strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy{

    private final String cardNumber;

    public CreditCardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of" + amount + "using card number" + cardNumber);
        return true;
    }
}
