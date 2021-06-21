package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuMahasiswa implements Initializable {

    @FXML
    private AnchorPane loadingMenu;

    @FXML
    private AnchorPane menuMahasiswa;

    @FXML
    private Button btn_pengajuanProposal;

    @FXML
    private Button btn_pendaftaranUjian;

    @FXML
    private AnchorPane menuDosen;

    @FXML
    private Button btn_back;

    @FXML
    private TableView<?> tvPelaksanaanUjian;

    @FXML
    private AnchorPane menuAdmin;

    @FXML
    private Button btn_Approval;

    @FXML
    private Button btn_PenjadwalanUjian;

    @FXML
    private Button btn_NilaiPKN;

    /*public void MenuMahasiswaShow(){
        menuMahasiswa.setVisible(true);
        menuDosen.setVisible(false);
        menuAdmin.setVisible(false);
    }*/

    @FXML
    void Approval(ActionEvent event) {

    }

    @FXML
    void NilaiPKN(ActionEvent event) {

    }

    @FXML
    void PendaftaranUjian(ActionEvent event) {

    }

    @FXML
    void PengajuanProposal(ActionEvent event) {

    }

    @FXML
    void PenjadwalanUjian(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("tampilanLogin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
}
