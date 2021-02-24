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
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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
    
    @GetMapping("/findClientById/{identification}")
    public ResponseEntity findClientById(@PathVariable String identification) {
        try {
            return ResponseEntity.ok(this.service.findClientByIdentification(identification));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByBirthdate/{birthdate}")
    public ResponseEntity findClientByBirthdate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate) {        
        try {
            return ResponseEntity.ok(this.service.findClientByBirthDate(birthdate));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByProvince/{province}")
    public ResponseEntity findClientByProvince(@PathVariable String province) {        
        try {
            return ResponseEntity.ok(this.service.findClientByProvince(province));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByCanton/{canton}")
    public ResponseEntity findClientByCanton(@PathVariable String canton) {        
        try {
            return ResponseEntity.ok(this.service.findClientByCanton(canton));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByParish/{parish}")
    public ResponseEntity findClientByParish(@PathVariable String parish) {        
        try {
            return ResponseEntity.ok(this.service.findClientByParish(parish));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByType/{type}")
    public ResponseEntity findClientByType(@PathVariable String type) {        
        try {
            return ResponseEntity.ok(this.service.findClientByType(type));
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
    public ResponseEntity update(@RequestBody UpdateClientRQ updateClient) {
        try {
            this.service.updateClient(updateClient);
            return ResponseEntity.ok().build();
        } catch (UpdateException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
