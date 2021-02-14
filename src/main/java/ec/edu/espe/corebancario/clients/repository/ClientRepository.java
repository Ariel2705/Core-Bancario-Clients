/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.repository;
import ec.edu.espe.corebancario.clients.model.Client;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
       
    Client findByIdentification(String identification);
    List<Client> findByNamesAndSurnames(String names,String surNames);
    List<Client> findByTotalBalanceAccountBetween(Integer from, Integer to);  
}
