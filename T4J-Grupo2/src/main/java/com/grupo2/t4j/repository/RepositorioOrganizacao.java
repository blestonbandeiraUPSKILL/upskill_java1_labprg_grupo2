/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao {

    private List<Organizacao> listaOrganizacoes = new ArrayList<>();
    
    public RepositorioOrganizacao(List<Organizacao> listaOrganizacoes){
        this.listaOrganizacoes = listaOrganizacoes;
    }
    public void addOrganizacao(Organizacao organizacao) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(organizacao.getNif());
        if (o == null) {
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }
    
    private Organizacao getOrganizacaoByNif(String NIF) {
        Organizacao organizacao = null;
        for (int i = 0; i < this.listaOrganizacoes.size(); i++) {
            organizacao = this.listaOrganizacoes.get(i);
            if (organizacao.getNif().equals(NIF)) {
                Organizacao copia = new Organizacao(organizacao);
                return copia;
            }
        }
        return null;
    }
}
