package application.api;

import application.Commands.CreateContractCommand;
import application.Commands.CreateCustomerCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class ContractController {

    private CommandGateway commandGateway;

    @Autowired
    public ContractController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String ping() {
        return "ping";
    }



    @RequestMapping(value = "/v1/contracts", method = RequestMethod.POST)
    @ResponseStatus(OK)
    public CompletableFuture<Object> post(@RequestBody ContractDto dto) {
        CreateContractCommand command = new CreateContractCommand(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                dto.number,
                dto.name);

       return commandGateway.send(command).exceptionally(e -> e);
    }

    @RequestMapping(value = "/v1/customers", method = RequestMethod.POST)
    @ResponseStatus(OK)
    public void post2( @RequestBody ContractDto dto) {
        CreateCustomerCommand command = new CreateCustomerCommand(UUID.randomUUID().toString(), dto.name);
        commandGateway.send(command);
    }


}
