package com.example.demo;


import application.CommandHandlers.CustomerHandler;
import application.Commands.CreateCustomerCommand;
import application.Events.CustomerCreatedEvent;
import application.Events.CustomerCreatedFailedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;

import org.junit.Test;

public class CustomerHandlerTest {

    private FixtureConfiguration<CustomerHandler> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<CustomerHandler>(CustomerHandler.class);
    }


    @Test
    public void testCreatedCustomer_Sucess() throws Exception {

        CreateCustomerCommand command = new CreateCustomerCommand("1", "1");

        fixture.givenNoPriorActivity().when(command)
                .expectEvents(new CustomerCreatedEvent("1", "1"));
    }

    @Test
    public void testCreatedCustomer_Failed() throws Exception {

        CreateCustomerCommand command = new CreateCustomerCommand("1", "1");

        fixture.givenNoPriorActivity().when(command)
                .expectEvents(new CustomerCreatedFailedEvent("1", "1"));
    }


}