package ec.edu.espe.corebancario.clients.api.dto;

import ec.edu.espe.corebancario.clients.model.Address;
import ec.edu.espe.corebancario.clients.model.Contributor;
import ec.edu.espe.corebancario.clients.model.Phone;
import java.util.List;
import lombok.Data;

@Data
public class ClientRq {

    private String identification;
    private String names;
    private String surnames;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private Contributor contributor;
}
