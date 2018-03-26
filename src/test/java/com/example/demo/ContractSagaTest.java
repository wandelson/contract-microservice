package com.example.demo;


import application.CommandHandlers.ContractHandler;
import application.Commands.CreateContractCommand;
import application.Commands.CreateCustomerCommand;
import application.Events.ContractCreatedEvent;
import application.Events.CustomerCreatedEvent;
import application.Sagas.ContractSaga;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;

public class ContractSagaTest {

    private org.axonframework.test.saga.FixtureConfiguration testFixture;

    @Before
    public void setUp() throws Exception {
        testFixture = new SagaTestFixture<>(ContractSaga.class);
    }

    @Test
    public void testCreateContract( )throws Exception {

        String contractId = "1";
        String customerId = "1";
        String customerName = "1";
        String number = "1";


        testFixture.givenNoPriorActivity()
                .whenAggregate(contractId).publishes(new ContractCreatedEvent(contractId,
                customerId,customerName,number))
                .expectActiveSagas(0)
                .expectDispatchedCommands(new CreateCustomerCommand(customerId, customerName));

    }








}