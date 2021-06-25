package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdminJadwalUjian {

    @FXML
    private TableView<?> tvJadwalUjian;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colNama;

    @FXML
    private TableColumn<?, ?> colNim;

    @FXML
    private TableColumn<?, ?> colEmali;

    @FXML
    private TableColumn<?, ?> colJadwal;

    @FXML
    private TableColumn<?, ?> colNilai;

    @FXML
    private Button btn_back;

    @FXML
    private DatePicker tfWaktuJadwal;

    @FXML
    private TextArea tfId;

    @FXML
    private Button btn_hapus;

    @FXML
    private Button btn_submit;

    @FXML
    private Button btn_lihatNilaiP;

    @FXML
    private Button btn_lihatLaporan;

    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlClass/MenuAdmin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @FXML
    void hapus(ActionEvent event) {

    }

    @FXML
    void lihatLaporan(ActionEvent event) {

    }

    @FXML
    void lihatNilaiPer(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
