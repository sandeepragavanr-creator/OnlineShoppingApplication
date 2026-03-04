package onlineshoppingplatform.state;

import onlineshoppingplatform.models.Order;

public class DeliveredState implements OrderState{
    @Override
    public void ship(Order order) {
        System.out.println("Order already delivered.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order already delivered");
    }

    @Override
    public void cancel(Order order) {
         System.out.println("Order already delivered. Hence cannot cancel");
    }
}
