package ec.edu.espe.corebancario.clients.repository;

import ec.edu.espe.corebancario.clients.model.Client;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

    Client findByIdentification(String identification);

    List<Client> findByBirthdate(Date birthdate);

    List<Client> findByAddressesProvince(String province);

    List<Client> findByAddressesCanton(String canton);

    List<Client> findByAddressesParish(String parish);

    List<Client> findByContributorNull();

    List<Client> findByContributorNotNull();
}
