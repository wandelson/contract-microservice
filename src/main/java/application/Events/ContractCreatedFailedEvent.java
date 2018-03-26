package application.Events;


import lombok.Value;

@Value
public class ContractCreatedFailedEvent {
    public String contractId;
    public String customerId;
    public String number;

}
