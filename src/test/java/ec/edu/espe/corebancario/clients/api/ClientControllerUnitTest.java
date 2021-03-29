package ec.edu.espe.corebancario.clients.api;

import ec.edu.espe.corebancario.clients.api.dto.ClientRq;
import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRq;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.model.Client;
import ec.edu.espe.corebancario.clients.service.ClientService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ClientControllerUnitTest {
    
    @Mock
    private ClientService service;
    
    @InjectMocks
    Client client;
    
    @BeforeEach
    public void setUp() {
    }
    
    @Test
    public void givenIdentificationReturnClientRq(){
        String identification = "1725456055";
        ClientController controller = new ClientController(service);
        ClientRq result = new ClientRq();
        try {
            when (service.findClientByIdentification(identification))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientById(identification));  
    }
    
    @Test
    public void givenDateReturnListOFClientRq(){
        Date date = new Date(); 
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByBirthDate(date))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByBirthdate(date));  
    }
    
    @Test
    public void givenProvinceReturnListOfClientRq(){
        String province = "Pichincha";
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByProvince(province))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByProvince(province));  
    }
    
    @Test
    public void givenCantonReturnListOfClientRq(){
        String canton = "Quito";
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByCanton(canton))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByCanton(canton));  
    }
    
    @Test
    public void givenParishReturnListOfClientRq(){
        String parish = "Chaupicruz";
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByParish(parish))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByParish(parish));  
    }
    
    @Test
    public void givenTypeReturnListOfClientRq(){
        String type = "Natural";
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByType(type))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByType(type));  
    }
    
    @Test
    public void givenBalanceReturnListOfClientRq(){
        BigDecimal balance = new BigDecimal("10");
        ClientController controller = new ClientController(service);
        List<ClientRq> result = new ArrayList<ClientRq>();
        try {
            when (service.findClientByBalance(balance))
                    .thenReturn(result);
        } catch (DocumentNotFoundException ex) {
            
        }
        ResponseEntity response = ResponseEntity.ok(result);
        Assertions.assertEquals(response, controller.findClientByBalance(balance));  
    }
    
    @Test
    public void givenClientRqReturnOk(){
        UpdateClientRq updateClientRq = new UpdateClientRq();
        ClientController controller = new ClientController(service);        
        ResponseEntity response = ResponseEntity.ok().build();
        Assertions.assertEquals(response, controller.update(updateClientRq));  
    }
    
    @Test
    public void givenNullIdentificationReturnNotFound(){
        String identification = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByIdentification(identification);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientById(identification));              
    }
    
    @Test
    public void givenNullDateReturnNotFound(){
        Date date = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByBirthDate(date);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByBirthdate(date));              
    }
    
    @Test
    public void givenNullProvinceReturnNotFound(){
        String province = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByProvince(province);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByProvince(province));              
    }
    
    @Test
    public void givenNullCantonReturnNotFound(){
        String canton = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByCanton(canton);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByCanton(canton));              
    }
    
    @Test
    public void givenNullParishReturnNotFound(){
        String parish = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByParish(parish);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByParish(parish));              
    }
    
    @Test
    public void givenNullTypeReturnNotFound(){
        String type = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByType(type);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByType(type));              
    }
    
    @Test
    public void givenNullBalanceReturnNotFound(){
        BigDecimal balance = null;
        ClientController controller = new ClientController(service);
        try {
            Mockito.doThrow(DocumentNotFoundException.class)
                    .when (service)
                    .findClientByBalance(balance);
        } catch (DocumentNotFoundException ex) {
           Logger.getLogger(ClientControllerUnitTest.class.getName()).log(Level.SEVERE, null, ex); 
        }
        ResponseEntity response = ResponseEntity.notFound().build();
        Assertions.assertEquals(response, controller.findClientByBalance(balance));              
    }
}
