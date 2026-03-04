package onlineshoppingplatform;

import onlineshoppingplatform.models.*;
import onlineshoppingplatform.services.InventoryService;
import onlineshoppingplatform.services.OrderService;
import onlineshoppingplatform.services.PaymentService;
import onlineshoppingplatform.services.SearchService;
import onlineshoppingplatform.strategy.PaymentStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineShoppingSystem {

    private static volatile OnlineShoppingSystem instance;

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<String, Customer> customers = new ConcurrentHashMap<>();
    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private  final Map<String, Integer> stock = new ConcurrentHashMap<>();

    private final InventoryService inventoryService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final SearchService searchService;

    public OnlineShoppingSystem() {
        this.inventoryService = new InventoryService(stock);
        this.orderService = new OrderService(inventoryService);
        this.paymentService = new PaymentService();
        this.searchService = new SearchService(products.values());
    }



    public  static OnlineShoppingSystem getInstance(){
        if (instance == null){
            synchronized (OnlineShoppingSystem.class){
                if(instance == null){
                    instance = new OnlineShoppingSystem();
                }
            }
        }
        return instance;
    }

    public void addProduct(Product product, int initialStock){
        products.put(product.getId(), product);
        inventoryService.addStock(product,initialStock);
    }

    public Customer registerCustomer(String name, String email, String password, Address address){
        Customer customer = new Customer(name, email, password, address);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void addToCart(String customerId, String productId,int quantity){
        Customer customer = customers.get(customerId);
        Product product = products.get(productId);
        customer.getAccount().getCart().addItem(product,quantity);
    }

    public ShoppingCart getCustomerCart(String customerId){
        Customer customer = customers.get(customerId);
        ShoppingCart shoppingCart = customer.getAccount().getCart();
        return shoppingCart;
    }

    public List<Product> searchProducts(String name){
        return searchService.searchByName(name);
    }

    public Order placeOrder(String customerId, PaymentStrategy paymentStrategy){
        Customer customer = customers.get(customerId);
        ShoppingCart shoppingCart = customer.getAccount().getCart();
        if(shoppingCart.getItems().isEmpty()){
            System.out.println("Cannot place an order in an empty Cart.");
            return null;
        }
        boolean isPaymentSuccess = paymentService.processPayment(paymentStrategy, shoppingCart.calculateTotal());
        if (!isPaymentSuccess){
            System.out.println("Payment failed. Please try again");
            return null;
        }
        try{
            Order order = orderService.createOrder(customer,shoppingCart);
            orders.put(order.getId(), order);

            shoppingCart.clearCart();
            return order;
        } catch (Exception e) {
            System.out.println("Order placement failed" + e.getMessage());
            return null;
        }
    }
}
