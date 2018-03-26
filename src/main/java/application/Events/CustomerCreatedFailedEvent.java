package application.Events;


import lombok.Value;

@Value
public class CustomerCreatedFailedEvent {
    public String customerId;
    public String name;
}
