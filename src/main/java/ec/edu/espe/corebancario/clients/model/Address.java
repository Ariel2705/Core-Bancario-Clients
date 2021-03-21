package ec.edu.espe.corebancario.clients.model;

import lombok.Data;

@Data
public class Address {

    private String type;
    private String mainStreet;
    private String sideStreet;
    private String number;
    private String reference;
    private String province;
    private String canton;
    private String parish;
}
