package ec.edu.espe.corebancario.clients.api;

import ec.edu.espe.corebancario.clients.api.dto.ClientRq;
import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRq;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.InsertException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.model.Client;
import ec.edu.espe.corebancario.clients.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/corebancario/client")
@Slf4j
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }
    
    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientById/{identification}")
    @ApiOperation(value = "Busqueda de cliente por número de identificacion",
            notes = "Busqueda de cliente por número de identificacion."
                    + " Un cliente puede tener cuentas y tarjetas de credito.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente no existente")
    })
    public ResponseEntity<ClientRq> findClientById(@PathVariable String identification) {
        try {
            return ResponseEntity.ok(this.service.findClientByIdentification(identification));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByBirthdate/{birthdate}")
    @ApiOperation(value = "Busqueda de clientes por fecha de nacimiento",
            notes = "Busqueda de clientes por fecha de nacimiento.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByBirthdate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate) {
        try {
            return ResponseEntity.ok(this.service.findClientByBirthDate(birthdate));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByProvince/{province}")
    @ApiOperation(value = "Busqueda de clientes por provincia de residencia",
            notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByProvince(@PathVariable String province) {
        try {
            return ResponseEntity.ok(this.service.findClientByProvince(province));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByCanton/{canton}")
    @ApiOperation(value = "Busqueda de clientes por canton de residencia",
            notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByCanton(@PathVariable String canton) {
        try {
            return ResponseEntity.ok(this.service.findClientByCanton(canton));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByParish/{parish}")
    @ApiOperation(value = "Busqueda de clientes por parroquia de residencia",
            notes = "Un cliente puede tener varias direcciones de residencia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByParish(@PathVariable String parish) {
        try {
            return ResponseEntity.ok(this.service.findClientByParish(parish));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByBalance/{balance}")
    @ApiOperation(value = "Busqueda de clientes por balance de cuentas mayor o igual al balance solicitado",
            notes = "El balance de un cliente, dependerá del balance de todas sus cuentas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByBalance(@PathVariable BigDecimal balance) {
        try {
            return ResponseEntity.ok(this.service.findClientByBalance(balance));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @GetMapping("/findClientByType/{type}")
    @ApiOperation(value = "Busqueda de clientes por tipo de persona",
            notes = "Los clientes pueden ser persona tipo natural o juridico")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente/es encontrado"),
            @ApiResponse(code = 404, message = "Cliente/es no existente")
    })
    public ResponseEntity<List<ClientRq>> findClientByType(@PathVariable String type) {
        try {
            return ResponseEntity.ok(this.service.findClientByType(type));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @PostMapping("/create")
    @ApiOperation(value = "Crea un cliente",
            notes = "Crea un cliente del Banco. "
                    + "Un cliente tiene cuentas o al menos una cuenta.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente creado"),
            @ApiResponse(code = 400, message = "Error al crear cliente")
    })
    public ResponseEntity<Client> create(@RequestBody Client client) {
        try {
            this.service.createClient(client);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('admin') OR hasRole('accountManager')")
    @PutMapping("/update")
    @ApiOperation(value = "Actualiza campos de un cliente", notes = "Actualiza campos permitidos de un cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente actualizado"),
            @ApiResponse(code = 400, message = "Error al actualizar cliente")
    })
    public ResponseEntity<UpdateClientRq> update(@RequestBody UpdateClientRq updateClient) {
        try {
            this.service.updateClient(updateClient);
            return ResponseEntity.ok().build();
        } catch (UpdateException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
