package application;

import application.CommandHandlers.ContractHandler;
import application.CommandHandlers.CustomerHandler;
import application.Commands.CreateContractCommand;
import application.Commands.CreateCustomerCommand;
import application.EventHandlers.LoggingEventHandler;
import application.Sagas.ContractSaga;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Configuration configuration = DefaultConfigurer.defaultConfiguration()
				.configureAggregate(ContractHandler.class)
				.configureAggregate(CustomerHandler.class)
				.registerModule(SagaConfiguration.subscribingSagaManager(ContractSaga.class))
				.registerModule(new EventHandlingConfiguration()
						.registerEventHandler(c -> new LoggingEventHandler()))
				.configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
				.buildConfiguration();

		configuration.start();

		CommandGateway commandGateway = configuration.commandGateway();

		commandGateway.send(new CreateContractCommand("1" ,"1","1","wans"), LoggingCallback.INSTANCE);


		configuration.shutdown();

	}

}