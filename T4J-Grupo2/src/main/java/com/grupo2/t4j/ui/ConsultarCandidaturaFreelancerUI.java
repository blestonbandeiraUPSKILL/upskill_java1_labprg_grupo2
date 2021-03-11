package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultarCandidaturaFreelancerUI implements Initializable {

    private ColaboradorLogadoUI colaboradorLogadoUI;

    private RegistarFreelancerController registarFreelancerController;
    private SeriarAnuncioController seriarAnuncioController;
    private RegistarTarefaController registarTarefaController;
    private RegistarReconhecimentoGPController registarReconhecimentoGPController;
    private Stage adicionarStage;

    @FXML
    TextField txtIdAnuncio;
    @FXML
    TextField txtIdCandidatura;
    @FXML
    TextField txtNome;
    @FXML
    TextField txtNIF;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtApresentacao;
    @FXML
    TextField txtMotivacao;
    @FXML
    TextField txtCustoAnuncio;
    @FXML
    TextField txtCustoFreelancer;
    @FXML
    TextField txtDuracaoAnuncio;
    @FXML
    TextField txtDuracaoFreelancer;
    @FXML
    Button btnVoltar;
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
     * @param ColaboradorLogadoUI 
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
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

        String email = colaboradorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getEmail();
        int idAnuncio = colaboradorLogadoUI.getIdAnuncio();
        int idCandidatura = colaboradorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getIdCandidatura();

        Freelancer freelancer = registarFreelancerController.findByEmail(email);
        Candidatura candidatura = seriarAnuncioController.findById(idCandidatura);
        Tarefa tarefa = registarTarefaController.findTarefa(idAnuncio);

        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        txtIdCandidatura.setText(Integer.toString(idCandidatura));
        txtNome.setText(freelancer.getNome());
        txtNIF.setText(freelancer.getNif());
        txtEmail.setText(email);
        txtApresentacao.setText(candidatura.getApresentacao());
        txtMotivacao.setText(candidatura.getMotivacao());
        mostrarCompetencias(email);
        mostrarHabilitacao(email);
        txtCustoAnuncio.setText(Double.toString(registarTarefaController.findTarefa(idAnuncio).getCustoEst()));
        txtCustoFreelancer.setText(Double.toString(colaboradorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getCusto()));
        txtDuracaoFreelancer.setText(Integer.toString(colaboradorLogadoUI.tabelaCandidaturasFreelancers.getSelectionModel().getSelectedItem().getDuracao()));
        txtDuracaoAnuncio.setText(Integer.toString(tarefa.getDuracaoEst()));
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
