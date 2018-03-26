package application.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contract {

    @Id
    private String id;

    private String customerId;

    private String number;

    @SuppressWarnings("unused")
    public Contract() {
    }

    public Contract(String id, String customerId, String number) {
        this.id = id;
        this.customerId = customerId;
        this.setNumber(number);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
