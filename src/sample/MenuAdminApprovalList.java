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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class MenuAdminApprovalList implements Initializable {
    Connection conn = null;
    ResultSet rs = null;
    Statement pst = null;
    PreparedStatement pstt = null;
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
    private TableColumn<DataMahasiswa, String> colPersetujuan;

    @FXML
    private TextField taId;



    @FXML
    private Button btn_hapus;

    @FXML
    private Button btn_back;

    @FXML
    void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlClass/MenuAdminApproval.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
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
                        rs.getString("waktuAkhir"),rs.getString("Persetujuan"));
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
        colPersetujuan.setCellValueFactory(new PropertyValueFactory<DataMahasiswa, String>("Persetujuan"));


        tvDataMahasiswa.setItems(list);
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
        DataMahasiswa dataMahasiswa = tvDataMahasiswa.getSelectionModel().getSelectedItem();
        taId.setText(""+dataMahasiswa.getIdKel());
    }

    @FXML
    void hapus(ActionEvent event) {
        btn_hapus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = mysqlconnect.ConnectDb();
                String sql = "delete from datamahasiswa where idKel=? ";
                try {
                    pstt = conn.prepareStatement(sql);
                    pstt.setString(1,taId.getText());
                    pstt.execute();
                    showID();
                    JOptionPane.showMessageDialog(null, "Data telah dihapus");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data tidak dihapus"+" "+e);
                }
            }
        });

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showID();

    }

}
