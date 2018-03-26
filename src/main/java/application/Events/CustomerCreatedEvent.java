package application.Events;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;


@Value
public class CustomerCreatedEvent {

    public String customerId;

    public String name;


}
