package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

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
    private TableView<?> tvPelaksanaanUjian;

    @FXML
    private AnchorPane menuAdmin;

    @FXML
    private Button btn_Approval;

    @FXML
    private Button btn_PenjadwalanUjian;

    @FXML
    private Button btn_NilaiPKN;

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
}
