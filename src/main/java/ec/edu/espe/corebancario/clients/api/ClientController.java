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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Busqueda de cliente por número de identificacion", notes = "Busqueda de cliente por número de identificacion. Un cliente puede tener cuentas y tarjetas de credito.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente encontrado"),
        @ApiResponse(code = 404, message = "Cliente no existente")
    })
    public ResponseEntity findClientById(@PathVariable String identification) {
        try {
            return ResponseEntity.ok(this.service.findClientByIdentification(identification));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByBirthdate/{birthdate}")
    @ApiOperation(value = "Busqueda de clientes por fecha de nacimiento", notes = "Busqueda de clientes por fecha de nacimiento.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente/es encontrado"),
        @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity findClientByBirthdate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate) {        
        try {
            return ResponseEntity.ok(this.service.findClientByBirthDate(birthdate));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByProvince/{province}")
    @ApiOperation(value = "Busqueda de clientes por provincia de residencia", notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente/es encontrado"),
        @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity findClientByProvince(@PathVariable String province) {        
        try {
            return ResponseEntity.ok(this.service.findClientByProvince(province));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByCanton/{canton}")
    @ApiOperation(value = "Busqueda de clientes por canton de residencia", notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente/es encontrado"),
        @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity findClientByCanton(@PathVariable String canton) {        
        try {
            return ResponseEntity.ok(this.service.findClientByCanton(canton));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByParish/{parish}")
    @ApiOperation(value = "Busqueda de clientes por parroquia de residencia", notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente/es encontrado"),
        @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity findClientByParish(@PathVariable String parish) {        
        try {
            return ResponseEntity.ok(this.service.findClientByParish(parish));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/findClientByType/{type}")
    @ApiOperation(value = "Busqueda de clientes por tipo de persona", notes = "Un cliente puede ser tipo persona natural o juridica.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente/es encontrado"),
        @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity findClientByType(@PathVariable String type) {        
        try {
            return ResponseEntity.ok(this.service.findClientByType(type));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/create")
    @ApiOperation(value = "Crea un cliente", notes = "Crea un cliente del Banco. Un cliente tiene cuentas o al menos una cuenta, y tambien puede tener tarjetas de credito")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente creado"),
        @ApiResponse(code = 400, message = "Error al crear cliente")
    })
    public ResponseEntity create(@RequestBody Client client) {
        try {
            this.service.createClient(client);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "Actualiza campos de un cliente", notes = "Actualiza campos permitidos de un cliente.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente actualizado"),
        @ApiResponse(code = 400, message = "Error al actualizar cliente")
    })
    public ResponseEntity update(@RequestBody UpdateClientRQ updateClient) {
        try {
            this.service.updateClient(updateClient);
            return ResponseEntity.ok().build();
        } catch (UpdateException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
