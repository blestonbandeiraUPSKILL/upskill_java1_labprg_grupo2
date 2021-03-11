package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import com.grupo2.t4j.persistence.RepositorioUtilizador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;
public class RegistarFreelancerController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();

   /**
     * Publicar tarefa boolean
     *
     * @param emailFree as email do freelancer
     * @param nome as nome do freelancer
     * @param nif as nif do freelancer
     * @param telefone as telefone do freelancer
     * @param arruamento as nome da rua onde mora o freelancer
     * @param numeroPorta as número da porta onde mora o freelancer
     * @param localidade as localidade onde mora o freelancer
     * @param codPostal as código postal onde mora o freelancer
     * @return boolean
     */    
    public boolean registarFreelancer(String emailFree, String nome, String nif, 
            String telefone, String arruamento, String numeroPorta, String localidade, String codPostal) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        String password = algoritmoGeradorPasswords.geraPassword();

        Freelancer freelancer = new Freelancer(emailFree, nome, nif, telefone, password, arruamento, numeroPorta, localidade, codPostal);

        registarFreelancerComoUtilizador(freelancer);

        return repositorioFreelancer.save(emailFree, nome, nif, telefone, password, arruamento, numeroPorta, localidade, codPostal);
    }

    public List<Freelancer> getAll() throws SQLException{
        return repositorioFreelancer.getAll();
    }
    
    public List<String> getAllEmails() throws SQLException{
        return repositorioFreelancer.getAllEmails();
    }
   
    public Freelancer findByNif(String NIF) throws SQLException{
        return repositorioFreelancer.findByNif(NIF);
    }
    
    public Freelancer findByEmail(String emailFree) throws SQLException{
        return repositorioFreelancer.findByEmail(emailFree);
    }

    public Password findPassword(String email) throws SQLException {
        return  repositorioFreelancer.findPassword(email);
    }

    public List<ReconhecimentoGP> getAllReconhecimentoGP(String emailFreelancer) throws SQLException {
        return repositorioFreelancer.getAllReconhecimentoGP(emailFreelancer);
    }

    public List<HabilitacaoAcademica> getAllHabsAcademicas(String emailFreelancer) throws SQLException {
        return repositorioFreelancer.getAllHabsAcademicas(emailFreelancer);
    }

    public EnderecoPostal getEnderecoPostal(String emailFreelancer) throws SQLException {
        return repositorioFreelancer.getEnderecoPostal(emailFreelancer);
    }

    ///////API
    public boolean registarFreelancerComoUtilizador(Freelancer freelancer) throws SQLException {
        String nome = freelancer.getNome();
        Email email = freelancer.getEmail();
        Password password = freelancer.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "freelancer");
    }


}
