/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    @Indexed(name = "idxu_clients_identification", unique = true)
    private String identificacion;
    private String names;
    private String surnames;
    private String genre;
    private Date birthdate;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private String nationality;
    private Contributor contributor;
    private BigDecimal totalBalanceAccount;

}
