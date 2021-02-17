/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.service;

import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRQ;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.InsertException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.model.Client;
import ec.edu.espe.corebancario.clients.repository.ClientRepository;
import java.math.BigDecimal;
import java.util.List;
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
            log.info("Creando cliente");
            client.setTotalBalanceAccount(BigDecimal.ZERO);
            this.clientRepo.insert(client);
        } catch (Exception e) {
            throw new InsertException("client", "Ocurrio un error al crear el usuario: " + client.toString(), e);
        }
    }

    public void updateClient(UpdateClientRQ client) throws UpdateException {
        try {
            Client clientUpdate = this.clientRepo.findByIdentification(client.getIdentification());
            if(clientUpdate != null) {
                log.info("Actualizando datos del cliente");
                clientUpdate.setNames((client.getNames() != null) ? client.getNames() : clientUpdate.getNames());
                clientUpdate.setSurnames((client.getSurnames() != null) ? client.getSurnames() : clientUpdate.getSurnames());
                clientUpdate.setGenre((client.getGenre() != null) ? client.getGenre() : clientUpdate.getGenre());
                clientUpdate.setAddresses((client.getAddresses() != null) ? client.getAddresses() : clientUpdate.getAddresses());
                clientUpdate.setPhones((client.getPhones() != null) ? client.getPhones() : clientUpdate.getPhones());
                clientUpdate.setEmail((client.getEmail() != null) ? client.getEmail() : clientUpdate.getEmail());
                clientUpdate.setNationality((client.getNationality()!= null) ? client.getNationality(): clientUpdate.getNationality());
                clientUpdate.setContributor((client.getContributor() != null) ? client.getContributor() : clientUpdate.getContributor());
                clientUpdate.setTotalBalanceAccount((client.getTotalBalanceAccount() != null) ? client.getTotalBalanceAccount() : clientUpdate.getTotalBalanceAccount());
                this.clientRepo.save(clientUpdate);                
            }else{
                throw new UpdateException("client", "Ocurrio un error, no existe el cliente.");
            }
        } catch (Exception e) {
            throw new UpdateException("client", "Ocurrio un error al actualizar el cliente: " + client.toString(), e);
        }
    }
    
    public List<Client> findClientsByNamesAndSurnames(Client client) throws DocumentNotFoundException {
         try {
            log.info("Buscando clientes con nombres: " + client.getNames() + " y apellidos" + client.getSurnames());
            List<Client> clientFind = this.clientRepo.findByNamesAndSurnames(client.getNames(),client.getSurnames());
            if(!clientFind.isEmpty()){
              return clientFind;  
            }else{
             throw new DocumentNotFoundException("No se encontro el cliente.");
         }            
        } catch (Exception e) {
            throw new DocumentNotFoundException("No se encontro el cliente.");
        }
    }
    
    public List<Client> findClientsByTotalBalanceAccount(Integer from, Integer to) throws DocumentNotFoundException {
         try {
            log.info("Listando cuentas con saldo entre: " + from + " hasta " + to);
            List<Client> clientFind = this.clientRepo.findByTotalBalanceAccountBetween(from,to);
            if(!clientFind.isEmpty()){
              return clientFind;  
            }else{
             throw new DocumentNotFoundException("No se encontro el cliente.");
         }            
        } catch (Exception e) {
            throw new DocumentNotFoundException("No se encontro el cliente.");
        }
    }
    
    public List<Client> findClientsByGenre(String genre) throws DocumentNotFoundException{
        try {
            log.info("Listando cuentas con genero: " + genre);
            List<Client> clientFind = this.clientRepo.findByGenre(genre);
            if(!clientFind.isEmpty()){
              return clientFind;  
            }else{
             throw new DocumentNotFoundException("No se encontro el cliente.");
         }            
        } catch (Exception e) {
            throw new DocumentNotFoundException("No se encontro el cliente.");
        }
    }
    
    public Client findClientByIdentification(String identification) throws DocumentNotFoundException{
        try {
            log.info("Cuenta con identificacion: " + identification);
            Client clientFind = this.clientRepo.findByIdentification(identification);
            if(clientFind != null){
              return clientFind;  
            }else{
             throw new DocumentNotFoundException("No se encontro el cliente.");
         }            
        } catch (Exception e) {
            throw new DocumentNotFoundException("No se encontro el cliente.");
        }
    }
}
