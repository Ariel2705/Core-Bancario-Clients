/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.model;

import lombok.Data;

@Data
public class Address {
    
    private String type;
    private String mainStreet;
    private String sideStreet;
    private String number;
    private String reference;
    private String province;
    private String canton;
    private String parish;
    
}
