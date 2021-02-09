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
public class Plataforma {
    private static Plataforma plataforma;
    private RegistoOrganizacoes ro;
    private IAlgoritmoGeradorPasswords agp;
    private UsersAPI uapi;
    
    public Plataforma(){
        //Geração de passwords
        agp = new AlgoritmoGeradorPasswords();
     
        //UsersAPI
        uapi = new UsersAPI();
        
        //Promoção do padrão HC/LC
        ro = new RegistoOrganizacoes(this);
        //...colocar aqui os restantes RegistoXXX
    }
    
    //Gets
    
    public static Plataforma getInstance() {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }
    
    public RegistoOrganizacoes getRegistoOrganizacoes() {
        return ro;
    }
    
    public IAlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }
    
    public UsersAPI getUsersAPI() {
        return uapi;
    }
}
