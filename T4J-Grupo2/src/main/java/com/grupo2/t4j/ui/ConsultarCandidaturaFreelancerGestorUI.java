package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.controller.RegistarReconhecimentoGPController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.controller.SeriarAnuncioController;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarCandidaturaFreelancerGestorUI implements Initializable {

    private GestorLogadoUI gestorLogadoUI;

    private RegistarFreelancerController registarFreelancerController;
    private SeriarAnuncioController seriarAnuncioController;
    private RegistarTarefaController registarTarefaController;
    private RegistarReconhecimentoGPController registarReconhecimentoGPController;
    private Stage adicionarStage;

    @FXML TextField txtIdAnuncio;
    @FXML TextField txtIdCandidatura;
    @FXML TextField txtNome;
    @FXML TextField txtNIF;
    @FXML TextField txtEmail;
    @FXML TextField txtApresentacao;
    @FXML TextField txtMotivacao;
    @FXML TextField txtCustoAnuncio;
    @FXML TextField txtCustoFreelancer;
    @FXML TextField txtDuracaoAnuncio;
    @FXML TextField txtDuracaoFreelancer;
    @FXML Button btnVoltar;

    ////Tabela Reconhecimento///////////////////////
    @FXML TableColumn<Object, Object> txtCompTec;
    @FXML TableColumn<Object, Object> txtDataReconhecimento;
    @FXML TableColumn<Object, Object> txtGrau;
    @FXML TableView<ReconhecimentoGP> tabelaReconhecimento;
    
    ////Tabela Habilitacoes Academicas//////////////
    @FXML TableColumn<Object, Object> txtGrauHabilitacao;
    @FXML TableColumn<Object, Object> txtDesignacaoCurso;
    @FXML TableColumn<Object, Object> txtMedia;
    @FXML TableColumn<Object, Object> txtInstituicao;
    @FXML TableView<HabilitacaoAcademica> tabelaHabilitacao;

    
    /**
     * Associa a scene ColaboradorLogadoUI como parent desta Scene 
     * @param gestorLogadoUI
     */
    public void associarParentUI(GestorLogadoUI gestorLogadoUI) {
        this.gestorLogadoUI = gestorLogadoUI;
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarFreelancerController = new RegistarFreelancerController();
        seriarAnuncioController = new SeriarAnuncioController();
        registarReconhecimentoGPController = new RegistarReconhecimentoGPController();
        try {
            registarTarefaController = new RegistarTarefaController();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }

    /**
     * Preenche a Scene com os dados da candidatura do Freelancer
     * @throws SQLException 
     */
    public void transferData() throws SQLException {

        String email = gestorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getEmail();
        int idAnuncio = gestorLogadoUI.getIdAnuncio();
        int idCandidatura = gestorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getIdCandidatura();

        String nome = registarFreelancerController.findByEmail(email).getNome();
        String nif = registarFreelancerController.findByEmail(email).getNif();
        String apresentacao = seriarAnuncioController.findById(idCandidatura).getApresentacao();
        String motivacao = seriarAnuncioController.findById(idCandidatura).getMotivacao();
        double custoAnuncio = registarTarefaController.findTarefa(idAnuncio).getCustoEst();
        int duracaoAnuncio = registarTarefaController.findTarefa(idAnuncio).getDuracaoEst();int duracaoFreelancer = seriarAnuncioController.findById(idCandidatura).getNumeroDias();

        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        txtIdCandidatura.setText(Integer.toString(idCandidatura));
        txtNome.setText(nome);
        txtNIF.setText(nif);
        txtEmail.setText(email);
        txtApresentacao.setText(apresentacao);
        txtMotivacao.setText(motivacao);
        mostrarCompetencias(email);
        mostrarHabilitacao(email);
        txtCustoAnuncio.setText(Double.toString(custoAnuncio));
        txtCustoFreelancer.setText(Double.toString(gestorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getCusto()));
        txtDuracaoFreelancer.setText(Integer.toString(gestorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getDuracao()));
        txtDuracaoAnuncio.setText(Integer.toString(duracaoAnuncio));
    }
    
    /**
     * Preenche a tabela de Competencias do Freelancer
     * @param emailFreelancer
     * @throws SQLException 
     */
    public void mostrarCompetencias (String emailFreelancer) throws SQLException {
        tabelaReconhecimento.getItems().setAll(registarReconhecimentoGPController.getAll(emailFreelancer));
        
        txtCompTec.setCellValueFactory(new PropertyValueFactory<>("descBreveCompetencia"));
        txtGrau.setCellValueFactory(new PropertyValueFactory<>("designacaoGrau"));
        txtDataReconhecimento.setCellValueFactory(new PropertyValueFactory<>("dataReconhecimento"));
    }

    /**
     * Preenche a tabela de habilitações do Freelancer
     * @param emailFreelancer
     * @throws SQLException 
     */
    public void mostrarHabilitacao (String emailFreelancer) throws SQLException {
        tabelaHabilitacao.getItems().setAll(registarFreelancerController.getAllHabsAcademicas(emailFreelancer));
        
        txtGrauHabilitacao.setCellValueFactory(new PropertyValueFactory<>("grau"));
        txtMedia.setCellValueFactory(new PropertyValueFactory<>("mediaCurso"));
        txtInstituicao.setCellValueFactory(new PropertyValueFactory<>("nomeInstituicao"));
        txtDesignacaoCurso.setCellValueFactory(new PropertyValueFactory<>("designacaoCurso"));
    }
    
    /**
     * Volta para a Scene anterior
     * @param event 
     */
    @FXML
    public void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
