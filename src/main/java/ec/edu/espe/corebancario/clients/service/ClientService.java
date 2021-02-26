/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRQ;
import ec.edu.espe.corebancario.clients.api.dto.ClientRQ;
import ec.edu.espe.corebancario.clients.enums.TypeClientEnum;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.InsertException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.model.Client;
import ec.edu.espe.corebancario.clients.repository.ClientRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
            this.clientRepo.insert(client);
        } catch (Exception e) {
            throw new InsertException("client", "Ocurrio un error al crear el usuario: " + client.toString(), e);
        }
    }

    public void updateClient(UpdateClientRQ client) throws UpdateException {
        try {
            Client clientUpdate = this.clientRepo.findByIdentification(client.getIdentification());
            if (clientUpdate != null) {
                log.info("Actualizando datos del cliente");
                clientUpdate.setNames((client.getNames() != null) ? client.getNames() : clientUpdate.getNames());
                clientUpdate.setSurnames((client.getSurnames() != null) ? client.getSurnames() : clientUpdate.getSurnames());
                clientUpdate.setGenre((client.getGenre() != null) ? client.getGenre() : clientUpdate.getGenre());
                clientUpdate.setAddresses((client.getAddresses() != null) ? client.getAddresses() : clientUpdate.getAddresses());
                clientUpdate.setPhones((client.getPhones() != null) ? client.getPhones() : clientUpdate.getPhones());
                clientUpdate.setEmail((client.getEmail() != null) ? client.getEmail() : clientUpdate.getEmail());
                clientUpdate.setNationality((client.getNationality() != null) ? client.getNationality() : clientUpdate.getNationality());
                clientUpdate.setContributor((client.getContributor() != null) ? client.getContributor() : clientUpdate.getContributor());
                this.clientRepo.save(clientUpdate);
            } else {
                throw new UpdateException("client", "Ocurrio un error, no existe el cliente.");
            }
        } catch (Exception e) {
            throw new UpdateException("client", "Ocurrio un error al actualizar el cliente: " + client.toString(), e);
        }
    }

    public ClientRQ findClientByIdentification(String identification) throws DocumentNotFoundException {
        try {
            log.info("Cliente con identificacion: " + identification);
            Client clientFind = this.clientRepo.findByIdentification(identification);
            if (clientFind != null) {
                ClientRQ clientRQ = new ClientRQ();
                clientRQ.setIdentification(clientFind.getIdentification());
                clientRQ.setAddresses(clientFind.getAddresses());
                clientRQ.setContributor(clientFind.getContributor());
                clientRQ.setEmail(clientFind.getEmail());
                clientRQ.setNames(clientFind.getNames());
                clientRQ.setSurnames(clientFind.getSurnames());
                clientRQ.setPhones(clientFind.getPhones());
                return clientRQ;
            } else {
                throw new DocumentNotFoundException("No se encontro el cliente.");
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar cliente.");
        }
    }

    public List<ClientRQ> findClientByBirthDate(Date birthdate) throws DocumentNotFoundException {
        try {
            log.info("Clientes con anio de nacimiento: " + birthdate.toString());
            List<Client> clientFind = this.clientRepo.findByBirthdate(birthdate);
            if (!clientFind.isEmpty()) {
                List<ClientRQ> clients = new ArrayList<>(clientFind.size());
                for (int i = 0; i < clientFind.size(); i++) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
                return clients;
            } else {
                throw new DocumentNotFoundException("No se encontro cliente/es nacidos en " + birthdate.toString());
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

    public List<ClientRQ> findClientByProvince(String province) throws DocumentNotFoundException {
        try {
            log.info("Clientes residentes en la provincia: " + province);
            List<Client> clientFind = this.clientRepo.findByAddressesProvince(province);
            if (!clientFind.isEmpty()) {
                List<ClientRQ> clients = new ArrayList<>(clientFind.size());
                for (int i = 0; i < clientFind.size(); i++) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
                return clients;
            } else {
                throw new DocumentNotFoundException("No se encontro cliente/es con residencia en " + province);
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

    public List<ClientRQ> findClientByCanton(String canton) throws DocumentNotFoundException {
        try {
            log.info("Clientes residentes en el canton: " + canton);
            List<Client> clientFind = this.clientRepo.findByAddressesCanton(canton);
            if (!clientFind.isEmpty()) {
                List<ClientRQ> clients = new ArrayList<>(clientFind.size());
                for (int i = 0; i < clientFind.size(); i++) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
                return clients;
            } else {
                throw new DocumentNotFoundException("No se encontro cliente/es con residencia en " + canton);
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

    public List<ClientRQ> findClientByParish(String parish) throws DocumentNotFoundException {
        try {
            log.info("Clientes residentes en la parroquia: " + parish);
            List<Client> clientFind = this.clientRepo.findByAddressesParish(parish);
            if (!clientFind.isEmpty()) {
                List<ClientRQ> clients = new ArrayList<>(clientFind.size());
                for (int i = 0; i < clientFind.size(); i++) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
                return clients;
            } else {
                throw new DocumentNotFoundException("No se encontro cliente/es con residencia en " + parish);
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

    public List<ClientRQ> findClientByType(String type) throws DocumentNotFoundException {
        try {
            log.info("Clientes por tipo : " + type);
            List<Client> clientFind = new ArrayList<>();
            if (TypeClientEnum.NATURAL.getDescription().equals(type)) {
                clientFind = this.clientRepo.findByContributorNull();
            } else if (TypeClientEnum.JURIDICA.getDescription().equals(type)) {
                clientFind = this.clientRepo.findByContributorNotNull();
            }
            if (!clientFind.isEmpty()) {
                List<ClientRQ> clients = new ArrayList<>(clientFind.size());
                for (int i = 0; i < clientFind.size(); i++) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
                return clients;
            } else {
                throw new DocumentNotFoundException("No se encontro cliente/es tipo " + type);
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

    public List<ClientRQ> findClientByBalance(BigDecimal balance) throws DocumentNotFoundException {
        try {
            List<Client> clientFind = this.clientRepo.findAll();
            List<ClientRQ> clients = new ArrayList<>();
            for (int i = 0; i < clientFind.size(); i++) {
                String response = Unirest.get("http://localhost:8082/api/corebancario/account/balanceClient/{identification}")
                        .routeParam("identification", clientFind.get(i).getIdentification()).asString().getBody();
                if (new BigDecimal(response).compareTo(balance) >= 0) {
                    ClientRQ clientRQ = new ClientRQ();
                    clientRQ.setIdentification(clientFind.get(i).getIdentification());
                    clientRQ.setAddresses(clientFind.get(i).getAddresses());
                    clientRQ.setContributor(clientFind.get(i).getContributor());
                    clientRQ.setEmail(clientFind.get(i).getEmail());
                    clientRQ.setNames(clientFind.get(i).getNames());
                    clientRQ.setSurnames(clientFind.get(i).getSurnames());
                    clientRQ.setPhones(clientFind.get(i).getPhones());
                    clients.add(clientRQ);
                }
            }
            if (!clients.isEmpty()){
                return clients;
            }else{
                log.error("No existen clientes con balance de cuenta mayor o igual al solicitado.");
                throw new DocumentNotFoundException("No existen clientes con balance de cuenta mayor o igual al solicitado.");
            }           
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar clientes.");
        }
    }

}
