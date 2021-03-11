package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.controller.RegistarReconhecimentoGPController;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class ConsultarFreelancerUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarFreelancerController registarFreelancerController;
    private RegistarReconhecimentoGPController registarReconhecimentoGPController;

    @FXML TextField txtPorta;
    @FXML TextField txtNif;
    @FXML TextField txtNome;
    @FXML TextField txtEmail;
    @FXML TextField txtCodigoPostal;
    @FXML TextField txtLocalidade;
    @FXML TextField txtArruamento;
    @FXML ListView<ReconhecimentoGP> listViewCompTec;
    @FXML ListView<HabilitacaoAcademica> listViewHabAcad;
    @FXML Button btnVoltar;
    @FXML TableColumn<Object, Object> txtCompTec;
    @FXML TableColumn<Object, Object> txtDataReconhecimento;
    @FXML TableColumn<Object, Object> txtGrau;
    @FXML TableView<ReconhecimentoGP> tabelaReconhecimento;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarFreelancerController = new RegistarFreelancerController();
        registarReconhecimentoGPController = new RegistarReconhecimentoGPController() ;

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }

    public void transferData() throws SQLException {
        String emailFreelancer = administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();

        String arruamento = registarFreelancerController.getEnderecoPostal(emailFreelancer).getArruamento();
        String numeroPorta = registarFreelancerController.getEnderecoPostal(emailFreelancer).getPorta();
        String localidade = registarFreelancerController.getEnderecoPostal(emailFreelancer).getLocalidade();
        String codPostal = registarFreelancerController.getEnderecoPostal(emailFreelancer).getCodigoPostal();

        txtNome.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getNome());
        txtNif.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getNif());
        txtEmail.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
        txtArruamento.setText(arruamento);
        txtPorta.setText(numeroPorta);
        txtLocalidade.setText(localidade);
        txtCodigoPostal.setText(codPostal);
        mostrarCompetencias(emailFreelancer);
        listViewHabAcad.getItems().setAll(registarFreelancerController.getAllHabsAcademicas(emailFreelancer));

    
    
    public void mostrarCompetencias (String emailFreelancer) throws SQLException {
        tabelaReconhecimento.getItems().setAll(registarReconhecimentoGPController.getAll(emailFreelancer));
        
        txtCompTec.setCellValueFactory(new PropertyValueFactory<>("descBreveCompetencia"));
        txtGrau.setCellValueFactory(new PropertyValueFactory<>("designacaoGrau"));
        txtDataReconhecimento.setCellValueFactory(new PropertyValueFactory<>("dataReconhecimento"));
    }

    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

}
