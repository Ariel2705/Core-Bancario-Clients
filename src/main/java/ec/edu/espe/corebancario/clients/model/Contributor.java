/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.model;

import lombok.Data;

@Data
public class Contributor {
    private String tradeName;
    private String bussinessName;
    private LegalRepresentative legalRepresentative;
}
