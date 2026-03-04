package onlineshoppingplatform;

import onlineshoppingplatform.decorator.GiftWrapDecorator;
import onlineshoppingplatform.enums.ProductCategory;
import onlineshoppingplatform.models.*;
import onlineshoppingplatform.strategy.CreditCardPaymentStrategy;
import onlineshoppingplatform.strategy.UPIPaymentStrategy;

public class OnlineShoppingDemo {
    public static void main(String[] args){
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        Product laptop = new Product.Builder("Lenovo Yoga Slim 7", 1500.00)
                .withDescription("High performance laptop")
                .withProductCategory(ProductCategory.ELECTRONICS)
                .build();

        Product book = new Product.Builder("Computer basics", 45.00)
                .withDescription("Easy for Beginner")
                .withProductCategory(ProductCategory.BOOKS)
                .build();

        system.addProduct(laptop, 100);
        system.addProduct(book, 50);

        Address sandeepAddress = new Address("ABC Street", "TN", "Chennai", "456");
        Customer Sandeep = system.registerCustomer("Sandeep", "sandy@gmail.com", "password", sandeepAddress);

        System.out.println("Sandeep is going to start shopping");
        system.addToCart(Sandeep.getId(), laptop.getId(), 2);

        System.out.println("Sandeep added a laptop to his cart");

        Product giftWrappedBook = new GiftWrapDecorator(book);
        system.addToCart(Sandeep.getId(), giftWrappedBook.getId(), 1);

        System.out.println("Sandeep has added a gift wrapped book to his cart");

        ShoppingCart sandeepCart = system.getCustomerCart(Sandeep.getId());
        System.out.println("The total bill for Sandeep is " + sandeepCart.calculateTotal());

        System.out.println("Sandeep needs to check-out");
        Order sandeepOrder = system.placeOrder(Sandeep.getId(), new CreditCardPaymentStrategy("1234"));
        if(sandeepOrder == null){
            System.out.println("Order placement failed");
            return;
        }
        System.out.println("Order has been placed for Sandeep successfully" + sandeepOrder.getId());

        System.out.println("Order processing starts");

        sandeepOrder.shipOrder();

        sandeepOrder.deliverOrder();

        sandeepOrder.cancelOrder();

        System.out.println("Out os stock scenario");

        Address bobAddress = new Address("XYZ Street","TN", "Tiruchy", "367");
        Customer bob = system.registerCustomer("Bob", "bob@gmail.com", "pass", bobAddress);
        system.addToCart(bob.getId(), laptop.getId(), 200);
        Order bobOrder = system.placeOrder(bob.getId(), new UPIPaymentStrategy("ihsd"));

        if(bobOrder == null){
            System.out.println("Bob could not place order due to lack of product");
            return;
        }
    }
}
