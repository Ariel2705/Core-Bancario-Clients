package ec.edu.espe.corebancario.clients.service;

import ec.edu.espe.corebancario.clients.api.dto.ClientRq;
import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRq;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.UpdateException;
import ec.edu.espe.corebancario.clients.repository.ClientRepository;
import ec.edu.espe.corebancario.clients.model.Client;
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
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceUnitTest {
    
    @Mock
    private ClientRepository repository;
    
    @InjectMocks
    Client client;
    
    @BeforeEach
    public void setUp() {
    }
    
    @Test
    public void givenIdentificationReturnClientRq(){
       String identification = "17254560555";
       ClientRq clientRq = new ClientRq();
       ClientService service = new ClientService(repository);
        try {
            Assertions.assertEquals(clientRq, service.findClientByIdentification(identification));
        } catch (DocumentNotFoundException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @Test
    public void givenDateReturnListOfClientRq(){
       Date date = new Date();
       List<ClientRq> clientsRq = new ArrayList<ClientRq>();
       ClientService service = new ClientService(repository);
        try {
            Assertions.assertEquals(clientsRq, service.findClientByBirthDate(date));
        } catch (DocumentNotFoundException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }    
    
    @Test
    public void givenNullIdentificationThrowDocumentNotFoundException(){
        String identification = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByIdentification(identification));
    }
    
    @Test
    public void givenNullDateThrowDocumentNotFoundException(){
        Date date = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByBirthDate(date));
    }
    
    @Test
    public void givenNullProvinceThrowDocumentNotFoundException(){
        String province = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByProvince(province));
    }
    
    @Test
    public void givenNullCantonThrowDocumentNotFoundException(){
        String canton = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByCanton(canton));
    }
    
    @Test
    public void givenNullParishThrowDocumentNotFoundException(){
        String parish = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByParish(parish));
    }
    
    @Test
    public void givenNullTypeThrowDocumentNotFoundException(){
        String type = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByType(type));
    }
    
    @Test
    public void givenBalanceTypeThrowDocumentNotFoundException(){
        BigDecimal balance = null;
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByBalance(balance));
    }
    
    @Test
    public void givenEmptyUpdateClientRqThrowUpdateException(){
        UpdateClientRq clientRq = new UpdateClientRq();
        ClientService service = new ClientService(repository);
        Assertions.assertThrows(UpdateException.class, () -> service.updateClient(clientRq));
    }   
}
