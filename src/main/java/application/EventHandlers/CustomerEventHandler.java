package application.EventHandlers;

import application.Entities.Customer;
import application.Events.CustomerCreatedEvent;
import application.Repositories.CustomerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerEventHandler {


    private CustomerRepository repository;

    @Autowired
    public CustomerEventHandler(CustomerRepository repository) {
        this.repository = repository;
    }


    @EventHandler
    public void on(CustomerCreatedEvent event) {
        repository.save(new Customer(event.getCustomerId(), event.getName()));
    }







}
