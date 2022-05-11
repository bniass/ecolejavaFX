package controller;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Formateur;
import model.Matiere;
import service.FormateurService;
import service.MatiereService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FormateurController implements Initializable {

    @FXML
    private TextField nomTFD;

    @FXML
    private TextField prenomTFD;

    @FXML
    private TextField emailTFD;

    @FXML
    private TextField salaireTFD;

    @FXML
    private ComboBox<Matiere> matiereCBX;

    @FXML
    private Button saveBTN;

    @FXML
    private Button initBTN;

    @FXML
    private TableView<Formateur> formateurTBV;

    @FXML
    private TableColumn<Formateur, String> nomTBCL;

    @FXML
    private TableColumn<Formateur, String> prenomTBCL;

    @FXML
    private TableColumn<Formateur, String> emailTBCL;

    @FXML
    private TableColumn<Formateur, Integer> salaireTBCL;

    @FXML
    private TableColumn<Formateur, LocalDate> dateNaissanceTBCL;

    @FXML
    private TableColumn<Formateur, String> matiereTBCL;

    FormateurService service = null;

    @FXML
    private DatePicker dateNaissancePK;

    @FXML
    void handleInit(ActionEvent event) {
        System.out.println("Init");
    }

    @FXML
    void handleSave(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion ecole");
        alert.setHeaderText("Gestion des formateurs");
        if(nomTFD.getText().trim().isEmpty() || prenomTFD.getText().trim().isEmpty()){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Tous les champs sont obligatoires !");
            alert.showAndWait();
            return;
        }

        try {
            Formateur formateur = new Formateur();
            formateur.setNom(nomTFD.getText());
            formateur.setPrenom(prenomTFD.getText());
            formateur.setEmail(emailTFD.getText());
            formateur.setSalaire(Integer.parseInt(salaireTFD.getText()));
            formateur.setDateNaissance(dateNaissancePK.getValue());
            System.out.println(dateNaissancePK.getValue());
            Matiere m = matiereCBX.getValue();
            formateur.setMatiere(m);
            service.save(formateur);
            alert.setContentText("Formateur enregistré !");
            alert.showAndWait();
            formateurObservableList.add(formateur);
            resetForm();
        }catch (Exception ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Erreur :"+ex.getMessage());
            alert.showAndWait();
        }
    }

    private void resetForm(){
        nomTFD.setText("");
        prenomTFD.setText("");
        emailTFD.setText("");
        salaireTFD.setText("");
        dateNaissancePK.setValue(LocalDate.now());
        matiereCBX.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            service = new FormateurService();
            MatiereService matiereService = new MatiereService();
            List<Matiere> matieres = matiereService.findAll();
            matiereCBX.setItems(FXCollections.observableList(matieres));

            initTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<Formateur> formateurObservableList;

    private void initTable(){
        try {
            nomTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>
                    (cellData.getValue().getNom()));
            prenomTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>
                    (cellData.getValue().getPrenom()));
            emailTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>
                    (cellData.getValue().getEmail()));
            salaireTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getSalaire()));
            dateNaissanceTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateNaissance()));
            matiereTBCL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMatiere().getLibelle()));

            List<Formateur> formateurs = service.findAll();
            formateurObservableList = FXCollections.observableList(formateurs);
            formateurTBV.setItems(formateurObservableList);

        }catch (Exception e){

        }
    }
}

