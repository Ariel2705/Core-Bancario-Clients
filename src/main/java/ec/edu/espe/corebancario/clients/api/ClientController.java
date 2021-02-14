/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.api;

import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRQ;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.InsertException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.model.Client;

import ec.edu.espe.corebancario.clients.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/corebancario/client")
@Slf4j
public class ClientController {
    
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }
    
    @GetMapping("/findClient")
    public ResponseEntity findClient(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(this.service.findClients(client));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Client client) {
        try {
            this.service.createClient(client);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        } 
    }
    
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UpdateClientRQ updateClient){
        try {
            this.service.updateClient(updateClient);
            return ResponseEntity.ok().build();
        } catch (UpdateException ex) {
            return ResponseEntity.badRequest().build();
        } 
    }
    
}
