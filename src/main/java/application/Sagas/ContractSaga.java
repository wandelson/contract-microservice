package application.Sagas;

import application.Commands.CreateContractCommand;
import application.Commands.CreateCustomerCommand;
import application.Commands.DeleteContractCommand;
import application.Events.ContractCreatedEvent;
import application.Events.ContractCreatedFailedEvent;
import application.Events.ContractDeletedEvent;
import application.Events.CustomerCreatedFailedEvent;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EmptyStackException;
import java.util.UUID;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;
import static org.axonframework.eventhandling.saga.SagaLifecycle.end;


@Saga
public class ContractSaga {

    private transient CommandBus commandBus;

    @Autowired
    public void setCommandBus(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    private String contractId;
    private String customerId;


    @StartSaga(forceNew = true)
    @SagaEventHandler(associationProperty = "contractId")
    public void on(ContractCreatedEvent event) {
        contractId = event.getContractId();
        customerId = event.getCustomerId();
        try {
            //TODO:compensação
            throw new EmptyStackException();
            //CreateCustomerCommand command = new CreateCustomerCommand(event.getCustomerId(), event.getCustomerName());
            // commandBus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);

        } catch (Exception ex) {
            DeleteContractCommand command = new DeleteContractCommand(event.getContractId());
            commandBus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);
        }

    }



        @SagaEventHandler(associationProperty = "contractId")
        public void on(ContractDeletedEvent event) {
            // or import static org.axonframework.eventhandling.saga.SagaLifecycle.end;
            end();
        }


    }





