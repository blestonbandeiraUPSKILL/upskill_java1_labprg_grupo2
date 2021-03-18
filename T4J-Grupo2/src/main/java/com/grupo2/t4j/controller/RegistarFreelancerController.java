package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.dto.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RegistarFreelancerController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();

   /**
     * Regista um novo Freelancer
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

    /**
     * Devolve uma lista de todos os freelancers
     * @return
     * @throws SQLException 
     */
    public List<FreelancerDTO> getAll() throws SQLException{
        List<Freelancer> freelancers = repositorioFreelancer.getAll();
        List<FreelancerDTO> freelancersDTO = new ArrayList<>();

        for(Freelancer freelancer : freelancers) {
            freelancersDTO.add((FreelancerDTO) freelancer.toDTO());
        }
        return freelancersDTO;
    }
    
    /**
     * Devolve um freelancer a partir do seu email
     * @param emailFree
     * @return
     * @throws SQLException 
     */
    public FreelancerDTO findByEmail(String emailFree) throws SQLException{
        Freelancer freelancer = repositorioFreelancer.findByEmail(emailFree);
        return (FreelancerDTO) freelancer.toDTO();
    }

    /**
     * Devolve a password de um freelancer
     * @param email
     * @return
     * @throws SQLException 
     */
    public PasswordDTO findPassword(String email) throws SQLException {
        Password password = repositorioFreelancer.findPassword(email);
        return (PasswordDTO) password.toDTO();
    }

    /**
     * Devolve uma lista de todos os reconhecimentos de todas as competencias de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public List<ReconhecimentoGPDTO> getAllReconhecimentoGP(String emailFreelancer) throws SQLException {
        List<ReconhecimentoGPDTO> reconhecimentosGPDTO = new ArrayList<>();
        List<ReconhecimentoGP> reconhecimentosGP = repositorioFreelancer.getAllReconhecimentoGP(emailFreelancer);

        for (ReconhecimentoGP reconhecimentoGP : reconhecimentosGP) {
            reconhecimentosGPDTO.add((ReconhecimentoGPDTO) reconhecimentoGP.toDTO());
        }
        return reconhecimentosGPDTO;
    }

    /**
     * Devolve uma lista de todas as habilitacoes academicas de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public List<HabilitacaoAcademicaDTO> getAllHabsAcademicas(String emailFreelancer) throws SQLException {
        List<HabilitacaoAcademica> habilitacoesAcademicas = repositorioFreelancer.getAllHabsAcademicas(emailFreelancer);
        List<HabilitacaoAcademicaDTO> habilitacoesAcademicasDTO = new ArrayList<>();

        for (HabilitacaoAcademica habilitacaoAcademica : habilitacoesAcademicas) {
            habilitacoesAcademicasDTO.add((HabilitacaoAcademicaDTO) habilitacaoAcademica.toDTO());
        }
        return habilitacoesAcademicasDTO;
    }

    /**
     * Devolve o endereco postal de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public EnderecoPostalDTO getEnderecoPostal(String emailFreelancer) throws SQLException {
        EnderecoPostal enderecoPostal = repositorioFreelancer.getEnderecoPostal(emailFreelancer);

        return (EnderecoPostalDTO) enderecoPostal.toDTO();
    }

    ///////API
    
    /**
     * Regista um freelancer como utilizador da aplicacao
     * @param freelancer
     * @return
     * @throws SQLException 
     */
    public boolean registarFreelancerComoUtilizador(Freelancer freelancer) throws SQLException {
        String nome = freelancer.getNome();
        Email email = freelancer.getEmail();
        Password password = freelancer.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "freelancer");
    }


}
