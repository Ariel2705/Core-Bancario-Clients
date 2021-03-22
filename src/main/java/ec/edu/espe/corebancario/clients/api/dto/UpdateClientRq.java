package ec.edu.espe.corebancario.clients.api.dto;

import ec.edu.espe.corebancario.clients.model.Address;
import ec.edu.espe.corebancario.clients.model.Contributor;
import ec.edu.espe.corebancario.clients.model.Phone;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class UpdateClientRq {

    private String identification;
    private String names;
    private String surnames;
    private String genre;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private String nationality;
    private Contributor contributor;
    private BigDecimal totalBalanceAccount;
}
