/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nunocastro
 */
public class OrganizacaoDB extends Persistable {

    private Organizacao org;
    
    public OrganizacaoDB(Object object) {
        super(object);
        org = (Organizacao) object;
    }

    @Override
    public boolean save() {
        try {
            //Implementar método de persistência
            System.out.println(org.toString());
            return true;    
        }
        catch(Exception e) {
            return false;
        }
    }
}
