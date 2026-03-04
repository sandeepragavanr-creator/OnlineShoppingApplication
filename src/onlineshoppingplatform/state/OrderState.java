package onlineshoppingplatform.state;

import onlineshoppingplatform.models.Order;

public interface OrderState {

    public void ship(Order order);

    public void deliver(Order order);

    public void cancel(Order order);
}
