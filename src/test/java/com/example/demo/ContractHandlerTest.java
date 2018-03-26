package com.example.demo;

import application.CommandHandlers.ContractHandler;
import application.Commands.CreateContractCommand;
import application.Events.ContractCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;


public class ContractHandlerTest {

	private FixtureConfiguration<ContractHandler> fixture;

	@Before
	public void setUp() {

		fixture = new AggregateTestFixture<>(ContractHandler.class);

		fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());

	}
		
	@Test
	public void testCreateContract( ) {
		CreateContractCommand command = new CreateContractCommand("1","1", "1", "1");

		fixture.givenNoPriorActivity().when(command)
		.expectEvents(new ContractCreatedEvent("1", "1", "1", "1"));
	}



}
