package application.EventHandlers;

import application.Entities.Contract;
import application.Events.ContractCreatedEvent;
import application.Events.ContractCreatedFailedEvent;
import application.Events.ContractDeletedEvent;
import application.Repositories.ContractRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ContractEventHandler {


    private ContractRepository repository;

    @Autowired
    public ContractEventHandler(ContractRepository repository) {
        this.repository = repository;
    }


    @EventHandler
    public void on(ContractCreatedEvent event) {

        repository.save(new Contract(event.getContractId(), event.getCustomerId(), event.number));

    }


    @EventHandler
    public void on(ContractDeletedEvent event) {

        repository.deleteById(event.contractId);


    }



}
