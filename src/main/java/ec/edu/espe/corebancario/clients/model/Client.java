package ec.edu.espe.corebancario.clients.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "clients")
public class Client {

    @Id
    private String id;
    @Indexed(name = "idxc_identification", unique = true)
    private String identification;
    private String names;
    private String surnames;
    private String genre;
    private Date birthdate;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private String nationality;
    private Contributor contributor;
}
