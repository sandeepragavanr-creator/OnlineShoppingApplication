package onlineshoppingplatform.state;

import onlineshoppingplatform.enums.OrderStatus;
import onlineshoppingplatform.models.Order;

public class PlacedState implements OrderState{
    @Override
    public void ship(Order order) {
        System.out.println("Shipping order" + order.getId());
        order.setStatus(OrderStatus.SHIPPED);
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {
        System.out.println("cannot deliver an order that has not been placed");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cancelling Order " + order.getId());
        order.setState(new CancelledState());
        order.setStatus(OrderStatus.CANCELLED);
    }
}
