package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MenuDosen implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement pst = null;
    PreparedStatement pstt = null;

    @FXML
    private AnchorPane menuDosen;

    @FXML
    private TextArea tfId;

    @FXML
    private Button btn_lihatNilaiP;

    @FXML
    private Button btn_lihatLaporan;

    @FXML
    private TableView<DaftarUjian> tvJadwalUjian;

    @FXML
    private TableColumn<DaftarUjian, Integer> colID;

    @FXML
    private TableColumn<DaftarUjian, String> colNama;

    @FXML
    private TableColumn<DaftarUjian, String> colNim;

    @FXML
    private TableColumn<DaftarUjian, String> colEmail;

    @FXML
    private TableColumn<DaftarUjian, String> colJadwal;

    @FXML
    private TableColumn<DaftarUjian, String> colNilai;

    @FXML
    private ComboBox typeNilai;

    @FXML
    private Button btn_submitAll;

    @FXML
    private TextField tfNilai;


    @FXML
    private Button btn_back;

    @FXML
    void lihatLaporan(ActionEvent event) {

    }

    @FXML
    void lihatNilaiPer(ActionEvent event) {

    }

    public ObservableList<DaftarUjian> getDaftarUjianList(){
        ObservableList<DaftarUjian>DaftarUjianList= FXCollections.observableArrayList();
        conn = mysqlconnect.ConnectDb();
        String query = "SELECT * FROM DaftarUjian";



        try{
            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            DaftarUjian daftarUjian;
            while(rs.next()){
                daftarUjian = new DaftarUjian(rs.getInt("ID"), rs.getString("Nama"), rs.getString("NIM"), rs.getString("Email"),
                        rs.getString("waktuUjian"),rs.getString("Nilai"));
                DaftarUjianList.add(daftarUjian);
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  DaftarUjianList;
    }
    public void showIDujian(){
        ObservableList<DaftarUjian> list = getDaftarUjianList();

        colID.setCellValueFactory(new PropertyValueFactory<DaftarUjian, Integer>("ID"));
        colNama.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Nama"));
        colNim.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("NIM"));
        colEmail.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Email"));
        colJadwal.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("waktuUjian"));
        colNilai.setCellValueFactory(new PropertyValueFactory<DaftarUjian, String>("Nilai"));

        tvJadwalUjian.setItems(list);
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
        DaftarUjian daftarMahasiswa = tvJadwalUjian.getSelectionModel().getSelectedItem();
        tfId.setText(""+daftarMahasiswa.getID());
        tfNilai.setText(daftarMahasiswa.getNilai());

        btn_submitAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "UPDATE daftarujian set Nilai=? where ID=? ";
                try {
                    pstt = conn.prepareStatement(sql);
                    pstt.setString(1, typeNilai.getValue().toString());
                    pstt.setString(2,tfId.getText());
                    pstt.execute();
                    showIDujian();
                    JOptionPane.showMessageDialog(null, "Data telah disimpan");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak disimpan"+" "+e);
                }
            }
        });


    }


    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlClass/tampilanLogin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showIDujian();
        typeNilai.getItems().addAll("A","B+","B","C+","C","D","E");
    }

}

