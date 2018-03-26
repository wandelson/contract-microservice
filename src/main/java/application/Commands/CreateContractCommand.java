package application.Commands;


import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

@Value
public class CreateContractCommand {

	@TargetAggregateIdentifier
	private String contractId;
	private String customerId;
	private String number;
	private String CustomerName;

}
