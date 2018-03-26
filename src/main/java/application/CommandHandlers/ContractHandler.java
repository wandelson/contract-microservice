package application.CommandHandlers;


import application.Commands.CreateContractCommand;
import application.Commands.DeleteContractCommand;
import application.Entities.Contract;
import application.Entities.Customer;
import application.Events.ContractCreatedEvent;
import application.Events.ContractDeletedEvent;
import application.Events.CustomerCreatedFailedEvent;
import application.Repositories.CustomerRepository;
import lombok.NoArgsConstructor;
import lombok.val;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;


@Aggregate
@NoArgsConstructor
public class ContractHandler {

    @AggregateIdentifier
    private String id;

  //  @Autowired
   // private  EventBus eventBus;

  //  @Autowired
   // private CustomerRepository customerRepository;


    @CommandHandler
    public  ContractHandler(CreateContractCommand command) {
      //  val customer = customerRepository.findById(command.getCustomerId());
        //if (customer != null) {
            apply(new ContractCreatedEvent(command.getContractId(), command.getCustomerId(), command.getNumber(), command.getCustomerName()));
     //   } else {
         //   eventBus.publish(asEventMessage(new CustomerCreatedFailedEvent(command.getCustomerId(), command.getCustomerName())));
      //  }
    }


    @CommandHandler
    public   void handle(DeleteContractCommand command) {

        apply(new ContractDeletedEvent(command.getContractId()));

    }


    @EventSourcingHandler
    public void on(ContractCreatedEvent event) {
        this.id = event.contractId;
    }


    @EventSourcingHandler
    public void on(ContractDeletedEvent event) {
        this.id = event.contractId;
    }

}
