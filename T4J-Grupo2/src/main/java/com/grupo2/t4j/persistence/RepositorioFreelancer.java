/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.util.ArrayList;

public interface RepositorioFreelancer {
    
    void save(Email email, String nome, Password password,
            String nif, String telefone, String codigoEnderecoPostal) throws FreelancerDuplicadoException;

    boolean save(Freelancer freelancer);

    Freelancer findByNif(String nif);

    ArrayList<Freelancer> getAll();
}
