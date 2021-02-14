/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.api.dto;

import ec.edu.espe.corebancario.clients.model.*;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class UpdateClientRQ {
    
    private String identification;
    private String names;
    private String surnames;
    private String genre;
    private List<Address> addresses;
    private String email;
    private List<Phone> phones;
    private String nationality;
    private Contributor contributor;
    private BigDecimal totalBalanceAccount;
}
