package application.CommandHandlers;


import application.Commands.CreateContractCommand;
import application.Commands.CreateCustomerCommand;
import application.Entities.Customer;
import application.Events.ContractCreatedEvent;
import application.Events.CustomerCreatedEvent;
import application.Repositories.CustomerRepository;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class CustomerHandler {


    @AggregateIdentifier
    private String customerId;

    private Repository<Customer> repository;


    @CommandHandler
    public  CustomerHandler(CreateCustomerCommand command) {
        apply(new CustomerCreatedEvent(command.getCustomerId(), command.getName()));
    }


    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.customerId = event.getCustomerId();
    }

}
