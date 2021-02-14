/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.corebancario.clients.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationValues {
    
    private final String mongoHost;
    private final String mongoDB;

    @Autowired
    public ApplicationValues(@Value("${clients.mongo.host}") String mongoHost, @Value("${clients.mongo.db}") String mongoDB) {
        this.mongoHost = mongoHost;
        this.mongoDB = mongoDB;
    }
    
    
}
