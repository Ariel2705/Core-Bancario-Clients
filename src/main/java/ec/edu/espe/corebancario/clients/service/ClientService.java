/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.service;

import ec.edu.espe.corebancario.clients.exception.InsertException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.model.Client;
import ec.edu.espe.corebancario.clients.repository.ClientRepository;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void createClient(Client client) throws InsertException {
        try {
            client.setTotalBalanceAccount(BigDecimal.ZERO);
            this.clientRepo.insert(client);
        } catch (Exception e) {
            throw new InsertException("client", "Ocurrio un error al crear el usuario: " + client.toString(), e);
        }
    }

    public void updateClient(Client client) throws UpdateException {
        try {
            Client clientUpdate = this.clientRepo.findByIdentification(client.getIdentificacion());
            if(clientUpdate != null) {
                clientUpdate.setNames((client.getNames() != null) ? client.getNames() : clientUpdate.getNames());
                clientUpdate.setSurnames((client.getSurnames() != null) ? client.getSurnames() : clientUpdate.getSurnames());
                clientUpdate.setGenre((client.getGenre() != null) ? client.getGenre() : clientUpdate.getGenre());
                clientUpdate.setAddresses((client.getAddresses() != null) ? client.getAddresses() : clientUpdate.getAddresses());
                clientUpdate.setPhones((client.getPhones() != null) ? client.getPhones() : clientUpdate.getPhones());
                clientUpdate.setEmail((client.getEmail() != null) ? client.getEmail() : clientUpdate.getEmail());
                clientUpdate.setContributor((client.getContributor() != null) ? client.getContributor() : clientUpdate.getContributor());
                clientUpdate.setTotalBalanceAccount((client.getTotalBalanceAccount() != null) ? client.getTotalBalanceAccount() : clientUpdate.getTotalBalanceAccount());
                this.clientRepo.save(clientUpdate);                
            }
        } catch (Exception e) {
            throw new UpdateException("client", "Ocurrio un error al actualizar el cliente: " + client.toString(), e);
        }
    }
    
    public Client findClients(Client client){
         Client clientFind = this.clientRepo.findByClientIn(client);
         return clientFind;
    }
}
