package application.Events;


import lombok.Value;

@Value
public class ContractCreatedEvent {
    public String contractId;
    public String customerId;
    public String number;
    public String customerName;

}
