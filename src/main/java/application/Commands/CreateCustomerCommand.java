package application.Commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.Min;


@Value
public class CreateCustomerCommand {

    @TargetAggregateIdentifier
    private String customerId;

    @Min(value = 0, message = "Name must not be less than zero")
    private String name;

}
