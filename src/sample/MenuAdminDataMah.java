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


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuAdminDataMah implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement pst = null;


    @FXML
    private AnchorPane paneDatamahasiswa;
    @FXML
    private AnchorPane paneTabel;

    @FXML
    private TableView<DataMahasiswa> tvDataMahasiswa;

    @FXML
    private TableColumn<DataMahasiswa, Integer> colId;

    @FXML
    private TableColumn<DataMahasiswa, String> colNamaAnggota;

    @FXML
    private TableColumn<DataMahasiswa, String> colLokasi;

    @FXML
    private TableColumn<DataMahasiswa, String> colAwal;

    @FXML
    private TableColumn<DataMahasiswa, String> colAkhir;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_lihatCV;

    @FXML
    private Button btn_lihatPortofolio;

    @FXML
    private TextArea taId;

    @FXML
    private TextArea taAnggotaKelompok;

    @FXML
    private TextArea taLokasi;

    @FXML
    private TextArea taWaktuAwal;

    @FXML
    private TextArea taWaktuAkhir;

    @FXML
    private Button btn_backward;

    @FXML
    private Button btn_forward;

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
    void backward(ActionEvent event) {
        if(btn_backward.isManaged()){
            conn = mysqlconnect.ConnectDb();
            int id = Integer.parseInt(taId.getText());
            int hitung = id-1;
            String back = String.valueOf(hitung);

            String query = "SELECT * FROM datamahasiswa where idKel = "+back;
            try{
                pst = conn.createStatement();
                rs = pst.executeQuery(query);
                while(rs.next()){
                    taId.setText(String.valueOf(rs.getInt("idKel")));
                    taAnggotaKelompok.setText(String.valueOf(rs.getString("anggotaKel")));
                    taLokasi.setText(String.valueOf(rs.getString("alamatKel")));
                    taWaktuAwal.setText(String.valueOf(rs.getString("waktuAwal")));
                    taWaktuAkhir.setText(String.valueOf(rs.getString("waktuAkhir")));
                }

            }catch (Exception ex){
                ex.printStackTrace();

            }
        }
    }

    @FXML
    void forward(ActionEvent event) {
        if(btn_forward.isManaged()){
            conn = mysqlconnect.ConnectDb();

            try{
                int id = Integer.parseInt(taId.getText());
                int hitung = id+1;
                String next = String.valueOf(hitung);

                String query = "SELECT * FROM datamahasiswa where idKel = "+next;

                pst = conn.createStatement();
                rs = pst.executeQuery(query);
                while(rs.next()){
                    taId.setText(String.valueOf(rs.getInt("idKel")));
                    taAnggotaKelompok.setText(String.valueOf(rs.getString("anggotaKel")));
                    taLokasi.setText(String.valueOf(rs.getString("alamatKel")));
                    taWaktuAwal.setText(String.valueOf(rs.getString("waktuAwal")));
                    taWaktuAkhir.setText(String.valueOf(rs.getString("waktuAkhir")));
                }

            }catch (Exception ex){
                ex.printStackTrace();

            }
        }
    }

    @FXML
    void lihatPortofolio(ActionEvent event) {

    }

    @FXML
    void lihatV(ActionEvent event) {

    }

    public ObservableList<DataMahasiswa> getDataMahasiswaList(){
        ObservableList<DataMahasiswa>DataMahasiswaList= FXCollections.observableArrayList();
        conn = mysqlconnect.ConnectDb();
        String query = "SELECT * FROM DataMahasiswa";


        try{
            pst = conn.createStatement();
            rs = pst.executeQuery(query);
            DataMahasiswa datamahasiswa;
            while(rs.next()){
                datamahasiswa = new DataMahasiswa(rs.getInt("idKel"), rs.getString("anggotaKel"), rs.getString("alamatKel"), rs.getString("waktuAwal"),
                        rs.getString("waktuAkhir"));
                DataMahasiswaList.add(datamahasiswa);
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return  DataMahasiswaList;
    }

    public void showID(){
        ObservableList<DataMahasiswa> list = getDataMahasiswaList();

        colId.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, Integer>("idKel"));
        colNamaAnggota.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("anggotaKel"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("alamatKel"));
        colAwal.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("waktuAwal"));
        colAkhir.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("waktuAkhir"));

        tvDataMahasiswa.setItems(list);
    }


    public void DatapaneShow() {
        paneDatamahasiswa.setVisible(true);
        paneTabel.setVisible(false);
    }

    public void TabelShow() {
        paneDatamahasiswa.setVisible(false);
        paneTabel.setVisible(true);
        taId.setText("1");

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        DataMahasiswa dataMahasiswa = tvDataMahasiswa.getSelectionModel().getSelectedItem();
        taId.setText(""+dataMahasiswa.getIdKel());
        taAnggotaKelompok.setText(dataMahasiswa.getAnggotaKel());
        taLokasi.setText(dataMahasiswa.getAlamatKel());
        taWaktuAwal.setText(dataMahasiswa.getWaktuAwal());
        taWaktuAkhir.setText(dataMahasiswa.getWaktuAkhir());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showID();

    }
}


