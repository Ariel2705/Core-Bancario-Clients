/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.enums;


public enum TypeClientEnum {
    NATURAL("NAT", "Natural"),
    JURIDICA("JUD", "Juridico");
    
    private final String type;
    private final String description;

    private TypeClientEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String gettype() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    
}
