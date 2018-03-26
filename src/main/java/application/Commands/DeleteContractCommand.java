package application.Commands;


import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class DeleteContractCommand {

	@TargetAggregateIdentifier
	private String contractId;

}
