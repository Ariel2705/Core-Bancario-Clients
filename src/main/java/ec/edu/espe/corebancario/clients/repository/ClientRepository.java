/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.repository;
import ec.edu.espe.corebancario.clients.model.Client;
import java.math.BigDecimal;
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
