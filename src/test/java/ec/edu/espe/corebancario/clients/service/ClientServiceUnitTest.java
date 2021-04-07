package ec.edu.espe.corebancario.clients.service;

import ec.edu.espe.corebancario.clients.api.dto.ClientRq;
import ec.edu.espe.corebancario.clients.api.dto.UpdateClientRq;
import ec.edu.espe.corebancario.clients.exception.DocumentNotFoundException;
import ec.edu.espe.corebancario.clients.exception.InsertException;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceUnitTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private Client client;
    private List<Client> clients;
    private ClientService service;
    private UpdateClientRq updateclientRq;
    private ClientRq clientRq;
    private List<ClientRq> clientsRq;

    @BeforeEach
    public void setUp() {
        this.client = new Client();        
        this.clients = new ArrayList<>();
        this.service = new ClientService(repository);
        this.updateclientRq = new UpdateClientRq();
        this.clientRq = new ClientRq();
        this.clientsRq = new ArrayList<>();
    }

    @Test
    public void givenClientCreateClient() {
        try {
            service.createClient(client);
            verify(repository, times(1)).insert(client);
        } catch (InsertException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void givenUpdateClientRqUpdateClient() {        
        try {
            when(repository.findByIdentification(updateclientRq.getIdentification())).thenReturn(client);
            service.updateClient(updateclientRq);
        } catch (UpdateException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void givenIdentificationReturnClientRq() {
        String identification = "17254560555";
        try {
            when(repository.findByIdentification(identification)).thenReturn(client);
            clientRq = service.findClientByIdentification(identification);
            Assertions.assertEquals(clientRq, service.findClientByIdentification(identification));
        } catch (DocumentNotFoundException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void givenDateReturnListOfClientRq() {
        Date date = new Date();
        try {
            when(repository.findByBirthdate(date)).thenReturn(clients);
            clientsRq = service.findClientByBirthDate(date);
            Assertions.assertEquals(clientsRq, service.findClientByBirthDate(date));
        } catch (DocumentNotFoundException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Test
    public void givenTypeReturnListOfNaturalClientRq() {
        String type = "Natural";
        try {
            when(repository.findByContributorNull()).thenReturn(clients);
            clientsRq = service.findClientByType(type);
            Assertions.assertEquals(clientsRq, service.findClientByType(type));
        } catch (DocumentNotFoundException ex) {
            Logger.getLogger(ClientServiceUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void givenNullIdentificationThrowDocumentNotFoundException() {
        String identification = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByIdentification(identification));
    }

    @Test
    public void givenNullDateThrowDocumentNotFoundException() {
        Date date = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByBirthDate(date));
    }

    @Test
    public void givenNullProvinceThrowDocumentNotFoundException() {
        String province = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByProvince(province));
    }

    @Test
    public void givenNullCantonThrowDocumentNotFoundException() {
        String canton = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByCanton(canton));
    }

    @Test
    public void givenNullParishThrowDocumentNotFoundException() {
        String parish = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByParish(parish));
    }

    @Test
    public void givenNullTypeThrowDocumentNotFoundException() {
        String type = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByType(type));
    }

    @Test
    public void givenBalanceTypeThrowDocumentNotFoundException() {
        BigDecimal balance = null;
        Assertions.assertThrows(DocumentNotFoundException.class, () -> service.findClientByBalance(balance));
    }

    @Test
    public void givenEmptyUpdateClientRqThrowUpdateException() {
        UpdateClientRq clientRq = new UpdateClientRq();
        Assertions.assertThrows(UpdateException.class, () -> service.updateClient(clientRq));
    }
}
