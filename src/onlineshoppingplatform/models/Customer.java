package onlineshoppingplatform.models;

import onlineshoppingplatform.enums.OrderStatus;
import onlineshoppingplatform.observer.OrderObserver;

import java.util.UUID;

public class Customer implements OrderObserver {
    private final String id;
    private final String name;
    private final String email;
    private final Account account;
    private Address shippingAddress;

    public Customer(String name, String email, String password, Address shippingAddress){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.account = new Account(email,password);
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void update(Order order) {
        System.out.println("Notification for %s: Your order status has been updated to" +
                 order.getId() + order.getStatus());
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Account getAccount(){
        return account;
    }
    public Address getShippingAddress(){
        return shippingAddress;
    }
    public void setShippingAddress(Address address){
        this.shippingAddress = address;
    }
}
