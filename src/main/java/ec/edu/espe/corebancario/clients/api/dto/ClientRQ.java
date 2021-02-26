/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.api.dto;

import java.util.List;
import lombok.Data;
import ec.edu.espe.corebancario.clients.model.Address;
import ec.edu.espe.corebancario.clients.model.Phone;
import ec.edu.espe.corebancario.clients.model.Contributor;

@Data
public class ClientRQ{
   
    private String identification;
    private String names;
    private String surnames;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private Contributor contributor;
    
}
