package application.Events;

import lombok.Value;

@Value
public class ContractDeletedEvent {
    public String contractId;
}
